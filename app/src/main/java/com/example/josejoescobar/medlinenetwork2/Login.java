package com.example.josejoescobar.medlinenetwork2;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity implements View.OnClickListener {
    Button btnLogin;
    EditText etUsername, etPassword;
    TextView txtLinkRegistro;
    TextView errMessg;
    private VideoView mVideoView;
    boolean errorEnIngresar = false;
    String newPass = "";
    Modificacion test = new Modificacion();
    RelativeLayout mylogin;
    AnimationDrawable animationDrawable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        errMessg = (TextView) findViewById(R.id.errorMessage);
        txtLinkRegistro = (TextView) findViewById(R.id.txtLinkRegistro);
        btnLogin.setOnClickListener(this);
        txtLinkRegistro.setOnClickListener(this);
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


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLogin:
                errMessg.clearComposingText();
                errorEnIngresar = false;
                final String email = etUsername.getText().toString();
                final String password = etPassword.getText().toString();
                //antiSQL
                if(!test.soloCorreo(email))
                   errorEnIngresar = true;
                if (!test.soloCorreo(password))
                    errorEnIngresar = true;
                newPass = test.encryptPass(password);

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");
                            if (success){
                                //get info de la base de datos
                                int idg = jsonResponse.getInt("id");
                                String nombre = jsonResponse.getString("nombre");
                                String username = jsonResponse.getString("username");
                                String emailg = jsonResponse.getString("email");
                                int typeg = jsonResponse.getInt("type");
                                if (typeg == 1) {
                                    //DOCTOR!
                                    //setup intent
                                    Intent intent = new Intent(Login.this, MainActivity.class);
                                    //send extra
                                    intent.putExtra("id", idg);
                                    intent.putExtra("nombre", nombre);
                                    intent.putExtra("username", username);
                                    intent.putExtra("email", emailg);
                                    intent.putExtra("type", typeg);
                                    //Change intent
                                    Login.this.startActivity(intent);

                                }else {
                                    //PACIENTE
                                    //setup intent
                                    Intent intent = new Intent(Login.this, MenuUsuario.class);
                                    //send extra
                                    intent.putExtra("id", idg);
                                    intent.putExtra("nombre", nombre);
                                    intent.putExtra("username", username);
                                    intent.putExtra("email", emailg);
                                    intent.putExtra("type", typeg);

                                    //Change intent
                                    Login.this.startActivity(intent);
                                }

                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                                builder.setMessage("Incorrecta contraseña o usuario").setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                            builder.setMessage("ERROR EN CONEXION AL INTERNET").setNegativeButton("Retry", null)
                                    .create()
                                    .show();
                        }
                    }
                };
                if(!errorEnIngresar) {
                    LoginRequest loginRequest = new LoginRequest(email, newPass, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(Login.this);
                    queue.add(loginRequest);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                    builder.setMessage("Favor solo ingresar letras y numeros").setNegativeButton("Retry", null)
                            .create()
                            .show();
                }
                break;

            case R.id.txtLinkRegistro:
                startActivity(new Intent(this, Registro.class));
                break;

        }
    }
    @Override
    public void onBackPressed(){

    }
}
