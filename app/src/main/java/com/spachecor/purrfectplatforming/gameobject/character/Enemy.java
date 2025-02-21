package com.spachecor.purrfectplatforming.gameobject.character;

import android.content.Context;

/**
 * Clase Enemy que define el comportamiento y las propiedades del enemigo, el cual tiene la capacidad
 * de matar al jugador
 * @author Selene
 * @version 1.0
 */
public class Enemy extends Character{

    private final Boolean MOBILE;
    private final Integer MIN_RANGE_OF_MOTION;
    private final Integer MAX_RANGE_OF_MOTION;
    private int direction = 1; //1: derecha, -1: izquierda

    /**
     * Constructor del objeto Enemy
     * @param context El contexto de la aplicación
     * @param posicionX La posición en el eje X del objeto
     * @param posicionY La posición en el eje Y del objeto
     * @param width El ancho del objeto
     * @param height El alto del objeto
     * @param spriteResources El array de los IDs de recursos(drawables) que se utilizarán para la animación
     * @param walkingVelocity Velocidad a la que camina el personaje
     * @param mobile Define si el enemigo se mueve o no
     * @param minRangeOfMotion La posicion minima a donde llega el enemigo
     * @param maxRangeOfMotion La posicion maxima a donde llega el enemigo
     */
    public Enemy(Context context, Integer posicionX, Integer posicionY, Integer width, Integer height, int[] spriteResources, Integer walkingVelocity, boolean mobile, Integer minRangeOfMotion, Integer maxRangeOfMotion) {
        super(context, posicionX, posicionY, width, height, spriteResources, walkingVelocity);
        this.MOBILE = mobile;
        this.MIN_RANGE_OF_MOTION = Math.min(minRangeOfMotion, maxRangeOfMotion);
        this.MAX_RANGE_OF_MOTION = Math.max(minRangeOfMotion, maxRangeOfMotion);
    }

    /**
     * Actualiza el movimiento del enemigo de forma incremental.
     * Se mueve en la dirección actual y, al alcanzar los límites, invierte la dirección.
     */
    public void updateMovement() {
        //si el enemigo no es móvil, no se actualiza el movimiento.
        if (!this.MOBILE) return;

        //calculamos la nueva posición en X
        int nuevaPosX = this.getPosicionX() + (this.getWALKING_VELOCITY() * this.direction);
        this.setPosicionX(nuevaPosX);

        //si se alcanza o supera el límite derecho, se invierte la dirección y se ajusta la posición.
        if (nuevaPosX >= this.MAX_RANGE_OF_MOTION) {
            this.direction = -1;
            this.setPosicionX(this.MAX_RANGE_OF_MOTION);
        }
        //si se alcanza o supera el límite izquierdo, se invierte la dirección y se ajusta la posición.
        else if (nuevaPosX <= this.MIN_RANGE_OF_MOTION) {
            this.direction = 1;
            this.setPosicionX(this.MIN_RANGE_OF_MOTION);
        }
    }
}
