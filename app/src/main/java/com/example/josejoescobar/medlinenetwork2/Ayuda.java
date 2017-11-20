package com.example.josejoescobar.medlinenetwork2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Ayuda extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ayuda);
    }
    public void Agregar(View view) {
        Intent i = new Intent(this, Agregar.class );
        startActivity(i);
    }
    public void Escoger(View view) {
        Intent j = new Intent(this, Escoger.class );
        startActivity(j);
    }
    public void Citas(View view) {
        Intent k = new Intent(this, Citas.class );
        startActivity(k);
    }
    public void Buscar(View view) {
        Intent l = new Intent(this, Buscar.class );
        startActivity(l);
    }
}
