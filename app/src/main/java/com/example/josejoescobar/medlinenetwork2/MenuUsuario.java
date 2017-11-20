package com.example.josejoescobar.medlinenetwork2;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;
import com.hitomi.cmlibrary.OnMenuStatusChangeListener;

/**
 * Created by Marlo on 8/15/2017.
 */

public class MenuUsuario extends AppCompatActivity {
    CircleMenu circleMenu;
    TextView ponerID;
    String nombre, username, email, type;
    int id;
    Intent cambio;
    RelativeLayout mylogin;
    AnimationDrawable animationDrawable;
    private String[] mItemTexts = new String[] {};
    //private String[] mItemTexts = new String[] {"Salir","Historial Medico", "Medicinas", "Pedir Medicinas", "Doctor"};
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_usuario);
        Intent intent = getIntent();
        mylogin = (RelativeLayout) findViewById(R.id.mylogin);
        animationDrawable = (AnimationDrawable) mylogin.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(2500);
        animationDrawable.start();
        nombre = intent.getStringExtra("nombre");
        username = intent.getStringExtra("username");
        email = intent.getStringExtra("email");
        type = "Paciente";
        id = intent.getIntExtra("id", 0);
        ponerID = (TextView) findViewById(R.id.bienvenida);
        ponerID.setText("Bienvenido " + nombre +" \nID: "+ id);

        circleMenu = (CircleMenu) findViewById(R.id.circle_menu);
        circleMenu.setMainMenu(Color.parseColor("#407d79b3"), R.mipmap.icon_menu, R.mipmap.icon_cancel);
        circleMenu.addSubMenu(Color.parseColor("#ffffff"), R.mipmap.salir)
                .addSubMenu(Color.parseColor("#407d79b3"), R.mipmap.historialmedico)
                .addSubMenu(Color.parseColor("#407d79b3"), R.mipmap.medicinas)
                .addSubMenu(Color.parseColor("#407d79b3"), R.mipmap.pedirmedicina)
                .addSubMenu(Color.parseColor("#407d79b3"), R.mipmap.doctor)
                .addSubMenu(Color.parseColor("#407d79b3"), R.mipmap.map)
                .addSubMenu(Color.parseColor("#407d79b3"), R.mipmap.ayuda);

        circleMenu.setOnMenuSelectedListener(new OnMenuSelectedListener() {

            @Override
            public void onMenuSelected(int index) {
                switch (index) {
                    case 0:
                        Toast.makeText(MenuUsuario.this, "Ha seleccionado, Cerrar Sesion", Toast.LENGTH_SHORT).show();
                        cambio = new Intent(MenuUsuario.this, Login.class);
                        startActivity(cambio);
                        break;
                    case 1:
                        Toast.makeText(MenuUsuario.this, "Ha seleccionado, Ver Historial de Medicamentos", Toast.LENGTH_SHORT).show();
                        cambio = new Intent(MenuUsuario.this, HistorialMedico.class);
                        cambio.putExtra("id", id);
                        cambio.putExtra("nombre", nombre);
                        cambio.putExtra("username", username);
                        cambio.putExtra("email", email);
                        startActivity(cambio);
                        break;
                    case 2:
                        Toast.makeText(MenuUsuario.this, "Ha seleccionado, Ver Medicinas", Toast.LENGTH_SHORT).show();
                        cambio = new Intent(MenuUsuario.this, Medicinas.class);
                        cambio.putExtra("id", id);
                        cambio.putExtra("nombre", nombre);
                        cambio.putExtra("username", username);
                        cambio.putExtra("email", email);
                        startActivity(cambio);
                        break;
                    case 3:
                        Toast.makeText(MenuUsuario.this, "Ha seleccionado, Solicitud de Medicina", Toast.LENGTH_SHORT).show();
                        cambio = new Intent(MenuUsuario.this, SolicitarMedicinas.class);
                        cambio.putExtra("id", id);
                        cambio.putExtra("nombre", nombre);
                        cambio.putExtra("username", username);
                        cambio.putExtra("email", email);
                        startActivity(cambio);
                        break;
                    case 4:
                        Toast.makeText(MenuUsuario.this, "Ha seleccionado, Ver Doctores", Toast.LENGTH_SHORT).show();
                        cambio = new Intent(MenuUsuario.this, DoctoresUsuario.class);
                        cambio.putExtra("id", id);
                        cambio.putExtra("nombre", nombre);
                        cambio.putExtra("username", username);
                        cambio.putExtra("email", email);
                        startActivity(cambio);
                        break;
                    case 5:
                        Toast.makeText(MenuUsuario.this, "Mapa", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(MenuUsuario.this, MapsActivity.class); // VerPacientesDoctores----> MAPAS
                        startActivity(i);
                        break;
                }
            }
        }   );

    }

    @Override
    public void onBackPressed() {
        if (circleMenu.isOpened())
            circleMenu.closeMenu();
    }
}
