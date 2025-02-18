package com.spachecor.purrfectplatforming.levelgenerator;

import com.spachecor.purrfectplatforming.gameobject.Scenery;
import com.spachecor.purrfectplatforming.gameobject.Trophy;
import com.spachecor.purrfectplatforming.gameobject.character.Enemy;
import com.spachecor.purrfectplatforming.gameobject.character.Gamer;
import com.spachecor.purrfectplatforming.gameobject.platform.Platform;

import java.util.List;

public class Level {
    private Scenery scenery;
    private Gamer gamer;
    private List<Platform> platforms;
    private List<Enemy> enemies;
    private Trophy trophy;

    public Level(Scenery scenery, Gamer gamer, List<Platform> platforms, List<Enemy> enemies, Trophy trophy) {
        this.scenery = scenery;
        this.gamer = gamer;
        this.platforms = platforms;
        this.enemies = enemies;
        this.trophy = trophy;
    }

    public Gamer getGamer() {
        return gamer;
    }

    public void setGamer(Gamer gamer) {
        this.gamer = gamer;
    }

    public Scenery getScenery() {
        return scenery;
    }

    public void setScenery(Scenery scenery) {
        this.scenery = scenery;
    }

    public List<Platform> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<Platform> platforms) {
        this.platforms = platforms;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(List<Enemy> enemies) {
        this.enemies = enemies;
    }

    public Trophy getTrophy() {
        return trophy;
    }

    public void setTrophy(Trophy trophy) {
        this.trophy = trophy;
    }
}
