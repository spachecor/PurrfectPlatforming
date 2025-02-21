package com.spachecor.purrfectplatforming.service.collision;

import com.spachecor.purrfectplatforming.gameobject.Trophy;
import com.spachecor.purrfectplatforming.gameobject.character.Enemy;
import com.spachecor.purrfectplatforming.gameobject.character.Gamer;

/**
 * Clase que gestiona las colisiones de los personajes
 * @author Selene
 * @version 1.0
 */
public class CharacterCollisionManager extends CollisionManager{
    /**
     * Funcion que conprueba si existe colision entre un Gamer y un Trophy
     * @param gamer El Gamer que está jugando el nivel
     * @param trophy El Trophy que haria que el Gamer pudiese ganar
     * @return Devuelve true si el Gamer toca el Trophy y false si no hay colision
     */
    public static boolean managingCollisionGamerTrophy(Gamer gamer, Trophy trophy){
        return CollisionManager.isThereACollision(gamer, trophy);
    }

    /**
     * Funcion que comprueba si hay colision entre un Gamer y un Enemy
     * @param gamer El Gamer que está jugando el nivel
     * @param enemy El Enemy contra el que podría tener la colision
     * @return Devuelve true si hay una colision entre ellos y false si no la hay
     */
    public static boolean managingCollisionGamerEnemy(Gamer gamer, Enemy enemy){
        return CollisionManager.isThereACollision(gamer, enemy);
    }
}
