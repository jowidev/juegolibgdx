package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.troops.game.Assets;
import com.troops.game.Boulder;
import com.troops.game.Slime;
import com.mygdx.game.Grid;

public class Gamemap extends Game {
	private TiledMap map;
	private OrthogonalTiledMapRenderer mapRenderer;
	public FitViewport viewport;
	public SpriteBatch batch;
	public Camera camera;
	public Assets assets;
	public Slime Slime;
	public Boulder Boulder;
	private Music trump;
	public Stage stage;


	@Override
	public void create() {
		this.assets = new Assets();
		Grid grid = new Grid();
		stage = new Stage();
		stage.addActor(grid);
		Gdx.input.setInputProcessor(stage);

		trump = Gdx.audio.newMusic(Gdx.files.internal("trumpsong.mp3"));
		trump.setVolume(.1f);
		batch = new SpriteBatch();

		this.map = new TmxMapLoader().load("tilemap/tilemap.tmx"); 		// Load the tmp

 
		mapRenderer = new OrthogonalTiledMapRenderer(map, constants.pixeltotile, batch); // Create the map renderer

		viewport = new FitViewport(20.45f, 12);
		camera = viewport.getCamera();
		camera.position.set(constants.GAME_WORLD_WIDTH_tile/2, constants.GAME_WORLD_HEIGHT_tile/2, 0);
		this.Boulder = new Boulder(this);
		//trump.play();

	}



	private void handleInput() {
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT) ) {
			camera.translate(-.09f, 0, 0);
			if (camera.position.x<=camera.viewportWidth / 2) {
				camera.translate(.09f, 0, 0);
			}
		} else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {		//NO PUEDE SER LO HICE SIN CHATGPT OJO ROCKSTAR
			camera.translate(.09f, 0, 0);
			if (camera.position.x>=constants.GAME_WORLD_WIDTH_tile - camera.viewportWidth / 2) {
				camera.translate(-.09f, 0, 0);
			}
		}

		//MOUSE
		if (Gdx.input.getX() >= Gdx.graphics.getWidth() - 150  &&  Gdx.input.getX() <= Gdx.graphics.getWidth()) {
			camera.translate(.15f, 0, 0);
			if (camera.position.x>=constants.GAME_WORLD_WIDTH_tile - camera.viewportWidth / 2) {
				camera.translate(-.15f, 0, 0);
			}
		}
		if (Gdx.input.getX() <= 150  &&  Gdx.input.getX() >= 0) {
			camera.translate(-.15f, 0, 0);
			if (camera.position.x <= camera.viewportWidth / 2) {
				camera.translate(.15f, 0, 0);
			}
		}


		//aca vienen inputs de gameplay (mariconadas) 
		if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
			this.Slime = new Slime(this);
		}
	}



	public void resize(int w, int h) {
		viewport.update(w, h);

	}



	@Override
	public void render() {
		Gdx.gl.glClearColor(.9f, .79f, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		handleInput();
		viewport.apply();

		batch.setProjectionMatrix(camera.combined);
		mapRenderer.setView(camera.combined, 0,0 ,21, 12);
		mapRenderer.render();
		if(Slime != null) {
			Slime.update();
		}
		Boulder.update();
		batch.begin();
		Boulder.render();
		if(Slime != null) {
		Slime.render();
		}
		batch.end();
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}

	@Override
	public void dispose() {
		//se llama cuando se cierra el programa 
		map.dispose();
		mapRenderer.dispose();
		trump.dispose();
		stage.dispose();

	}
}