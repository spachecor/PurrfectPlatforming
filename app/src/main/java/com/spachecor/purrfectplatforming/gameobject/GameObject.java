package com.spachecor.purrfectplatforming.gameobject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.spachecor.purrfectplatforming.service.SpriteManager;

/**
 * Clase abstracta "padre" GameObject, que es la clase de la que hereda cualquier objeto del juego
 * @author Selene
 * @version 1.0
 */
public abstract class GameObject {
    private Integer posicionX;
    private Integer posicionY;
    private Integer width;
    private Integer height;
    private Bitmap[] spriteFrames;
    private int currentFrame;
    private long lastFrameChangeTime;
    /**
     * Constructor del objeto GameObject
     * @param context El contexto de la aplicación
     * @param posicionX La posición en el eje X del objeto
     * @param posicionY La posición en el eje Y del objeto
     * @param width El ancho del objeto
     * @param height El alto del objeto
     * @param spriteResources El array de los IDs de recursos(drawables) que se utilizarán para la animación
     */
    protected GameObject(Context context, Integer posicionX, Integer posicionY, Integer width, Integer height, int[] spriteResources){
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.width = width;
        this.height = height;
        this.spriteFrames = SpriteManager.bitmapCreator(context, width, height, spriteResources);
        this.currentFrame = 0;
        this.lastFrameChangeTime = 0L;
    }

    public Integer getPosicionX() {
        return posicionX;
    }

    public void setPosicionX(Integer posicionX) {
        this.posicionX = posicionX;
    }

    public Integer getPosicionY() {
        return posicionY;
    }

    public void setPosicionY(Integer posicionY) {
        this.posicionY = posicionY;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Bitmap[] getSpriteFrames() {
        return spriteFrames;
    }

    public void setSpriteFrames(Bitmap[] spriteFrames) {
        this.spriteFrames = spriteFrames;
    }

    public int getCurrentFrame() {
        return currentFrame;
    }

    public void setCurrentFrame(int currentFrame) {
        this.currentFrame = currentFrame;
    }

    public long getLastFrameChangeTime() {
        return lastFrameChangeTime;
    }

    public void setLastFrameChangeTime(long lastFrameChangeTime) {
        this.lastFrameChangeTime = lastFrameChangeTime;
    }

    public synchronized void draw(Canvas canvas){
        canvas.drawBitmap(this.spriteFrames[this.currentFrame], this.posicionX, this.posicionY, null);
    }
}
