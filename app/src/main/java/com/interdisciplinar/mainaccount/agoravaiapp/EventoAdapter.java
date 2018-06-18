package com.interdisciplinar.mainaccount.agoravaiapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class EventoAdapter extends ArrayAdapter<Evento> {

    private ArrayList<Evento> eventos;
    private Context contexto;


    public EventoAdapter(Context context, ArrayList<Evento> objects) {
        super(context, 0, objects);
        this.contexto = context;
        this.eventos = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = null;

        LayoutInflater inflater = (LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(R.layout.listaevento, parent, false);

        TextView txtTitulo = (TextView) view.findViewById(R.id.txtTitulo);
        TextView txtCategoria = (TextView) view.findViewById(R.id.txtCategoria);
        TextView txtDescricao = (TextView) view.findViewById(R.id.txtDescricao);

        Evento evento = eventos.get(position);

        txtTitulo.setText("Título: "+evento.getTitulo());
        txtCategoria.setText("Categoria: "+evento.getCategoria());
        txtDescricao.setText("Descrição: "+ evento.getDescricao());

        return view;
    }

}
