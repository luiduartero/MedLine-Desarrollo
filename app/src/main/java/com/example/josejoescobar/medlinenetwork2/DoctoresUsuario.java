package com.example.josejoescobar.medlinenetwork2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Marlo on 8/15/2017.
 */

public class DoctoresUsuario extends AppCompatActivity implements View.OnClickListener{
    Button regresar;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctores);
        //regresar = (Button) findViewById(R.id.regresar);
        //regresar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        /**switch( view.getId()){
            case R.id.regresar:
                startActivity(new Intent(this, MenuUsuario.class));
                break;
        }*/

    }
}
