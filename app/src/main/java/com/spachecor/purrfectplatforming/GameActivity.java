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
import com.spachecor.purrfectplatforming.service.SpriteManager;
import com.spachecor.purrfectplatforming.view.GameView;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<Platform> platforms = new ArrayList<>();
        platforms.add(new Platform(this, 500, 500, 100, 15, SpriteManager.Sprite.SOLID_PLATFORM.getVALORES(), Platform.PlatformType.SOLID));
        platforms.add(new Platform(this, 750, 500, 100, 10, SpriteManager.Sprite.SEMISOLID_PLATFORM.getVALORES(), Platform.PlatformType.SEMISOLID));
        List<Enemy> enemies = new ArrayList<>();
        enemies.add(new Enemy(this, 500, 500, 100, 100, SpriteManager.Sprite.TOILET_PAPER.getVALORES(), 3, true, 500, 700));
        Level level = new Level(
                new Scenery(
                        2,
                        R.drawable.fondococina
                ),
                new Gamer(
                        this,
                        200,
                        500,
                        100,
                        100,
                        SpriteManager.Sprite.GORDI_SENTADA.getVALORES(),
                        -30,
                        15
                ),
                platforms,
                enemies,
                new Trophy(this, 500, 355, 100, 150, new int[]{R.drawable.trofeo})
        );
        setContentView(new GameView(this, level));
    }
}
