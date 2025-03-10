package com.spachecor.purrfectplatforming.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.spachecor.purrfectplatforming.R;

public class GameOverActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if(!MainActivity.soundEffectsService.isMenuMusicActivated())MainActivity.soundEffectsService.toggleMenuMusic();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_over_view);
        Button volverIntentarlo, volverMenu;
        volverIntentarlo = this.findViewById(R.id.btnNuevoIntento);
        volverMenu = this.findViewById(R.id.btnVolverAlMenu);
        volverIntentarlo.setOnClickListener(v->{
            Bundle bundle = this.getIntent().getExtras();
            Intent intent = new Intent(GameOverActivity.this, GameActivity.class);
            intent.putExtra("level", bundle.getInt("level"));
            this.startActivity(intent);
        });
        volverMenu.setOnClickListener(v->{
            this.startActivity(new Intent(GameOverActivity.this, LevelsActivity.class));
        });
    }
}
