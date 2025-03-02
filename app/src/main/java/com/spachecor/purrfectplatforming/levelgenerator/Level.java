package com.spachecor.purrfectplatforming.levelgenerator;

import com.spachecor.purrfectplatforming.gameobject.Scenery;
import com.spachecor.purrfectplatforming.gameobject.Trophy;
import com.spachecor.purrfectplatforming.gameobject.character.Enemy;
import com.spachecor.purrfectplatforming.gameobject.character.Gamer;
import com.spachecor.purrfectplatforming.gameobject.platform.Platform;

import java.util.List;

/**
 * Clase Level, que define la estructura, comportamiento y propiedades del nivel
 * @author Selene
 * @version 1.0
 */
public class Level {
    private final Scenery SCENERY;
    private final Gamer GAMER;
    private final List<Platform> PLATFORMS;
    private final List<Enemy> ENEMIES;
    private final Trophy TROPHY;
    private Boolean victory;

    /**
     * Constructor del objeto Level
     * @param scenery El escenario sobre el que ocurre el nivel
     * @param gamer El jugador que juega el nivel
     * @param platforms Las plataformas del nivel
     * @param enemies Los enemigos del nivel
     * @param trophy El trofeo del nivel
     */
    public Level(Scenery scenery, Gamer gamer, List<Platform> platforms, List<Enemy> enemies, Trophy trophy) {
        this.SCENERY = scenery;
        this.GAMER = gamer;
        this.PLATFORMS = platforms;
        this.ENEMIES = enemies;
        this.TROPHY = trophy;
        this.victory = false;
    }

    public Gamer getGAMER() {
        return GAMER;
    }

    public Scenery getSCENERY() {
        return SCENERY;
    }

    public List<Platform> getPLATFORMS() {
        return PLATFORMS;
    }

    public List<Enemy> getENEMIES() {
        return ENEMIES;
    }

    public Trophy getTROPHY() {
        return TROPHY;
    }

    public Boolean getVictory() {
        return victory;
    }

    public void setVictory(Boolean victory) {
        this.victory = victory;
    }
}
