package com.spachecor.purrfectplatforming;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private int progressStatus = 0;
    private final Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.initial_activity);

        progressBar = findViewById(R.id.progressBar);

        // Actualiza la barra de progreso cada 50ms hasta que llegue al 100%
        new Thread(() -> {
            while (progressStatus < 100) {
                progressStatus += 1; // Incrementa en 1% cada iteración
                handler.post(() -> progressBar.setProgress(progressStatus));
                try {
                    Thread.sleep(50); // Espera 50ms antes de la siguiente actualización
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // Cuando termine la barra, inicia la siguiente actividad
            handler.post(() -> {
                startActivity(new Intent(MainActivity.this, MenuActivity.class));
                finish();
            });

        }).start();
    }
}
