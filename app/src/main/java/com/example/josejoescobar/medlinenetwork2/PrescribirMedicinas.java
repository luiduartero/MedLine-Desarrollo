package com.example.josejoescobar.medlinenetwork2;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PrescribirMedicinas extends AppCompatActivity implements View.OnClickListener{
    TextView enfermedad, mes, dia, anio, receta, cantidad, tiempo, diasDeUso, observaciones, id;
    Button generar;
    Spinner spinMed;
    String enfermedadT, mesT, diaT, anioT, recetaT, cantidadT, tiempoT, getDiaT, observacionT, idT, fechaParaMandar, fechaFinal, errorMessage, paraGrabar;
    int day,month,year, doses,counter, indexSpinner;
    boolean errorEnIngresar;
    Modificacion test = new Modificacion();
    String[] idString, nombre;
    ArrayList<String> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescribir_medicinas);
        enfermedad = (EditText) findViewById(R.id.ETxtDiagnostico);
        spinMed = (Spinner) findViewById(R.id.spinnerMedicina);
        dia = (EditText) findViewById(R.id.ETxtDia);
        mes = (EditText) findViewById(R.id.ETxtMes);
        anio = (EditText) findViewById(R.id.ETxtAno);
        receta = (EditText) findViewById(R.id.ETxtMedicina);
        cantidad = (EditText) findViewById(R.id.ETxtCantidadMedicamento);
        tiempo = (EditText) findViewById(R.id.ETxtTiempoDosisAlDia);
        diasDeUso = (EditText) findViewById(R.id.ETxtTiempoDeUso);
        observaciones = (EditText) findViewById(R.id.ETxtObservaciones);
        id = (EditText) findViewById(R.id.ETxtIDPaciente);
        generar = (Button) findViewById(R.id.button2);
        generar.setOnClickListener(this);
        errorEnIngresar = false;
        errorMessage = "";
            System.out.println("ANTES DEL FEO");

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
                    System.out.println("ONRESPONSE PRESCRIBIR> ");
                    idString = deJasonModifier3000(jsonResponse.getString("id_medicina"));
                    //System.out.println("el id = "+idString[0]);
                    nombre = deJasonModifier3000(jsonResponse.getString("nombre")); 
                    //crear Tabla
                    fillDropdown(nombre);
                }catch (JSONException e) {
                    System.out.println("ERROR: JSON RESPONSE UNEXPECTED");
                }

            }
        };
        DropDownRequest hMR = new DropDownRequest(responseListener);
        RequestQueue queue = Volley.newRequestQueue(PrescribirMedicinas.this);
        queue.add(hMR);
        //spinMed
        //spinMed.getSelectedItemId();



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button2:
                errorEnIngresar = false;
                errorMessage = "";
                mesT = mes.getText().toString();
                diaT = dia.getText().toString();
                anioT = anio.getText().toString();
                fechaParaMandar = ""+anioT+"-"+mesT+"-"+diaT;
                enfermedadT = enfermedad.getText().toString();
                anioT = anio.getText().toString();
                //recetaT = receta.getText().toString();
                tiempoT = tiempo.getText().toString();
                cantidadT = cantidad.getText().toString();
                getDiaT = diasDeUso.getText().toString();
                observacionT = observaciones.getText().toString();

                indexSpinner = spinMed.getSelectedItemPosition();
                recetaT = idString[indexSpinner];
                idT = id.getText().toString();

                //ERROR HANDLING
                if(recetaT == null || recetaT.equalsIgnoreCase("")) {
                    errorEnIngresar = true;
                    errorMessage += "Tiene que resetar Medicina\n";
                }if(mesT == null || mesT.equalsIgnoreCase("") || diaT == null || diaT.equalsIgnoreCase("") || anioT == null || anioT.equalsIgnoreCase("")  ) {
                    errorEnIngresar = true;
                    errorMessage += "Favor Ingresar Fecha\n";
                }if(tiempoT == null || tiempoT.equalsIgnoreCase("")) {
                    errorEnIngresar = true;
                    errorMessage += "Favor ingresar cuanto tiempo se utilizara\n";
                }if(cantidadT == null || cantidadT.equalsIgnoreCase("")) {
                    errorEnIngresar = true;
                    errorMessage += "Favor ingresar cantidad\n";
                }if(getDiaT == null || getDiaT.equalsIgnoreCase("")) {
                    errorEnIngresar = true;
                    errorMessage += "Favor ingresar cuantos dias\n";
                }if(idT == null || idT.equalsIgnoreCase("")) {
                    errorEnIngresar = true;
                    errorMessage += "Favor ingresar el ID del usuario\n";
                }if(!test.soloCorreo(recetaT)){
                errorEnIngresar = true;
                errorMessage += "no se permite ingresar simbolos";
            }if(!test.soloCorreo(mesT)){
                errorEnIngresar = true;
                errorMessage += "no se permite ingresar simbolos";
            }if(!test.soloCorreo(tiempoT)){
                errorEnIngresar = true;
                errorMessage += "no se permite ingresar simbolos";
            }if(!test.soloCorreo(cantidadT)){
                errorEnIngresar = true;
                errorMessage += "no se permite ingresar simbolos";
            }if(!test.soloCorreo(getDiaT)){
                errorEnIngresar = true;
                errorMessage += "no se permite ingresar simbolos";
            }if(!test.soloCorreo(idT)){
                errorEnIngresar = true;
                errorMessage += "no se permite ingresar simbolos";
            }if(!test.soloCorreo(observacionT)){
                errorEnIngresar = true;
                errorMessage += "no se permite ingresar simbolos";
            }if(!test.soloCorreo(anioT)){
                errorEnIngresar = true;
                errorMessage += "no se permite ingresar simbolos";
            }
                try {
                    day = Integer.parseInt(diaT);
                    month = Integer.parseInt(mesT);
                    year = Integer.parseInt(anioT);
                    doses = Integer.parseInt(getDiaT);
                    if(day > 31 || day < 1){
                        errorEnIngresar = true;
                        errorMessage += "Favor ingresar un dia que existe\n";
                    }
                    if(month > 12 || month < 1){
                        errorEnIngresar = true;
                        errorMessage += "Favor ingresar un mes que existe\n";
                    }
                }catch (NumberFormatException e) {
                    errorEnIngresar = true;
                    errorMessage += "Favor ingresar números donde se le indica\n";
                }

                //END ERROR HANLDING
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                public void onResponse(String response) {
            try {
                JSONObject jsonResponse = new JSONObject(response);
                boolean success = jsonResponse.getBoolean("success"); //in the php file
                System.out.println("boolean =" + success);
                if (success){
                    Toast.makeText(PrescribirMedicinas.this, "Medicine Agregada",
                            Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(PrescribirMedicinas.this, MainActivity.class);
                    PrescribirMedicinas.this.startActivity(intent);
                    //Intent intent = new Intent(Login.this, MainActivity.class);

                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(PrescribirMedicinas.this);
                    builder.setMessage("Agregar Failed").setNegativeButton("Retry", null)
                            .create()
                            .show();
                    Toast.makeText(PrescribirMedicinas.this, "No se logro agregar medicina!",
                            Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                System.out.println("ERROR: No se logró conectarse a la base de datos");
            }

        }
                };
                //System.out.println("Prescribir, antes");
                if(!errorEnIngresar) {
                    day = day+doses;
                    if(day > 30){
                        day = day % 30;
                        month += 1;
                    }if(month > 12){
                        month = month % 12;
                        year++;
                    }
                    fechaFinal = ""+year+"-"+month+"-"+day;
                    //System.out.println("\n"+"LA FECHA INICIAL: "+fechaParaMandar);
                    //System.out.println("LA FECHA FINAL: "+fechaFinal);
                    AgregarMedicinaRequest medRequest = new AgregarMedicinaRequest(enfermedadT, recetaT, cantidadT,
                            tiempoT, getDiaT, observacionT, idT, fechaParaMandar, fechaFinal, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(PrescribirMedicinas.this);
                    queue.add(medRequest);
                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(PrescribirMedicinas.this);
                    builder.setMessage(errorMessage).setNegativeButton("Retry", null)
                            .create()
                            .show();
                }

                break;
        }
    }
    private void fillDropdown(String[] given){
        for (int i=0; i< given.length-1 ;i++) {
            System.out.println("String["+i+"] = " + given[i]);
        }

        items = new ArrayList<String>();
        for(int i = 0; i < given.length-1; i++){
            items.add(given[i]);
        }
        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinMed.setAdapter(adapter);
    }
    private String[] deJasonModifier3000(String noobString){
        int prePos = 0;
        int counterMas = 0;
        String[] proPwnString = new String[counter+1];
        //System.out.println("noobString> " + noobString);
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
