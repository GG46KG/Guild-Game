package com.mygdx.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.world.Tile;
import com.mygdx.game.world.WorldMap;

public class MapView{

    private Array<Sprite> mapSprites;
    private SpriteBatch batch;

    private static final int DEFAULT_SCALE = 4;
    private static final int X = 0;
    private static final int Y = 1;

    private final Float[] SPRITE_DIMENSIONS;
    private final int MAP_SCALE;
    private final int TILE_SPACING;
    private final int[] RENDER_REGION_DIMENSIONS;

    private int[] maxGridDimensions = new int[2];

    private Color clear;

    public MapView(Array<Sprite> mapSprites, int scale){
        this.mapSprites = mapSprites;
        batch = new SpriteBatch();
        clear = new Color(batch.getColor());
        MAP_SCALE = scale;
        TILE_SPACING = 2 * (MAP_SCALE/2);
        SPRITE_DIMENSIONS = new Float[]{mapSprites.first().getWidth(), mapSprites.first().getHeight()};
        RENDER_REGION_DIMENSIONS = new int[]{(int) Math.floor(.75 * Gdx.graphics.getWidth()), Gdx.graphics.getHeight()};
        setGridDimensions();
    }

    public MapView(Array<Sprite> mapSprites){
        this(mapSprites, DEFAULT_SCALE);
    }

    private void setGridDimensions(){
        for (int i = 0; i < maxGridDimensions.length; i++) {
            maxGridDimensions[i] = (int) Math.floor(RENDER_REGION_DIMENSIONS[i] / (TILE_SPACING + SPRITE_DIMENSIONS[i] * MAP_SCALE));
        }
    }

    public void render(WorldMap map, int xCenter, int yCenter){
        Tile[][] mapToRender = map.getTiles();

        int[] xBounds = new int[2];
        int[] yBounds = new int[2];
        xBounds[0] = Math.max(0, xCenter - (int) Math.floor(maxGridDimensions[X] * 0.5));
        yBounds[0] = Math.max(0, yCenter - (int) Math.floor(maxGridDimensions[Y] * 0.5));
        xBounds[1] = Math.min(mapToRender.length, xCenter + (int) Math.ceil(maxGridDimensions[X] * 0.5));
        yBounds[1] = Math.min(mapToRender[0].length, yCenter + (int) Math.ceil(maxGridDimensions[Y] * 0.5));

        adjustBoundsForMapBorder(mapToRender, xBounds, maxGridDimensions[X]);
        adjustBoundsForMapBorder(mapToRender, yBounds, maxGridDimensions[Y]);

        drawMap(xCenter, yCenter, mapToRender, xBounds, yBounds);
    }

    private void adjustBoundsForMapBorder(Tile[][] mapToRender, int[] bounds, int dimensionSize) {
        if(bounds[0] + bounds[1] < dimensionSize){
            if(bounds[0] == 0){
                bounds[1] = dimensionSize;
            }else if(bounds[1] == mapToRender.length){
                bounds[0] = mapToRender.length - dimensionSize;
            }
        }
    }

    private void drawMap(int xCenter, int yCenter, Tile[][] mapToRender, int[] xBounds, int[] yBounds) {
        float[] offsets = new float[2];
        batch.begin();
        for (int x = xBounds[0]; x < xBounds[1]; x++) {
            for (int y = yBounds[0]; y < yBounds[1]; y++) {
                offsets[X] = (x - xBounds[0]) * SPRITE_DIMENSIONS[X] * MAP_SCALE + TILE_SPACING * (x - xBounds[0] + 1);
                offsets[Y] = (y - yBounds[0]) * SPRITE_DIMENSIONS[Y] * MAP_SCALE + TILE_SPACING * (y - yBounds[0] + 1);
                if(x == xCenter && y == yCenter){
                    batch.setColor(1,0,0,1);
                }
                batch.draw(getTileSprite(mapToRender[x][y]), offsets[X], offsets[Y],
                        SPRITE_DIMENSIONS[X] * MAP_SCALE, SPRITE_DIMENSIONS[Y] * MAP_SCALE);
                batch.setColor(clear);
            }
        }
        batch.end();
    }

    //for testing only, remove when done
    public void render(WorldMap map){
        render(map, 0, 0);
    }

    private Sprite getTileSprite(Tile tileToRender){
        return mapSprites.get(tileToRender.getTileType().spriteId);
    }


}
