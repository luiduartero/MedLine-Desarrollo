package com.example.josejoescobar.medlinenetwork2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Agregar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregar);
    }
    public void Regresar(View view) {
        Intent i = new Intent(this, Ayuda.class );
        startActivity(i);
    }
}
