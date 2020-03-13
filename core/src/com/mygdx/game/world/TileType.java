package com.mygdx.game.world;


import com.badlogic.gdx.graphics.Color;

public enum TileType {
    FOREST ("Forest", new Color(.34f, .49f, .192f, 1)),
    MOUNTAIN ("mountain", new Color(.686f, .58f, .364f,1)),
    WATER ("Waves", new Color(0x113ea7ff)),
    DESERT ("Waves", new Color(0xe5dc8aff)),
    PEAK ("Peak", new Color(0xffffffff)),
    VOLCANO("Peak", new Color(0x591212ff)),
    PLAINS ("GentleHills", new Color(0xa6d22dff));

    public final String spriteName;
    public final Color spriteColor;

    TileType(String spriteName, Color color){
        this.spriteColor = color;
        this.spriteName = spriteName;
    }
}
