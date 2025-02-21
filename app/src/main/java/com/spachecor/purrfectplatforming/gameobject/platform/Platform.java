package com.spachecor.purrfectplatforming.gameobject.platform;

import android.content.Context;

import com.spachecor.purrfectplatforming.gameobject.GameObject;

/**
 * Clase Platform que se encarga de definir el comportamiento y las propiedades de las plataformas,
 * sobre las cuales estarán enemigos, jugador y trofeo
 * @author Selene
 * @version 1.0
 */
public class Platform extends GameObject {
    /**
     * Enum PlatformType que define las constantes de los tipos de plataformas que pueden haber
     */
    public enum PlatformType{
        SOLID,
        SEMISOLID
    }

    private final PlatformType PLATFORM_TYPE;

    /**
     * Constructor del objeto del tipo Platform
     * @param context El contexto de la aplicación
     * @param posicionX La posición en el eje X del objeto
     * @param posicionY La posición en el eje Y del objeto
     * @param width El ancho del objeto
     * @param height El alto del objeto
     * @param spriteResources El array de los IDs de recursos(drawables) que se utilizarán para la animación
     * @param platformType El tipo de plataforma que se crea
     */
    public Platform(Context context, Integer posicionX, Integer posicionY, Integer width, Integer height, int[] spriteResources, PlatformType platformType) {
        super(context, posicionX, posicionY, width, height, spriteResources);
        this.PLATFORM_TYPE = platformType;
    }

    public PlatformType getPLATFORM_TYPE() {
        return PLATFORM_TYPE;
    }
}
