package com.spachecor.purrfectplatforming;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LevelsActivity extends AppCompatActivity {
    private Intent intent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        ImageButton levelZero, levelOne, levelTwo, levelThree, levelFour;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levels_view);
        levelZero = this.findViewById(R.id.levelZeroButton);
        levelOne = this.findViewById(R.id.levelOneButton);
        levelTwo = this.findViewById(R.id.levelTwoButton);
        levelThree = this.findViewById(R.id.levelThreeButton);
        levelFour = this.findViewById(R.id.levelFourButton);

        this.intent = new Intent(LevelsActivity.this, GameActivity.class);

        levelZero.setOnClickListener(v->{
            System.out.println("LEVEL 0");
            this.goToGame(0);
        });
        levelOne.setOnClickListener(v->{
            System.out.println("LEVEL 1");
            this.goToGame(1);
        });
        levelTwo.setOnClickListener(v->{
            System.out.println("LEVEL 2");
            this.goToGame(2);
        });
        levelThree.setOnClickListener(v->{
            System.out.println("LEVEL 3");
            this.goToGame(3);
        });
        levelFour.setOnClickListener(v->{
            System.out.println("LEVEL 4");
            this.goToGame(4);
        });

    }
    private void goToGame(int level){
        this.intent.putExtra("level", level);
        startActivity(intent);
    }
}
