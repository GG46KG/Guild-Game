package com.mygdx.game.world;


import com.badlogic.gdx.graphics.Color;

public enum TileType {
    FOREST ("Forest", new Color(.34f, .49f, .192f, 1)),
    MOUNTAIN ("mountain", new Color(.686f, .58f, .364f,1));

    public final String spriteName;
    public final Color spriteColor;

    TileType(String spriteName, Color color){
        this.spriteColor = color;
        this.spriteName = spriteName;
    }
}
