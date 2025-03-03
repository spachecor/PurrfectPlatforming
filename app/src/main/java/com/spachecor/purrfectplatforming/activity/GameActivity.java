package com.spachecor.purrfectplatforming.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.spachecor.purrfectplatforming.levelgenerator.LevelConfigurator;
import com.spachecor.purrfectplatforming.view.GameView;

/**
 * Clase GameActivity, que se encarga de generar la GameView y gestionar el inicio y el fin del
 * nivel
 * @author Selene
 * @version 1.0
 */
public class GameActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getIntent().getExtras();
        setContentView(new GameView(this, LevelConfigurator.getLevel(this, bundle.getInt("level"))));
    }
}
