package com.spachecor.purrfectplatforming;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Clase MenuActivity, que se encarga de gestionar el menu inicial del juego
 * @author Selene
 * @version 1.0
 */
public class MenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button nuevaPartidaButton, cargarPartidaButton, ajustesButton, salirButton;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);
        nuevaPartidaButton = this.findViewById(R.id.btnNuevaPartida);
        nuevaPartidaButton.setOnClickListener(v->{

        });
    }
}
