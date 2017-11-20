package com.example.josejoescobar.medlinenetwork2;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.intellij.lang.annotations.JdkConstants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Marlo on 8/15/2017.
 */

public class Medicinas extends AppCompatActivity implements View.OnClickListener {
    Button regresar2;
    TableLayout tablaDeHistorail;
    int id, counter, position, prePos, counterMas;
    Modificacion modify = new Modificacion();
    String paraGrabar;
    String[] nombre, fecha_de_inicio,tiempo_de_uso, diagnostico, observaciones;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicinas);
        regresar2 = (Button) findViewById(R.id.regresar2);
        regresar2.setOnClickListener(this);
        tablaDeHistorail = (TableLayout) findViewById(R.id.tableToFill2);
        //agarar extra datos
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        //coneccion base de datos
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    System.out.println(response);
                    JSONObject jsonResponse = new JSONObject(response);
                    paraGrabar = jsonResponse.getString("nombre");
                    counter = 0;
                    for(int i = 0; i < paraGrabar.length();i++){
                        if(response.charAt(i) == ',')
                            counter++;
                    }
                    nombre = new String[counter+1];
                    fecha_de_inicio = new String[counter+1];
                    diagnostico = new String[counter+1];
                    observaciones = new String[counter+1];
                    tiempo_de_uso = new String[counter+1];
                    nombre = deJasonModifier3000(jsonResponse.getString("nombre"));
                    fecha_de_inicio = deJasonModifier3000(jsonResponse.getString("fecha_inicio"));
                    diagnostico = deJasonModifier3000(jsonResponse.getString("diagnostico"));
                    tiempo_de_uso = deJasonModifier3000(jsonResponse.getString("tiempo_de_uso"));
                    observaciones = deJasonModifier3000(jsonResponse.getString("observacion"));
                    //crear Tabla
                    createTable();
                }catch (JSONException e) {
                    System.out.println("ERROR: JSON RESPONSE UNEXPECTED");
                }

            }
        };

        MedicinasRequest hMR = new MedicinasRequest(id, responseListener);
        RequestQueue queue = Volley.newRequestQueue(Medicinas.this);
        queue.add(hMR);


    }

    @Override
    public void onClick(View view) {
        switch( view.getId()){
            case R.id.regresar2:
                Intent intent = new Intent(Medicinas.this, MenuUsuario.class);
                intent.putExtra("id", id);
                startActivity(intent);
                break;
        }

    }

    private void createTable(){
        int nombreLength = nombre.length;
        tablaDeHistorail.setStretchAllColumns(true);
        tablaDeHistorail.bringToFront();

        TextView[] titulos = new TextView[1];
        for(int i =0; i < 1; i++){
            titulos[i] = new TextView(this.getApplicationContext());
            titulos[i].setTextSize(23);
        }
        TableRow row2;
        row2 = new TableRow(this.getApplicationContext());
        titulos[0].setText("Medicinas en uso:\n");

        for(int i = 0; i < 1; i++){
            row2.addView(titulos[i]);
        }
        tablaDeHistorail.addView(row2,0);
        for (int i = 0; i < nombreLength; i++) {

            row2=new TableRow(this.getApplicationContext());
            TextView nombreView=new TextView(this.getApplicationContext());
            TextView fechaView = new TextView(this.getApplicationContext());
            TextView tiempoView = new TextView(this.getApplicationContext());

            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row2.setLayoutParams(lp);

            nombreView.setText(nombre[i]);
            nombreView.setTypeface(null, Typeface.BOLD);
            nombreView.setTextSize(15);


            fechaView.setText(fecha_de_inicio[i]);
            fechaView.setTextSize(15);



            tiempoView.setText(tiempo_de_uso[i]);
            tiempoView.setTextSize(15);

            row2.addView(nombreView);
            row2.addView(fechaView);
            row2.addView(tiempoView);

            tablaDeHistorail.addView(row2,i+1);
        }

    }

    private String[] deJasonModifier3000(String noobString){
        prePos = 0;
        counterMas = 0;
        String[] proPwnString = new String[counter+1];
        System.out.println("noobString> " + noobString);
        //System.out.println("LENGHT DEL NOOB STRING: " + noobString.length());
        //System.out.println("NOOB STRING: " + noobString);

        for(int i = 0; i < noobString.length();i++){
            if(noobString.charAt(i) == ',' || noobString.charAt(i) == ']' ) {
                proPwnString[counterMas] = "";
                for (int k = 0; k < (i - prePos); k++) {
                    if (noobString.charAt(k+prePos) != ']' && noobString.charAt(k+prePos) != '[' && noobString.charAt(k+prePos) != ',' && noobString.charAt(k+prePos) != '"' && noobString.charAt(k+prePos) != '\\')
                        proPwnString[counterMas] += "" + noobString.charAt(k+prePos);
                }
                //System.out.println("proPWNSTRING en el curr es:=: "+proPwnString[counterMas]);
                counterMas++;
                prePos = i;
            }
            //System.out.println("i = " + i);
        }
        return proPwnString;
    }
}
