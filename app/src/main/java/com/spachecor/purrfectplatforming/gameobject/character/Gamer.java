package com.spachecor.purrfectplatforming.gameobject.character;

import android.content.Context;
import android.graphics.Bitmap;

import com.spachecor.purrfectplatforming.service.SpriteManager;

/**
 * Clase Gamer que define el comportamiento y las propiedades del jugador, el cual debe obtener el
 * trofeo sin que el enemigo lo alcance
 * @author Selene
 * @version 1.0
 */
public class Gamer extends Character {

    private final Integer JUMP_FORCE;

    //sprites precargados
    private final Bitmap[] GORDI_SENTADA;
    private final Bitmap[] GORDI_CAMINANDO_IZQ;
    private final Bitmap[] GORDI_CAMINANDO_DCHA;

    /**
     * Constructor del objeto Gamer
     * @param context El contexto de la aplicación
     * @param posicionX La posición en el eje X del objeto
     * @param posicionY La posición en el eje Y del objeto
     * @param width El ancho del objeto
     * @param height El alto del objeto
     * @param spriteResources El array de los IDs de recursos(drawables) que se utilizarán para la animación
     * @param jumpForce La fuerza de salto que tiene el Gamer
     * @param walkingVelocity Velocidad a la que camina el personaje
     */
    public Gamer(Context context, Integer posicionX, Integer posicionY, Integer width, Integer height, int[] spriteResources, Integer jumpForce, Integer walkingVelocity) {
        super(context, posicionX, posicionY, width, height, spriteResources, walkingVelocity);
        this.JUMP_FORCE = jumpForce;
        this.setJumping(false);
        this.GORDI_SENTADA = SpriteManager.bitmapCreator(
                context,
                width,
                height,
                SpriteManager.Sprite.GORDI_SENTADA.getVALORES()
        );
        this.GORDI_CAMINANDO_IZQ = SpriteManager.bitmapCreator(
                context,
                150,
                height,
                SpriteManager.Sprite.GORDI_CAMINANDO_IZQ.getVALORES()
        );
        this.GORDI_CAMINANDO_DCHA = SpriteManager.bitmapCreator(
                context,
                150,
                height,
                SpriteManager.Sprite.GORDI_CAMINANDO_DCHA.getVALORES()
        );
    }

    public Bitmap[] getGORDI_SENTADA() {
        return GORDI_SENTADA;
    }

    public Bitmap[] getGORDI_CAMINANDO_IZQ() {
        return GORDI_CAMINANDO_IZQ;
    }

    public Bitmap[] getGORDI_CAMINANDO_DCHA() {
        return GORDI_CAMINANDO_DCHA;
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
        this.setPosicionX(this.getPosicionX()-super.getWALKING_VELOCITY());
    }
    /**
     * Funcion que determina que el personaje se mueva hacia la derecha
     */
    public void rightMove(){
        this.setPosicionX(this.getPosicionX()+super.getWALKING_VELOCITY());
    }
}
