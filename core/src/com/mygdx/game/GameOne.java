package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.views.MapView;

public class GameOne extends ApplicationAdapter {
	SpriteBatch batch;
	TextureAtlas mapSprites;

	private MapView mapView;
	private GameData gameData;

	@Override
	public void create () {
	    gameData = new GameData();
		batch = new SpriteBatch();
		mapSprites = new TextureAtlas("sprites.txt");
		mapView = new MapView(mapSprites.createSprites());
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		mapView.render(gameData.getMap(), 22, 22);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		mapSprites.dispose();
	}
}
