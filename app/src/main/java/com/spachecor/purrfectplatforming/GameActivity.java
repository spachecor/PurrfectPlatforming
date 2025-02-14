package com.spachecor.purrfectplatforming;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.spachecor.purrfectplatforming.gameobject.personaje.Gamer;
import com.spachecor.purrfectplatforming.service.SpriteManager;
import com.spachecor.purrfectplatforming.view.GameView;

public class GameActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(
                new GameView(
                        this,
                        new Gamer(
                                this,
                                200,
                                500,
                                100,
                                100,
                                SpriteManager.Sprite.GORDI_SENTADA.getVALORES(),
                                -30,
                                20
                        ),
                        2
                )
        );
    }
}
