package com.spachecor.purrfectplatforming.service;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.spachecor.purrfectplatforming.gameobject.GameObject;

/**
 * Clase SpriteManager que se encarga de gestionar los sprites en el juego
 * @author Selene
 * @version 1.0
 */
public class SpriteManager {
    private static final Integer FRAME_LENGTH_IN_MILLISECONDS = 100;
    /**
     * Funcion que se encarga de crear un array de bitmaps para generar el sprite del objeto.
     * @param context El contexto de la aplicación
     * @param width El ancho del objeto
     * @param height El alto del objeto
     * @param resourceIds El array de los IDs de recursos(drawables) que se utilizarán para la animación
     * @return El array de Bitmaps con la nueva animación
     */
    public static Bitmap[] bitmapCreator(Context context, int width, int height, int[] resourceIds){
        Bitmap[] bitmap = new Bitmap[resourceIds.length];
        for(int i = 0;i<resourceIds.length;i++){
            bitmap[i] = BitmapFactory.decodeResource(context.getResources(), resourceIds[i]);
            bitmap[i] = Bitmap.createScaledBitmap(bitmap[i], width, height, false);
        }
        return bitmap;
    }

    /**
     * Funcion sincronizada (apta para hilos) que se encarga de cambiar la animación del objeto.
     * @param gameObject El objeto al que aplicar la nueva animación.
     * @param newFrames La nueva animación
     */
    public static synchronized void setCurrentAnimation(GameObject gameObject, Bitmap[] newFrames){
        gameObject.setSpriteFrames(newFrames);
    }

    public static synchronized void controlSpriteMovement(GameObject gameObject){
        long currentTime = System.currentTimeMillis();
        if(currentTime > gameObject.getLastFrameChangeTime() + SpriteManager.FRAME_LENGTH_IN_MILLISECONDS &&
                gameObject.getSpriteFrames() != null &&
                gameObject.getSpriteFrames().length > 0){

            // Actualiza el frame actual
            int nextFrame = (gameObject.getCurrentFrame() + 1) % gameObject.getSpriteFrames().length;
            gameObject.setCurrentFrame(nextFrame);

            // Actualiza el tiempo de cambio de frame
            gameObject.setLastFrameChangeTime(currentTime);
        }
    }
}
