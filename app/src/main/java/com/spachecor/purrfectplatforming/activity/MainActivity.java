package com.spachecor.purrfectplatforming.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.spachecor.purrfectplatforming.R;
import com.spachecor.purrfectplatforming.activity.service.SoundEffectsService;

/**
 * Clase MainActivity, que es la Activity principal, que llama a la Splash Screen y a los 5
 * segundos llaman al MenuActivity, pasando de la pantalla de carga a la del menu principal
 * @author Selene
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {
    public static SoundEffectsService soundEffectsService;
    private ProgressBar progressBar;
    private int progressStatus = 0;
    private final Handler handler = new Handler(Looper.getMainLooper());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MainActivity.soundEffectsService = new SoundEffectsService(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.initial_activity);

        this.progressBar = this.findViewById(R.id.progressBar);

        //actualiza la barra de progreso cada 50ms hasta que llegue al 100% (5 seg)
        new Thread(() -> {
            while (this.progressStatus < 100) {
                this.progressStatus += 1; //incrementamos en 1% cada iteración
                this.handler.post(() -> this.progressBar.setProgress(this.progressStatus));
                try {
                    Thread.sleep(50); //esperamos 50ms antes de la siguiente actualización
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //cuando termine la barra, iniciamos la siguiente actividad
            this.handler.post(() -> {
                //this.startActivity(new Intent(MainActivity.this, MenuActivity.class));
                //BORRAR DESPUES
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                this.startActivity(intent);
                intent.putExtra("level", 0);
                startActivity(intent);
                //BORRAR DEPSUES
                this.finish();
            });
        }).start();
    }
}
