package com.example.candyworld_is;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<String> nombres;

    public MyAdapter(Context context, int layout, ArrayList<String> nombres){
        this.context = context;
        this.layout = layout;
        this.nombres = nombres;
    }

    @Override
    public int getCount(){return this.nombres.size();}

    @Override
    public Object getItem(int position) {
        return this.nombres.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        //Patron View holder
        ViewHolder holder;

        if(convertView == null){
            //Inflamos la vista que nos ha llegado a nuestro layout
            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            convertView = layoutInflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();

            //Referencia el elemento a modificar y lo rellenamos
            holder.nametextView = (TextView) convertView.findViewById(R.id.textView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        //Nos traemos el valor actual dependiente de la posicion
        String nombreActual = nombres.get(position);

        //Referencias el elemento a modificar y lo rellenamos
        holder.nametextView.setText(nombreActual);

        return convertView;
    }

    static class ViewHolder{
        public TextView nametextView;
    }
}
