package com.mygdx.game.world;

import java.util.ArrayList;

public class Tile {
    private TileType tileType;
    private ArrayList<WorldFeature> features;

    public Tile(TileType tileType) {
        this.tileType = tileType;
        features = new ArrayList<>();
    }

    public TileType getTileType() {
        return tileType;
    }

    public void addFeature(WorldFeature feature){
        features.add(feature);
    }

    public void removeFeature(WorldFeature feature){
        features.remove(feature);
    }

    public ArrayList<WorldFeature> getFeatures(){
        return features;
    }
}
