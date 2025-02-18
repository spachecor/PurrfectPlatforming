package com.spachecor.purrfectplatforming.service.collision;

import android.graphics.Rect;

import com.spachecor.purrfectplatforming.gameobject.GameObject;
import com.spachecor.purrfectplatforming.gameobject.character.Character;
import com.spachecor.purrfectplatforming.gameobject.platform.Platform;

import java.util.List;

public class CollisionManager {

    protected static boolean isThereACollision(GameObject gameObjectOne, GameObject gameObjectTwo){
        return Rect.intersects(gameObjectOne.getRectContainer(), gameObjectTwo.getRectContainer());
    }

    public static void lowerCollision(int lowerLimit, Character character){
        if(character.getPosicionY()+ character.getHeight()>lowerLimit){
            character.setPosicionY(lowerLimit-character.getHeight());
            character.setVelocityY(0);
            character.setJumping(false);
        }
    }

    public static void horizontalCollision(int screenWidth, Character... characters) {
        for(Character character:characters){
            //verificamos la colisión con el lado izquierdo
            if (character.getPosicionX() < 0) {
                character.setPosicionX(0);
            }
            //verificamos la colisión con el lado derecho
            else if (character.getPosicionX() + character.getWidth() > screenWidth) {
                character.setPosicionX(screenWidth - character.getWidth());
            }
            //actualizamos el rectángulo de colisión del personaje
            character.getRectContainer().set(
                    character.getPosicionX(),
                    character.getPosicionY(),
                    character.getPosicionX() + character.getWidth(),
                    character.getPosicionY() + character.getHeight()
            );
        }
    }
}
