package com.spachecor.purrfectplatforming.gameobject;


import android.content.Context;

/**
 * Clase Trophy que define el comportamiento y las propiedades del Trofeo, el cual deberá ser obtenido
 * por el jugador antes de que lo alcance el enemigo
 * @author Selene
 * @version 1.0
 */
public class Trophy extends GameObject {

    /**
     * Constructor del objeto Trophy
     * @param context El contexto de la aplicación
     * @param posicionX La posición en el eje X del objeto
     * @param posicionY La posición en el eje Y del objeto
     * @param width El ancho del objeto
     * @param height El alto del objeto
     * @param spriteResources El array de los IDs de recursos(drawables) que se utilizarán para la animación
     */
    public Trophy(Context context, Integer posicionX, Integer posicionY, Integer width, Integer height, int[] spriteResources) {
        super(context, posicionX, posicionY, width, height, spriteResources);
    }
}
