package com.spachecor.purrfectplatforming.gameobject.platform;

import android.content.Context;

import com.spachecor.purrfectplatforming.gameobject.GameObject;

public class Platform extends GameObject {

    public enum PlatformType{
        SOLID,
        SEMISOLID
    }

    private PlatformType platformType;

    public Platform(Context context, Integer posicionX, Integer posicionY, Integer width, Integer height, int[] spriteResources, PlatformType platformType) {
        super(context, posicionX, posicionY, width, height, spriteResources);
        this.platformType = platformType;
    }

    public PlatformType getPlatformType() {
        return platformType;
    }

    public void setPlatformType(PlatformType platformType) {
        this.platformType = platformType;
    }
}
