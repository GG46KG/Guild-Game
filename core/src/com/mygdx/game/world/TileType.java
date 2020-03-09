package com.mygdx.game.world;

public enum TileType {
    FOREST (0),
    MOUNTAIN (1);
    public final int spriteId;

    TileType(int spriteId){
        this.spriteId = spriteId;
    }
}
