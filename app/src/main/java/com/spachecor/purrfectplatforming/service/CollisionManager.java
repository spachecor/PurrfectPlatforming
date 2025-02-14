package com.spachecor.purrfectplatforming.service;

import com.spachecor.purrfectplatforming.gameobject.personaje.Character;

public class CollisionManager {
    public static void lowerCollision(int lowerLimit, Character character){
        if(character.getPosicionY()+ character.getHeight()>lowerLimit){
            character.setPosicionY(lowerLimit-character.getHeight());
            character.setVelocityY(0);
            character.setJumping(false);
            //todo revisar si eliminar siguiente linea
            character.setNewPosition(character.getPosicionX(), character.getPosicionY());
        }
    }
}
