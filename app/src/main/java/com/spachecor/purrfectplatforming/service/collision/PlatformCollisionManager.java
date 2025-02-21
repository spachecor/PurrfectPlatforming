package com.spachecor.purrfectplatforming.service.collision;

import com.spachecor.purrfectplatforming.gameobject.character.Character;
import com.spachecor.purrfectplatforming.gameobject.platform.Platform;

import java.util.List;

/**
 * Clase que gestiona las colisiones de las plataformas
 * @author Selene
 * @version 1.0
 */
public class PlatformCollisionManager extends CollisionManager{

    /**
     * Funcion que gestiona la accion que sucedera si hubiese una colision entre una Platform y
     * un Character dependiendo del tipo de Platform que sea
     * @param platforms La lista de Platforms del nivel
     * @param character El Character que podría tener una colision con cualquiera de las Platforms
     */
    public static void managingCollisionsPlatformsCharacters(List<Platform> platforms, Character character){
        //recorremos las plataformas para detectar colision
        for (Platform platform:platforms){
            if(CollisionManager.isThereACollision(platform, character)){
                //plataforma solida
                if(platform.getPLATFORM_TYPE()== Platform.PlatformType.SOLID){
                    PlatformCollisionManager.resolveCollisionSolidPlatformCharacter(platform, character);
                }else {//plataforma semisolida
                    PlatformCollisionManager.resolveCollisionSemiSolidPlatformCharacter(platform, character);
                }
            }
        }
    }

    /**
     * Funcion que define como se resuelve una colision entre una Platform de tipo SemiSolid y un
     * Character
     * @param platform La Platform tipo SemiSolid con la que el Character tiene la colision
     * @param character El Character implicado en la colision
     */
    protected static void resolveCollisionSemiSolidPlatformCharacter(Platform platform, Character character) {
        //verificamos si el personaje está cayendo
        if(character.getVelocityY() > 0){
            //verificamos si el personaje está por encima de la plataforma y colisionando con ella
            if(character.getRECT_CONTAINER().bottom > platform.getPosicionY() &&
                    character.getRECT_CONTAINER().top < platform.getPosicionY() &&
                    character.getRECT_CONTAINER().right > platform.getPosicionX() &&
                    character.getRECT_CONTAINER().left < platform.getPosicionX() + platform.getWidth()) {

                //colocamos al personaje justo encima de la plataforma
                character.setPosicionY(platform.getPosicionY() - character.getHeight());
                character.setVelocityY(0); //detenemos la caída
                character.setJumping(false); //permitimos un nuevo salto

                //actualizamos el rectángulo de colisión del personaje
                character.getRECT_CONTAINER().set(
                        character.getPosicionX(),
                        character.getPosicionY(),
                        character.getPosicionX() + character.getWidth(),
                        character.getPosicionY() + character.getHeight()
                );
            }
        }
    }

    /**
     * Funcion que define como se resuelve una colision entre una Platform de tipo Solid y un
     * Character
     * @param platform La Platform tipo Solid con la que el Character tiene la colision
     * @param character El Character implicado en la colision
     */
    protected static void resolveCollisionSolidPlatformCharacter(Platform platform, Character character) {
        //calculamos las distancias de solapamiento en cada dirección
        int overlapLeft = character.getRECT_CONTAINER().right - platform.getRECT_CONTAINER().left;
        int overlapRight = platform.getRECT_CONTAINER().right - character.getRECT_CONTAINER().left;
        int overlapTop = character.getRECT_CONTAINER().bottom - platform.getRECT_CONTAINER().top;
        int overlapBottom = platform.getRECT_CONTAINER().bottom - character.getRECT_CONTAINER().top;

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
        character.getRECT_CONTAINER().set(
                character.getPosicionX(),
                character.getPosicionY(),
                character.getPosicionX() + character.getWidth(),
                character.getPosicionY() + character.getHeight()
        );
    }
}
