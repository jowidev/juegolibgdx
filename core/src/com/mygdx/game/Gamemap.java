package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Gamemap  extends ApplicationAdapter  {
	private OrthographicCamera camera;
	private TiledMap map;
	private OrthogonalTiledMapRenderer mapRenderer;
	private FitViewport viewport;
	private SpriteBatch batch;
	private MapProperties props;

	float w;

	@Override
	public void create() {
		w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		batch = new SpriteBatch();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, (w/h) * 320, 384);
		camera.update();

		// Load the tilemap from the .tmx file
		TiledMap map = new TmxMapLoader().load("tilemap.tmx");

		// Create the map renderer
		mapRenderer = new OrthogonalTiledMapRenderer(map);

		viewport = new FitViewport(800, 800);
	}
	private void handleInput(float w) {

		if (Gdx.input.isKeyPressed(Input.Keys.LEFT) ) {
			camera.translate(-5, 0, 0);
			if (camera.position.x<=camera.viewportWidth / 2) {
				camera.translate(5, 0, 0);
			}
		} else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {		//NO PUEDE SER LO HICE SIN CHATGPT OJO ROCKSTAR
			camera.translate(5, 0, 0);
			if (camera.position.x>=w - camera.viewportWidth / 2) {
				camera.translate(-5, 0, 0);
			}
		}
	}

	public void update() {

		handleInput(w);
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

		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		batch.end();
		update();
		camera.update();
		mapRenderer.setView(camera);
		mapRenderer.render();
	}

	@Override
	public void dispose() {
		// Dispose of resources when the game is closed
		map.dispose();
		mapRenderer.dispose();
	}


}