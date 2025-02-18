package com.spachecor.purrfectplatforming.service.collision;

import com.spachecor.purrfectplatforming.gameobject.Trophy;
import com.spachecor.purrfectplatforming.gameobject.character.Enemy;
import com.spachecor.purrfectplatforming.gameobject.character.Gamer;

public class CharacterCollisionManager extends CollisionManager{
    public static int managingCollisionGamerTrophy(Gamer gamer, Trophy trophy){
        return (CollisionManager.isThereACollision(gamer, trophy))?1:0;
    }
    public static int managingCollisionGamerEnemy(Gamer gamer, Enemy enemy){
        return (CollisionManager.isThereACollision(gamer, enemy))?-1:0;
    }
}
