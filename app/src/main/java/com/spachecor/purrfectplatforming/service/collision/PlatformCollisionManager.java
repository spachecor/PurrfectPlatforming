package com.spachecor.purrfectplatforming.service.collision;

import com.spachecor.purrfectplatforming.gameobject.character.Character;
import com.spachecor.purrfectplatforming.gameobject.platform.Platform;

import java.util.List;

public class PlatformCollisionManager extends CollisionManager{

    public static void managingCollisionsPlatformsCharacters(List<Platform> platforms, Character character){
        //recorremos las plataformas para detectar colision
        for (Platform platform:platforms){
            if(CollisionManager.isThereACollision(platform, character)){
                //plataforma solida
                if(platform.getPlatformType()== Platform.PlatformType.SOLID){
                    PlatformCollisionManager.resolveCollisionSolidPlatformCharacter(platform, character);
                }else {//plataforma semisolida
                    PlatformCollisionManager.resolveCollisionSemiSolidPlatformCharacter(platform, character);
                }
            }
        }
    }

    protected static void resolveCollisionSemiSolidPlatformCharacter(Platform platform, Character character) {
        //verificamos si el personaje está cayendo
        if(character.getVelocityY() > 0){
            //verificamos si el personaje está por encima de la plataforma y colisionando con ella
            if(character.getRectContainer().bottom > platform.getPosicionY() &&
                    character.getRectContainer().top < platform.getPosicionY() &&
                    character.getRectContainer().right > platform.getPosicionX() &&
                    character.getRectContainer().left < platform.getPosicionX() + platform.getWidth()) {

                //colocamos al personaje justo encima de la plataforma
                character.setPosicionY(platform.getPosicionY() - character.getHeight());
                character.setVelocityY(0); //detenemos la caída
                character.setJumping(false); //permitimos un nuevo salto

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


    protected static void resolveCollisionSolidPlatformCharacter(Platform platform, Character character) {
        //calculamos las distancias de solapamiento en cada dirección
        int overlapLeft = character.getRectContainer().right - platform.getRectContainer().left;
        int overlapRight = platform.getRectContainer().right - character.getRectContainer().left;
        int overlapTop = character.getRectContainer().bottom - platform.getRectContainer().top;
        int overlapBottom = platform.getRectContainer().bottom - character.getRectContainer().top;

        //determinamos el menor solapamiento para encontrar la colisión más cercana
        int minOverlap = Math.min(Math.min(overlapLeft, overlapRight), Math.min(overlapTop, overlapBottom));

        //colisión superior (cayendo sobre la plataforma)
        if (minOverlap == overlapTop && character.getVelocityY() > 0) {
            character.setPosicionY(platform.getPosicionY() - character.getHeight());
            character.setVelocityY(0);
            character.setJumping(false);
        }
        //colisión inferior (golpeando la parte inferior de la plataforma)
        else if (minOverlap == overlapBottom && character.getVelocityY() < 0) {
            character.setPosicionY(platform.getPosicionY() + platform.getHeight());
            character.setVelocityY(0);
        }
        //colisión lateral izquierda (chocando con el lado izquierdo de la plataforma)
        else if (minOverlap == overlapLeft) {
            character.setPosicionX(platform.getPosicionX() - character.getWidth());
        }
        //colisión lateral derecha (chocando con el lado derecho de la plataforma)
        else if (minOverlap == overlapRight) {
            character.setPosicionX(platform.getPosicionX() + platform.getWidth());
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
