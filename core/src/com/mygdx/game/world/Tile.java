package com.mygdx.game.world;

public class Tile {
    private TileType tileType;

    public Tile(){
        tileType = TileType.FOREST;
    }

    public Tile(TileType tileType) { this.tileType = tileType;}

    public TileType getTileType() {
        return tileType;
    }
}
