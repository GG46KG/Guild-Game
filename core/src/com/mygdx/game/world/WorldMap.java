package com.mygdx.game.world;

import com.mygdx.game.util.Cursor;

import java.util.Random;

public class WorldMap {
    private Tile[][] map;

    private static final int DEFAULT_LENGTH = 100;
    private static final int DEFAULT_HEIGHT = 60;
    Random randomizer = new Random();

    private Cursor cursor;

    public WorldMap (int length, int height){
        map = new Tile[length][height];
        generateNewMap();
        cursor = new Cursor(false, false, length, height);
    }

    public WorldMap(){
        this(DEFAULT_LENGTH, DEFAULT_HEIGHT);
    }

    public Tile[][] getTiles(){
        return map;
    }

    public Cursor getCursor() {
        return cursor;
    }

    private void generateNewMap() {
        //TODO map generation
        for(int x = 0; x < map.length; x++){
            for (int y = 0; y < map[x].length; y++) {
                if(randomizer.nextInt(100) < 70) {
                    map[x][y] = new Tile();
                }else{
                    map[x][y] = new Tile(TileType.MOUNTAIN);
                }
            }
        }
    }

    public boolean handleInput(int keycode){
        return false;
    }
}
