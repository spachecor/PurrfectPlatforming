package com.spachecor.purrfectplatforming.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.spachecor.purrfectplatforming.R;
import com.google.android.gms.ads.MobileAds;
import com.spachecor.purrfectplatforming.activity.service.AdBannerService;
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
        cargarPartidaButton.setOnClickListener(v-> this.startActivity(intent));
        //ANUNCIO
        MobileAds.initialize(this, initializationStatus -> {});
        FrameLayout frameLayoutContenedor = this.findViewById(R.id.contenedor);
        AdBannerService adBannerService = new AdBannerService(this);
        adBannerService.setUpAdvView(frameLayoutContenedor);
    }
}
