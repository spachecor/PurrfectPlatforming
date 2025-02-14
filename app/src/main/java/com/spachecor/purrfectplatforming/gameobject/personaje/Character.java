package com.spachecor.purrfectplatforming.gameobject.personaje;

import android.content.Context;

import com.spachecor.purrfectplatforming.gameobject.GameObject;

public abstract class Character extends GameObject{
    /**
     * Constructor del objeto Personaje
     * @param context El contexto de la aplicación
     * @param posicionX La posición en el eje X del objeto
     * @param posicionY La posición en el eje Y del objeto
     * @param width El ancho del objeto
     * @param height El alto del objeto
     * @param spriteResources El array de los IDs de recursos(drawables) que se utilizarán para la animación
     */
    protected Character(Context context, Integer posicionX, Integer posicionY, Integer width, Integer height, int[] spriteResources) {
        super(context, posicionX, posicionY, width, height, spriteResources);
    }
}
