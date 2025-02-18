package com.spachecor.purrfectplatforming.gameobject.character;

import android.content.Context;

public class Enemy extends Character{

    public Enemy(Context context, Integer posicionX, Integer posicionY, Integer width, Integer height, int[] spriteResources, Integer walkingVelocity) {
        super(context, posicionX, posicionY, width, height, spriteResources, walkingVelocity);
    }
}
