package com.example.josejoescobar.medlinenetwork2;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Registro extends AppCompatActivity implements View.OnClickListener {

    TextView txtLinkLogin;
    RadioButton appPaciente;
    RadioGroup rg;
    RadioButton rb;
    RadioButton appDoctor;
    Button btnRegistrarse;
    EditText editTNombre, editTUsuario, editTCorreo, editTPass, editTRePass;
    int type = 0;
    Modificacion modi = new Modificacion();
    boolean errorIngresando = false;
    String errir = "";
    String encryptPass;
    RelativeLayout mylogin;
    AnimationDrawable animationDrawable;
    private VideoView mVideoView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //La siguiente linea explica con que activity esta pareado
        setContentView(R.layout.activity_registro);
        editTNombre = (EditText) findViewById(R.id.editTNombre);
        editTCorreo = (EditText) findViewById(R.id.editTCorreo);
        editTUsuario = (EditText) findViewById(R.id.editTUsuario);
        editTPass = (EditText) findViewById(R.id.editTPass);
        editTRePass= (EditText) findViewById(R.id.editTRePass);
        btnRegistrarse = (Button) findViewById(R.id.btnRegistrarse);
        txtLinkLogin = (TextView) findViewById(R.id.txtLinkLogin);
        appPaciente = (RadioButton) findViewById(R.id.appPaciente);
        appDoctor = (RadioButton) findViewById(R.id.appDoctor);
        btnRegistrarse.setOnClickListener(this);
        txtLinkLogin.setOnClickListener(this);
        rg =(RadioGroup) findViewById(R.id.rgroup);
        mylogin = (RelativeLayout) findViewById(R.id.mylogin);
        animationDrawable = (AnimationDrawable) mylogin.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(2500);
        animationDrawable.start();

        mVideoView = (VideoView) findViewById(R.id.videoView);
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.medline);
        mVideoView.setVideoURI(uri);
        mVideoView.start();
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);
            }
        });
    }

    public void rbClick(View v){
        int radiobuttonID = rg.getCheckedRadioButtonId();
        rb = (RadioButton) findViewById(radiobuttonID);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnRegistrarse:
                errorIngresando = false;
                errir = "";

                final String email = editTCorreo.getText().toString();
                final String name = editTNombre.getText().toString();
                final String username = editTUsuario.getText().toString();
                final String password = editTPass.getText().toString();
                //ERROR HANDLING
                if(email == null || email.equalsIgnoreCase("")) {
                    errorIngresando = true;
                    errir += "Favor Ingresar Email\n";
                }if(name == null || name.equalsIgnoreCase("")) {
                errorIngresando = true;
                errir += "Favor Ingresar nombre\n";
            }if(username == null || username.equalsIgnoreCase("")) {
                errorIngresando = true;
                errir += "Favor ingresar usuario\n";
            }if(password == null || password.equalsIgnoreCase("")) {
                errorIngresando = true;
                errir += "Favor ingresar contraseña\n";
            }   if (!modi.soloCorreo(email)) {
                errorIngresando = true;
                errir += "solo letras, numeros, punto y @ en email\n";
            }
                if (!modi.soloCorreo(username)) {
                    errorIngresando = true;
                    errir += "solo letras y numeros en username\n";
                }
                if (!modi.soloCorreo(name)){
                    errorIngresando = true;
                    errir += "solo letras y numeros en nombre\n";
                }
                if (!modi.soloCorreo(password)){
                    errorIngresando = true;
                    errir += "solo letras y numeros en password\n";
                }if(!appPaciente.isChecked() && !appDoctor.isChecked()){
                errorIngresando = true;
                errir += "Favor ingresar si es paciente o doctor\n";
                 }//END ERROR HANDLING

                if (appPaciente.isChecked()){
                    type = 0;
                } else if (appDoctor.isChecked()){
                    type = 1;
                }
                encryptPass = modi.encryptPass(password);
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsopnResponse = new JSONObject(response);
                            boolean success = jsopnResponse.getBoolean("success");
                            if (success){
                                Toast.makeText(Registro.this, "Cuenta Creada", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Registro.this, Login.class);
                                Registro.this.startActivity(intent);
                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(Registro.this);
                                builder.setMessage("Favor Ingresar correo que no se haya utilizado").setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            System.out.println("no llego a despues");
                            Toast.makeText(Registro.this, "Error en JSON", Toast.LENGTH_SHORT).show();
                            System.out.println("error en JSON OBJECT RESPONSE");
                        }
                    }
                };
                if(!errorIngresando) {
                    RegisterRequest registerRequest = new RegisterRequest(name, username, email, type, encryptPass, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(Registro.this);
                    queue.add(registerRequest);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Registro.this);
                    builder.setMessage("Error: "+ errir).setNegativeButton("Retry", null)
                            .create()
                            .show();
                }
                break;

            case R.id.txtLinkLogin:
                startActivity(new Intent(this, Login.class));
                break;

        }


    }


}
