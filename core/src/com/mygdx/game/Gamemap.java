package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Gamemap  extends Game {
	private TiledMap map;
	private OrthogonalTiledMapRenderer mapRenderer;
	private final Vector2 mousepos = new Vector2();
	public FitViewport viewport;
	public SpriteBatch batch;
	public Camera camera;
	public Assets assets;
	public Plant plant;
	public Boulder boulder; 
	

	@Override
	public void create() {
		this.assets = new Assets();
		
		batch = new SpriteBatch();

		// Load the tilemap from the .tmx file
		this.map = new TmxMapLoader().load("tilemap.tmx");

		// Create the map renderer
		mapRenderer = new OrthogonalTiledMapRenderer(map, constants.pixeltotile, batch);

		viewport = new FitViewport(12, 12);
		camera = viewport.getCamera();
		camera.position.set(constants.GAME_WORLD_WIDTH_tile/2, constants.GAME_WORLD_HEIGHT_tile/2, 0);
		this.boulder = new Boulder(this);
	}
	private void handleInput() {
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT) ) {
			camera.translate(-.5f, 0, 0);
			if (camera.position.x<=camera.viewportWidth / 2) {
				camera.translate(.5f, 0, 0);
			}
		} else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {		//NO PUEDE SER LO HICE SIN CHATGPT OJO ROCKSTAR
			camera.translate(.5f, 0, 0);
			if (camera.position.x>=constants.GAME_WORLD_WIDTH_tile - camera.viewportWidth / 2) {
				camera.translate(-.5f, 0, 0);
			}
		}
		if(Gdx.input.isKeyPressed(Input.Keys.NUM_1)) {
			this.plant = new Plant(this);
		}
	}



	public void resize(int w, int h) {
		viewport.update(w, h);
	}

	@Override
	public void render() {
		// Clear the screen
		Gdx.gl.glClearColor(.8f, .8f, .8f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		handleInput();
		viewport.apply();
		batch.setProjectionMatrix(camera.combined);
		mapRenderer.setView(camera.combined, 0,0 ,21, 12);
		mapRenderer.render();
		if(plant != null) {
			plant.update();
			}
		boulder.update();
		batch.begin();
		boulder.render();
		if(plant != null) {
		plant.render();
		}
		batch.end();


	}

	@Override
	public void dispose() {
		// Dispose of resources when the game is closed
		map.dispose();
		mapRenderer.dispose();
	}


}