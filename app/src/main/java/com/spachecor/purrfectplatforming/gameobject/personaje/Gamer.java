package com.spachecor.purrfectplatforming.gameobject.personaje;

import android.content.Context;

public class Gamer extends Character {

    private Integer velocityY;
    private Integer jumpForce;
    private Integer walkingVelocity;

    /**
     * Constructor del objeto Personaje
     * @param context El contexto de la aplicación
     * @param posicionX La posición en el eje X del objeto
     * @param posicionY La posición en el eje Y del objeto
     * @param width El ancho del objeto
     * @param height El alto del objeto
     * @param spriteResources El array de los IDs de recursos(drawables) que se utilizarán para la animación
     */
    public Gamer(Context context, Integer posicionX, Integer posicionY, Integer width, Integer height, int[] spriteResources, Integer jumpForce, Integer walkingVelocity) {
        super(context, posicionX, posicionY, width, height, spriteResources);
        this.velocityY = 0;
        this.jumpForce = jumpForce;
        this.walkingVelocity = walkingVelocity;
    }
}
