package com.interdisciplinar.mainaccount.agoravaiapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class CreateEventoActivity extends Activity {

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private Evento evento;

    private EditText txtTitulo;
    private EditText txtCategoria;
    private EditText txtDescricao;
    private Button btnCadEvento;

    private TextView voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_evento);

        mAuth = FirebaseAuth.getInstance();
        txtTitulo = findViewById(R.id.txtTitulo);
        txtCategoria = findViewById(R.id.txtCategoria);
        txtDescricao = findViewById(R.id.txtDescricao);
        btnCadEvento = findViewById(R.id.btnCadEvento);
        voltar = findViewById(R.id.textViewVoltar);

        btnCadEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evento = new Evento();
                evento.setTitulo(txtTitulo.getText().toString());
                evento.setCategoria(txtCategoria.getText().toString());
                evento.setDescricao(txtDescricao.getText().toString());

                salvarEvento(evento);
            }
        });

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callActivity(DashboardActivity.class);
            }
        });
    }

    private boolean salvarEvento(Evento evento){
        String id = UUID.randomUUID().toString();
        try{
            mDatabase = FirebaseDatabase.getInstance().getReference();
            mDatabase = mDatabase.child("eventos");
            mDatabase.child(id).setValue(evento);
            Toast.makeText(CreateEventoActivity.this,"Evento cadastrado!", Toast.LENGTH_SHORT).show();

            callActivity(DashboardActivity.class);

            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    private void callActivity(Class newActivity) {
        Intent newIntent = new Intent(CreateEventoActivity.this,newActivity);
        startActivity(newIntent);
        finish();
    }
}
