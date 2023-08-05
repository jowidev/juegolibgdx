package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.*;

public class Gamemap extends ApplicationAdapter {
	private OrthographicCamera camera;
	private TiledMap tiledMap;
	private OrthogonalTiledMapRenderer mapRenderer;
	private Viewport viewport;
	private SpriteBatch batch;
	float screenWidth;
	float screenHeight; //chabon no entiendo una pija

	@Override
	public void create() {
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, screenWidth, screenHeight);

		// Load the tilemap from the .tmx file
		TmxMapLoader mapLoader = new TmxMapLoader();
		tiledMap = mapLoader.load("tilemap.tmx");

		// Create the map renderer
		mapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

		viewport = new FitViewport(800, 600, camera);
	}

	private void handleInput() {
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			camera.translate(-5, 0, 0);

		} else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			camera.translate(5, 0, 0);

		}
	}
	public void update() {
		handleInput();
		camera.update();
	}

	public void resize(int width, int height) {
		viewport.update(width, height, true);
	}

	@Override
	public void render() {
		// Clear the screen
		Gdx.gl.glClearColor(.8f, .8f, .8f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.setProjectionMatrix(camera.combined);


		batch.end();
		update();
		camera.update();
		mapRenderer.setView(camera);
		mapRenderer.render();
	}

	@Override
	public void dispose() {
		// Dispose of resources when the game is closed
		tiledMap.dispose();
		mapRenderer.dispose();
	}
}
