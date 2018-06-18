package com.interdisciplinar.mainaccount.agoravaiapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListagemActivity extends Activity {

    private TextView voltar;
    private ListView list;
    private ArrayAdapter<Evento> adapter;
    private ArrayList<Evento> eventos;
    private DatabaseReference mDatabase;
    private ValueEventListener valueEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem);

        voltar = findViewById(R.id.tvVoltar);
        list = findViewById(R.id.list);

        eventos = new ArrayList<>();
        adapter = new EventoAdapter(this,eventos);
        list.setAdapter(adapter);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase = mDatabase.child("eventos");

        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                eventos.clear();

                for (DataSnapshot dados : dataSnapshot.getChildren()){
                    Evento novoEvento =  dados.getValue(Evento.class);
                    eventos.add(novoEvento);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callActivity(DashboardActivity.class);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mDatabase.addValueEventListener(valueEventListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mDatabase.removeEventListener(valueEventListener);
    }

    private void callActivity(Class newActivity) {
        Intent newIntent = new Intent(ListagemActivity.this,newActivity);
        startActivity(newIntent);
        finish();
    }
}
