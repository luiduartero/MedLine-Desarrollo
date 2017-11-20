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

public class MainActivity extends AppCompatActivity  {
    CircleMenu circleMenu;
    RelativeLayout mylogin;
    AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mylogin = (RelativeLayout) findViewById(R.id.mylogin);
        animationDrawable = (AnimationDrawable) mylogin.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(2500);
        animationDrawable.start();

/**
 * Menu circular
 */
        circleMenu = (CircleMenu) findViewById(R.id.circle_menu);
        circleMenu.setMainMenu(Color.parseColor("#407d79b3"), R.mipmap.icon_menu, R.mipmap.icon_cancel);
        circleMenu.addSubMenu(Color.parseColor("#407d79b3"), R.mipmap.salir)
                .addSubMenu(Color.parseColor("#FFFFFF"), R.mipmap.paciente)
                .addSubMenu(Color.parseColor("#407d79b3"), R.mipmap.nuevopaciente)
                .addSubMenu(Color.parseColor("#FFFFFF"), R.mipmap.appointment)
                .addSubMenu(Color.parseColor("#407d79b3"), R.mipmap.agregarmedicina)
                .addSubMenu(Color.parseColor("#FFFFFF"), R.mipmap.ayuda)
        ;
        circleMenu.setOnMenuSelectedListener(new OnMenuSelectedListener() {

            @Override
            public void onMenuSelected(int index) {
                switch (index) {
                    case 0:
                        Toast.makeText(MainActivity.this, "Cerrar Sesion", Toast.LENGTH_SHORT).show();
                        Intent s = new Intent(MainActivity.this, Login.class);
                        startActivity(s);
                        break;

                    case 1:
                        Toast.makeText(MainActivity.this, "Ver Pacientes", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(MainActivity.this, VerPacientesDoctor.class);
                        startActivity(i);
                        break;
                    case 2:
                        Toast.makeText(MainActivity.this, "Agregar Paciente", Toast.LENGTH_SHORT).show();
                        Intent c = new Intent(MainActivity.this, NuevoPaciente.class);
                        startActivity(c);
                        break;
                    case 3:
                        Toast.makeText(MainActivity.this, "Citas del dia", Toast.LENGTH_SHORT).show();
                        Intent o = new Intent(MainActivity.this, CitasDoctor.class);
                        startActivity(o);
                        break;
                    case 4:
                        Toast.makeText(MainActivity.this, "Prescripcion de Medicinas", Toast.LENGTH_SHORT).show();
                        Intent j = new Intent(MainActivity.this, PrescribirMedicinas.class);
                        startActivity(j);
                        break;
                    case 5:
                        Toast.makeText(MainActivity.this, "Informacion de Uso", Toast.LENGTH_SHORT).show();
                        Intent k = new Intent(MainActivity.this, LaAyuda.class);
                        startActivity(k);
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
