package com.mygdx.game.world;

public class Tile {
    private TileType tileType;

    public Tile(){
        tileType = TileType.FOREST;
    }

    public TileType getTileType() {
        return tileType;
    }
}
