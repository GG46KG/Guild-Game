package com.mygdx.game;

import com.mygdx.game.world.WorldMap;

public class GameData {
    private WorldMap map;

    public GameData(WorldMap map){
        this.map = map;
    }

    public GameData(){
        this(new WorldMap());
    }

    public WorldMap getMap() {
        return map;
    }
}
