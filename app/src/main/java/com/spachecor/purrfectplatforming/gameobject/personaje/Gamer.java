package com.spachecor.purrfectplatforming.gameobject.personaje;

import android.content.Context;

public class Gamer extends Character {

    private final Integer JUMP_FORCE;
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
        this.setVelocityY(0);
        this.JUMP_FORCE = jumpForce;
        this.walkingVelocity = walkingVelocity;
        this.setJumping(false);
    }

    /**
     * Inicia el salto del personaje.
     * Si el personaje no está saltando, se aplica la fuerza de salto
     * y se marca como en estado de salto.
     */
    public void jump() {
        if (!this.getJumping()) {
            this.setVelocityY(this.JUMP_FORCE);
            this.setJumping(true);
        }
    }
    /**
     * Funcion que determina que el personaje se mueva hacia la izquierda
     */
    public void leftMove(){
        this.setPosicionX(this.getPosicionX()-this.walkingVelocity);
    }
    /**
     * Funcion que determina que el personaje se mueva hacia la derecha
     */
    public void rightMove(){
        this.setPosicionX(this.getPosicionX()+this.walkingVelocity);
    }

}
