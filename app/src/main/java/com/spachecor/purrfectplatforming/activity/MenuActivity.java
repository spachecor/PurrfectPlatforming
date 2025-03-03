package com.spachecor.purrfectplatforming.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.spachecor.purrfectplatforming.R;
import com.spachecor.purrfectplatforming.activity.service.LevelPassedService;

/**
 * Clase MenuActivity, que se encarga de gestionar el menu inicial del juego
 * @author Selene
 * @version 1.0
 */
public class MenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button nuevaPartidaButton, cargarPartidaButton, ajustesButton, salirButton;
        Intent intent = new Intent(MenuActivity.this, LevelsActivity.class);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);
        nuevaPartidaButton = this.findViewById(R.id.btnNuevaPartida);
        cargarPartidaButton = this.findViewById(R.id.btnCargar);
        nuevaPartidaButton.setOnClickListener(v->{
            LevelPassedService.resetLevels(this);
            this.startActivity(intent);
        });
        cargarPartidaButton.setOnClickListener(v->{
            intent.putExtra("level0", LevelPassedService.isLevelPassed(this, "level0"));
            intent.putExtra("level1", LevelPassedService.isLevelPassed(this, "level1"));
            intent.putExtra("level2", LevelPassedService.isLevelPassed(this, "level2"));
            intent.putExtra("level3", LevelPassedService.isLevelPassed(this, "level3"));
            intent.putExtra("level4", LevelPassedService.isLevelPassed(this, "level4"));
            this.startActivity(intent);
        });
    }
}
