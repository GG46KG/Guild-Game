package com.mygdx.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.util.Cursor;
import com.mygdx.game.world.Tile;
import com.mygdx.game.world.WorldMap;
import javafx.util.Pair;

import java.util.HashMap;

public class MapView {

    private HashMap<String, Sprite> mapSprites;
    private SpriteBatch batch;

    private static final int DEFAULT_SCALE = 2;
    private static final int X = 0;
    private static final int Y = 1;
    private static final float BLINK_DELAY_S = .25f;

    private Pair<Integer, Integer> oldXYOfCursor = new Pair(0,0);
    private float blinkTimer = 0;
    private boolean isBlink = true;

    private final Float[] SPRITE_DIMENSIONS;
    private final int MAP_SCALE;
    private final int TILE_SPACING;
    private final int[] RENDER_REGION_DIMENSIONS;

    private int[] maxGridDimensions = new int[2];

    private Color clear;

    public MapView(TextureAtlas textureAtlas, int scale){
        createSpriteMap(textureAtlas);
        batch = new SpriteBatch();
        clear = new Color(batch.getColor());
        MAP_SCALE = scale;
        TILE_SPACING = 2 * (MAP_SCALE/2);
        SPRITE_DIMENSIONS = new Float[]{mapSprites.get("Forest").getWidth(), mapSprites.get("Forest").getHeight()};
        RENDER_REGION_DIMENSIONS = new int[]{(int) Math.floor(.75 * Gdx.graphics.getWidth()), Gdx.graphics.getHeight()};
        setGridDimensions();
    }

    private void createSpriteMap(TextureAtlas textureAtlas) {
        mapSprites = new HashMap<>();
        for(TextureAtlas.AtlasRegion region : textureAtlas.getRegions()){
            mapSprites.put(region.name, textureAtlas.createSprite(region.name));
        }
    }

    public MapView(TextureAtlas mapSprites){
        this(mapSprites, DEFAULT_SCALE);
    }

    private void setGridDimensions(){
        for (int i = 0; i < maxGridDimensions.length; i++) {
            maxGridDimensions[i] = (int) Math.floor(RENDER_REGION_DIMENSIONS[i] / (TILE_SPACING + SPRITE_DIMENSIONS[i] * MAP_SCALE));
        }
    }

    public void render(WorldMap map){
        blinkTimer += Gdx.graphics.getDeltaTime();

        Tile[][] mapToRender = map.getTiles();
        Cursor cursor = map.getCursor();
        setBlinkState(cursor);

        int[] xBounds = new int[2];
        int[] yBounds = new int[2];

        setDrawBounds(mapToRender, cursor, xBounds, yBounds);
        drawMap(cursor.getX(), cursor.getY(), mapToRender, xBounds, yBounds);
    }

    private void setDrawBounds(Tile[][] mapToRender, Cursor cursor, int[] xBounds, int[] yBounds) {
        xBounds[0] = Math.max(0, cursor.getX() - (int) Math.floor(maxGridDimensions[X] * 0.5));
        yBounds[0] = Math.max(0, cursor.getY() - (int) Math.floor(maxGridDimensions[Y] * 0.5));
        xBounds[1] = Math.min(mapToRender.length - 1, cursor.getX() + (int) Math.ceil(maxGridDimensions[X] * 0.5));
        yBounds[1] = Math.min(mapToRender[0].length - 1, cursor.getY() + (int) Math.ceil(maxGridDimensions[Y] * 0.5));

        adjustBoundsForMapBorder(mapToRender, xBounds, maxGridDimensions[X], mapToRender.length-1);
        adjustBoundsForMapBorder(mapToRender, yBounds, maxGridDimensions[Y], mapToRender[0].length-1);
    }

    private void setBlinkState(Cursor cursor) {
        if(oldXYOfCursor.getKey() != cursor.getX()
                || oldXYOfCursor.getValue() != cursor.getY()){
            isBlink = true;
            blinkTimer = 0;
            oldXYOfCursor = new Pair<>(cursor.getX(), cursor.getY());
        }else if(blinkTimer > BLINK_DELAY_S){
            isBlink = !isBlink;
            blinkTimer = 0;
        }
    }

    private void adjustBoundsForMapBorder(Tile[][] mapToRender, int[] bounds, int dimensionSize, int dimensionMax) {
        if(bounds[1] - bounds[0]  < dimensionSize){
            if(bounds[0] == 0){
                bounds[1] = dimensionSize;
            }else if(bounds[1] == dimensionMax){
                bounds[0] = dimensionMax - dimensionSize;
            }
        }
    }

    private void drawMap(int xCenter, int yCenter, Tile[][] worldMap, int[] xBounds, int[] yBounds) {
        float[] offsets = new float[2];
        batch.begin();
        for (int x = xBounds[0]; x < xBounds[1]; x++) {
            for (int y = yBounds[0]; y < yBounds[1]; y++) {
                offsets[X] = (x - xBounds[0]) * SPRITE_DIMENSIONS[X] * MAP_SCALE + TILE_SPACING * (x - xBounds[0] + 1);
                offsets[Y] = (y - yBounds[0]) * SPRITE_DIMENSIONS[Y] * MAP_SCALE + TILE_SPACING * (y - yBounds[0] + 1);
                if(x == xCenter && y == yCenter && isBlink){
                    batch.setColor(1,0,0,1);
                } else {
                    batch.setColor(worldMap[x][y].getTileType().spriteColor);
                }
                batch.draw(getTileSprite(worldMap[x][y]), offsets[X], offsets[Y],
                        SPRITE_DIMENSIONS[X] * MAP_SCALE, SPRITE_DIMENSIONS[Y] * MAP_SCALE);
                batch.setColor(clear);
            }
        }
        batch.end();
    }

    private Sprite getTileSprite(Tile tileToRender){
        return mapSprites.get(tileToRender.getTileType().spriteName);
    }

}
