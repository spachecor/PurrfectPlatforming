package com.spachecor.purrfectplatforming.levelgenerator;

import com.spachecor.purrfectplatforming.gameobject.Scenery;
import com.spachecor.purrfectplatforming.gameobject.character.Gamer;
import com.spachecor.purrfectplatforming.gameobject.platform.Platform;

import java.util.List;

public class Level {
    private Scenery scenery;
    private Gamer gamer;
    private List<Platform> platforms;

    public Level(Scenery scenery, Gamer gamer, List<Platform> platforms) {
        this.scenery = scenery;
        this.gamer = gamer;
        this.platforms = platforms;
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
}
