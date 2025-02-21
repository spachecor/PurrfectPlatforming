package com.spachecor.purrfectplatforming;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.spachecor.purrfectplatforming.gameobject.Scenery;
import com.spachecor.purrfectplatforming.gameobject.Trophy;
import com.spachecor.purrfectplatforming.gameobject.character.Enemy;
import com.spachecor.purrfectplatforming.gameobject.character.Gamer;
import com.spachecor.purrfectplatforming.gameobject.platform.Platform;
import com.spachecor.purrfectplatforming.levelgenerator.Level;
import com.spachecor.purrfectplatforming.levelgenerator.LevelConfigurator;
import com.spachecor.purrfectplatforming.service.SpriteManager;
import com.spachecor.purrfectplatforming.view.GameView;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GameView(this, LevelConfigurator.getLevel(this, 0)));
    }
}
