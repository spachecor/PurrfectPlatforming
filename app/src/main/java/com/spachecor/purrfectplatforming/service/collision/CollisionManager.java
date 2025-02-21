package com.spachecor.purrfectplatforming.service.collision;

import android.graphics.Rect;

import com.spachecor.purrfectplatforming.gameobject.GameObject;
import com.spachecor.purrfectplatforming.gameobject.character.Character;

/**
 * Clase gestora de las colisiones más básicas
 * @author Selene
 * @version 1.0
 */
public class CollisionManager {

    /**
     * Funcion que se encarga de detectar si dos objetos de tipo GameObject tienen colision entre si
     * @param gameObjectOne El GameObject primero
     * @param gameObjectTwo El GameObject segundo
     * @return Devuelve true si hay colision y false si no la hay
     */
    protected static boolean isThereACollision(GameObject gameObjectOne, GameObject gameObjectTwo){
        return Rect.intersects(gameObjectOne.getRECT_CONTAINER(), gameObjectTwo.getRECT_CONTAINER());
    }

    /**
     * Funcion que se encarga de gestionar la colision de un personaje con el "suelo" del nivel
     * @param lowerLimit El punto más bajo del nivel
     * @param character El personaje que esta dentro del nivel
     */
    public static void lowerCollision(int lowerLimit, Character character){
        if(character.getPosicionY()+ character.getHeight()>lowerLimit){
            character.setPosicionY(lowerLimit-character.getHeight());
            character.setVelocityY(0);
            character.setJumping(false);
        }
    }

    /**
     * Funcion que se encarga de gestionar la colision de un personaje con los laterales del nivel
     * @param screenWidth El ancho del nivel
     * @param character El personaje que esta dentro del nivel
     */
    public static void horizontalCollision(int screenWidth, Character character) {
        //verificamos la colisión con el lado izquierdo
        if (character.getPosicionX() < 0) {
            character.setPosicionX(0);
        }
        //verificamos la colisión con el lado derecho
        else if (character.getPosicionX() + character.getWidth() > screenWidth) {
            character.setPosicionX(screenWidth - character.getWidth());
        }
        //actualizamos el rectángulo de colisión del personaje
        character.getRECT_CONTAINER().set(
                character.getPosicionX(),
                character.getPosicionY(),
                character.getPosicionX() + character.getWidth(),
                character.getPosicionY() + character.getHeight()
        );
    }
}
