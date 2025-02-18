package com.spachecor.purrfectplatforming.gameobject.character;

import android.content.Context;

public class Enemy extends Character{

    private Boolean mobile;
    private Integer minRangeOfMotion;
    private Integer maxRangeOfMotion;
    private int direction = 1; // 1: derecha, -1: izquierda

    public Enemy(Context context, Integer posicionX, Integer posicionY, Integer width, Integer height, int[] spriteResources, Integer walkingVelocity, boolean mobile, Integer minRangeOfMotion, Integer maxRangeOfMotion) {
        super(context, posicionX, posicionY, width, height, spriteResources, walkingVelocity);
        this.mobile = mobile;
        this.minRangeOfMotion = Math.min(minRangeOfMotion, maxRangeOfMotion);
        this.maxRangeOfMotion = Math.max(minRangeOfMotion, maxRangeOfMotion);
    }

    public Boolean getMobile() {
        return mobile;
    }

    public void setMobile(Boolean mobile) {
        this.mobile = mobile;
    }

    public Integer getMinRangeOfMotion() {
        return minRangeOfMotion;
    }

    public void setMinRangeOfMotion(Integer minRangeOfMotion) {
        this.minRangeOfMotion = minRangeOfMotion;
    }

    public Integer getMaxRangeOfMotion() {
        return maxRangeOfMotion;
    }

    public void setMaxRangeOfMotion(Integer maxRangeOfMotion) {
        this.maxRangeOfMotion = maxRangeOfMotion;
    }

    /**
     * Actualiza el movimiento del enemigo de forma incremental.
     * Se mueve en la dirección actual y, al alcanzar los límites, invierte la dirección.
     */
    public void updateMovement() {
        //si el enemigo no es móvil, no se actualiza el movimiento.
        if (!this.mobile) return;

        //calculamos la nueva posición en X
        int nuevaPosX = this.getPosicionX() + (this.getWalkingVelocity() * this.direction);
        this.setPosicionX(nuevaPosX);

        //si se alcanza o supera el límite derecho, se invierte la dirección y se ajusta la posición.
        if (nuevaPosX >= this.maxRangeOfMotion) {
            this.direction = -1;
            this.setPosicionX(this.maxRangeOfMotion);
        }
        //si se alcanza o supera el límite izquierdo, se invierte la dirección y se ajusta la posición.
        else if (nuevaPosX <= this.minRangeOfMotion) {
            this.direction = 1;
            this.setPosicionX(this.minRangeOfMotion);
        }
    }
}
