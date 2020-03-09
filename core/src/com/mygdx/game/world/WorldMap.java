package com.mygdx.game.world;

public class WorldMap {
    private Tile[][] map;

    private static final int DEFAULT_LENGTH = 100;
    private static final int DEFAULT_HEIGHT = 60;

    public WorldMap (int length, int height){
        map = new Tile[length][height];
        generateNewMap();
    }

    public WorldMap(){
        this(DEFAULT_LENGTH, DEFAULT_HEIGHT);
    }

    public Tile[][] getTiles(){
        return map;
    }

    private void generateNewMap() {
        //TODO map generation
        for(int x = 0; x < map.length; x++){
            for (int y = 0; y < map[x].length; y++) {
                map[x][y] = new Tile();
            }
        }
    }

}
