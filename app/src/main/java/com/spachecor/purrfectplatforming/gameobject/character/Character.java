package com.spachecor.purrfectplatforming.gameobject.character;

import android.content.Context;

import com.spachecor.purrfectplatforming.gameobject.GameObject;

public abstract class Character extends GameObject{

    private Integer velocityY;
    private Boolean isJumping;
    private Integer walkingVelocity;
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
        this.walkingVelocity = walkingVelocity;
        this.velocityY = 0;
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

    public Integer getWalkingVelocity() {
        return walkingVelocity;
    }

    public void setWalkingVelocity(Integer walkingVelocity) {
        this.walkingVelocity = walkingVelocity;
    }
}
