package com.spachecor.purrfectplatforming.gameobject.personaje;

import android.content.Context;
import android.graphics.Bitmap;

import com.spachecor.purrfectplatforming.service.SpriteManager;

public class Gamer extends Character {

    private final Integer JUMP_FORCE;
    private Integer walkingVelocity;

    //sprites precargados
    private final Bitmap[] GORDI_SENTADA;
    private final Bitmap[] GORDI_CAMINANDO_IZQ;
    private final Bitmap[] GORDI_CAMINANDO_DCHA;

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

    public Bitmap[] getGORDI_SENTADA() {
        return GORDI_SENTADA;
    }

    public Bitmap[] getGORDI_CAMINANDO_IZQ() {
        return GORDI_CAMINANDO_IZQ;
    }

    public Bitmap[] getGORDI_CAMINANDO_DCHA() {
        return GORDI_CAMINANDO_DCHA;
    }
}
