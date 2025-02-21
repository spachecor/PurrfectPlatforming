package com.spachecor.purrfectplatforming.gameobject.character;

import android.content.Context;

import com.spachecor.purrfectplatforming.gameobject.GameObject;

/**
 * Clase abstracta que define el comportamiento de todos los personajes
 * @author Selene
 * @version 1.0
 */
public abstract class Character extends GameObject{

    private Integer velocityY;
    private Boolean isJumping;
    private final Integer WALKING_VELOCITY;
    /**
     * Constructor del objeto Personaje
     * @param context El contexto de la aplicación
     * @param posicionX La posición en el eje X del objeto
     * @param posicionY La posición en el eje Y del objeto
     * @param width El ancho del objeto
     * @param height El alto del objeto
     * @param spriteResources El array de los IDs de recursos(drawables) que se utilizarán para la animación
     * @param walkingVelocity Velocidad a la que camina el personaje
     */
    protected Character(Context context, Integer posicionX, Integer posicionY, Integer width, Integer height, int[] spriteResources, int walkingVelocity) {
        super(context, posicionX, posicionY, width, height, spriteResources);
        this.WALKING_VELOCITY = walkingVelocity;
        this.velocityY = 0;
    }

    public Integer getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(Integer velocityY) {
        this.velocityY = velocityY;
    }

    public Boolean getJumping() {
        return isJumping;
    }

    public void setJumping(Boolean jumping) {
        isJumping = jumping;
    }

    public Integer getWALKING_VELOCITY() {
        return WALKING_VELOCITY;
    }

    /**
     * Funcion que aplica la gravedad al eje x del personaje
     * @param gravity La gravedad
     */
    public void applyGravity(int gravity){
        //aplicamos la velocidad vertical actual a la posición
        this.setPosicionY(this.getPosicionY() + this.getVelocityY());
        //aumentamos la velocidad vertical con la gravedad
        this.setVelocityY(this.getVelocityY() + gravity);
    }
}
