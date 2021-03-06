package com.mygdx.game.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.util.Cursor;

import java.util.ArrayList;
import java.util.Random;

public class WorldMap {
    private static final float TIME_STEP_INTERVAL_S = 1.5f;
    private static float timeStepGlobalTimer = TIME_STEP_INTERVAL_S;

    private Tile[][] map;
    private ArrayList<Creature> creatures;

    private static final int DEFAULT_LENGTH = 100;
    private static final int DEFAULT_HEIGHT = 60;
    Random randomizer = new Random();

    private Cursor cursor;

    public WorldMap (int length, int height){
        map = new Tile[length][height];
        generateNewMap();
        cursor = new Cursor(false, false, length, height);
        creatures = new ArrayList<>();
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
                    map[x][y] = new Tile(TileType.FOREST);
                }else{
                    map[x][y] = new Tile(TileType.MOUNTAIN);
                }
            }
        }
    }

    public boolean handleInput(int keycode){
        if(keycode == Input.Keys.B){
            map[cursor.getX()][cursor.getY()].addFeature(new GuildHall());
            return true;
        }
        return false;
    }

    public void addCreatureAtCoordinates(Creature creature, int x, int y){

    }

    public void advanceGameTimer() {
        if(timeStepGlobalTimer >= 0){
            timeStepGlobalTimer -= Gdx.graphics.getDeltaTime();
        }else{
            //process time based actions
            timeStepGlobalTimer = TIME_STEP_INTERVAL_S;
        }
    }
}
