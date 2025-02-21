package com.spachecor.purrfectplatforming.service;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.spachecor.purrfectplatforming.R;
import com.spachecor.purrfectplatforming.gameobject.GameObject;

/**
 * Clase SpriteManager que se encarga de gestionar los sprites en el juego
 * @author Selene
 * @version 1.0
 */
public class SpriteManager {

    /**
     * Enum que define las constantes que representan los diferentes sprites necesarios en el juego
     * @author Selene
     * @version 1.0
     */
    public enum Sprite{
        //CHARACTERS
        //GORDI
        GORDI_SENTADA(
                new int[]{
                        R.drawable.gordisentada1,
                        R.drawable.gordisentada1,
                        R.drawable.gordisentada1,
                        R.drawable.gordisentada2,
                        R.drawable.gordisentada2,
                        R.drawable.gordisentada2
                }
        ),
        GORDI_CAMINANDO_IZQ(
                new int[]{
                        R.drawable.gordicaminandoizq1,
                        R.drawable.gordicaminandoizq1,
                        R.drawable.gordicaminandoizq2,
                        R.drawable.gordicaminandoizq2,
                        R.drawable.gordicaminandoizq3,
                        R.drawable.gordicaminandoizq3
                }
        ),
        GORDI_CAMINANDO_DCHA(
                new int[]{
                        R.drawable.gordicaminandodcha1,
                        R.drawable.gordicaminandodcha1,
                        R.drawable.gordicaminandodcha2,
                        R.drawable.gordicaminandodcha2,
                        R.drawable.gordicaminandodcha3,
                        R.drawable.gordicaminandodcha3
                }
        ),
        //PLATFORMS
        SOLID_PLATFORM(
                new int[]{
                        R.drawable.solidplatformcocina
                }
        ),
        SEMISOLID_PLATFORM(
                new int[]{
                        R.drawable.semisolidplatformcocina
                }
        ),
        //ENEMIES
        TOILET_PAPER(
                new int[]{
                        R.drawable.enemigo
                }
        ),
        //TROPHYS
        TROPHY(
                new int[]{
                        R.drawable.trofeo
                }
        );
        private final int[] VALORES;
        Sprite(int[] valores){
            this.VALORES = valores;
        }

        public int[] getVALORES() {
            return VALORES;
        }
    }

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

    /**
     * Funcion que se encarga de actualizar la animacion del personaje segun sus fotogramas
     * @param gameObject El personaje a actualizar la animacion
     */
    public static synchronized void controlSpriteMovement(GameObject gameObject){
        long currentTime = System.currentTimeMillis();
        //si ha pasado el tiempo requerido entre fotogramas, cambia de fotograma
        if(currentTime > gameObject.getLastFrameChangeTime() + SpriteManager.FRAME_LENGTH_IN_MILLISECONDS &&
                gameObject.getSpriteFrames() != null &&
                gameObject.getSpriteFrames().length > 0){

            //actualiza el frame actual
            int nextFrame = (gameObject.getCurrentFrame() + 1) % gameObject.getSpriteFrames().length;
            gameObject.setCurrentFrame(nextFrame);

            //actualiza el tiempo de cambio de frame
            gameObject.setLastFrameChangeTime(currentTime);
        }
    }
}
