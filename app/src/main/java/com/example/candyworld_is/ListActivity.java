package com.example.candyworld_is;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    private ListView listView;
    private MyAdapter myAdapter;
    ArrayList<String> nombres;
    private int contador = 0;
    //ArrayList<Candy> nombre = new ArrayList<Candy>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.ListView);

        /*nombre.add(new Candy("Marshmallow ", "Golosina de distintas formas"), R.drawable.marshmallow);
        nombre.add(new Candy("Donut", "Dulce de distintos sabores y rellenos")R.drawable.);
        nombre.add(new Candy("Oreo", "Galleta de chocolate con crema"), R.drawable.);
        nombre.add(new Candy("Kit-Kat", "Barquillo de chocolate con leche", R.drawable.kitkat));*/

        nombres = new ArrayList<String>();
        nombres.add("Marshmallow "+'\n'+"Golosina de distintas formas");
        nombres.add("Donut"+'\n'+"Dulce de distintos sabores y rellenos");
        nombres.add("Oreo"+'\n'+"Galleta de chocolate con crema");
        nombres.add("Kit-Kat"+'\n'+"Barquillo de chocolate con leche");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                Toast.makeText(ListActivity.this, "click en "+nombres.get(i), Toast.LENGTH_LONG).show();
            }
        });

        //Enlazamos con nuestro adaptador personalizado
        myAdapter = new MyAdapter(this, R.layout.list_item, nombres);
        listView.setAdapter(myAdapter);

        //Registramos el adaptador
        registerForContextMenu(listView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.add_item:
                //Agregamos nuevo nombre
                /*Candy dulce = new Candy("Dulce"+'\n'+"Golosina ");
                nombre.add(dulce);*/
                this.nombres.add("Dulce"+'\n'+"Golosina "+ (++contador));
                //Notificamos al adaptador del cambio producido
                this.myAdapter.notifyDataSetChanged();
                return true;

            case R.id.cambioVista:
                Intent intent = new Intent(ListActivity.this, GridActivity.class);
                intent.putExtra("nombres", nombres);
                /*
                intent.putExtra("variable_string", objeto.getNombre());
                intent.putExtra("objeto_float", objeto.getPrecio());
                 */
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //Creamos el menu contextual
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        //menu.setHeaderTitle(this.nombre.get(info.position).getNombre());
        menu.setHeaderTitle(this.nombres.get(info.position));
        inflater.inflate(R.menu.context_menu, menu);
    }

    //Manejamos eventos del menu contextual
    @Override
    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch(item.getItemId()){
            case R.id.delete_item:
                //Eliminamos el item clickeado
                this.nombres.remove(info.position);
                //Notificamos al adapter del cambio
                this.myAdapter.notifyDataSetChanged();
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }
}
