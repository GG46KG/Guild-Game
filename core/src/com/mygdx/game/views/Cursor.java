package com.mygdx.game.views;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class Cursor{
    public final boolean IS_LIST_CURSOR;
    public final boolean RESET_ON_VIEW_CHANGE;

    private int x = 0;
    private int y = 0;

    private int xLimit;
    private int yLimit;

    public Cursor(boolean isList, boolean resetOnViewChange, int xLimit, int yLimit){
        IS_LIST_CURSOR = isList;
        RESET_ON_VIEW_CHANGE = resetOnViewChange;
        this.xLimit = xLimit;
        this.yLimit = yLimit;
    }

    public Cursor(boolean isList, boolean resetOnViewChange, int maxX){
        this(isList, resetOnViewChange, maxX, 0);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean adjustCursorForInput(int keycode) {
        if(keycode == Input.Keys.LEFT && x > 0 && !IS_LIST_CURSOR){
            x--;
        }else if(keycode == Input.Keys.RIGHT && x < xLimit - 1 && !IS_LIST_CURSOR){
            x++;
        }else if(keycode == Input.Keys.DOWN && y > 0){
            y--;
        }else if(keycode == Input.Keys.UP && y < yLimit - 1){
            y++;
        }else{
            return false;
        }
        return true;
    }
}
