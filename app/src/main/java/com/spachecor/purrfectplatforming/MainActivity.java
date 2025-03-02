package com.spachecor.purrfectplatforming;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Clase MainActivity, que es la Activity principal, que llama a la Splash Screen y a los 5
 * segundos llaman al MenuActivity, pasando de la pantalla de carga a la del menu principal
 * @author Selene
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private int progressStatus = 0;
    private final Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.initial_activity);

        progressBar = findViewById(R.id.progressBar);

        //actualiza la barra de progreso cada 50ms hasta que llegue al 100% (5 seg)
        new Thread(() -> {
            while (progressStatus < 100) {
                progressStatus += 1; //incrementamos en 1% cada iteración
                handler.post(() -> progressBar.setProgress(progressStatus));
                try {
                    Thread.sleep(50); //esperamos 50ms antes de la siguiente actualización
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //cuando termine la barra, iniciamos la siguiente actividad
            handler.post(() -> {
                startActivity(new Intent(MainActivity.this, LevelsActivity.class));
                finish();
            });
        }).start();
    }
}
