package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.views.ActiveView;
import com.mygdx.game.views.MapView;

public class GameCoordinator extends ApplicationAdapter implements InputProcessor {
	SpriteBatch batch;
	TextureAtlas mapSprites;

	private MapView mapView;
	private GameData gameData;

	private ActiveView activeView = ActiveView.Map;

	@Override
	public void create () {
	    gameData = new GameData();
		batch = new SpriteBatch();
		mapView = new MapView( new TextureAtlas("sprites.txt"));
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if(activeView == ActiveView.Map) {
            mapView.render(gameData.getMap());
            gameData.getMap().getCursor().adjustCursor();
            gameData.getMap().advanceGameTimer();
        }
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		mapSprites.dispose();
	}

    @Override
    public boolean keyDown(int keycode) {
        if(activeView == ActiveView.Map){
            return gameData.getMap().handleInput(keycode);
        }else{
            return false;
        }
    }

    @Override
    public boolean keyUp(int keycode) { return false; }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
