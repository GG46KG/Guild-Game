package com.mygdx.game.util;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class Cursor {
    private final static float CURSOR_MOVE_DELAY_S = .1f;

    public final boolean IS_LIST_CURSOR;
    public final boolean RESET_ON_VIEW_CHANGE;

    private int x = 0;
    private int y = 0;

    private int lastX = 0;
    private int lastY = 0;

    private float movementDelayTimer;

    private int xLimit;
    private int yLimit;

    public Cursor(boolean isList, boolean resetOnViewChange, int xLimit, int yLimit) {
        IS_LIST_CURSOR = isList;
        RESET_ON_VIEW_CHANGE = resetOnViewChange;
        this.xLimit = xLimit;
        this.yLimit = yLimit;
    }

    public Cursor(boolean isList, boolean resetOnViewChange, int maxX) {
        this(isList, resetOnViewChange, maxX, 0);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void adjustCursor() {
        if (movementDelayTimer > 0) {
            movementDelayTimer -= Gdx.graphics.getDeltaTime();
        } else {
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && x > 0 && !IS_LIST_CURSOR) {
                x--;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && x < xLimit - 1 && !IS_LIST_CURSOR) {
                x++;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && y > 0) {
                y--;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.UP) && y < yLimit - 1) {
                y++;
            }
        }
        if (lastX != x || lastY != y) {
            movementDelayTimer = CURSOR_MOVE_DELAY_S;
            lastY = y;
            lastX = x;
        }
    }
}