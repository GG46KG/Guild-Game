package com.mygdx.game.world;

import com.badlogic.gdx.graphics.Color;

public abstract class WorldFeature {
    protected boolean hasHealth = false;
    private final String spriteId;
    private final Color spriteColor;
    private final int spritePriority;

    public WorldFeature(String featureSpriteId, Color featureColor, int spritePriority){
        this.spriteId = featureSpriteId;
        this.spriteColor = featureColor;
        this.spritePriority = spritePriority;
    }

    public String getSpriteId() {
        return spriteId;
    }

    public Color getSpriteColor() {
        return spriteColor;
    }

    public int getSpritePriority() {
        return spritePriority;
    }
}
