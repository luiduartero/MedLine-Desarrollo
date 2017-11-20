package com.example.josejoescobar.medlinenetwork2;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreen extends Activity {

    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        iv = (ImageView)findViewById(R.id.splash);
        Animation myanim = AnimationUtils.loadAnimation(this,R.anim.mytransition);
        iv.startAnimation(myanim);
        final Intent i = new Intent(this,Login.class);

        Thread timer = new Thread(){
            public void run (){
                try {
                    sleep(5000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                finally {
                    startActivity(i);
                    finish();

                }
            }
        };
            timer.start();
    }
}
