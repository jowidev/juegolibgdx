package com.mygdx.game;

import com.MenuScreens.MainMenu;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.Troops.Assets;
import com.Troops.Boulder;
import com.Troops.Slime;

public class Gamemap extends Game {
	public FitViewport viewport;
	public SpriteBatch batch;
	public Assets assets;

	public static Sound sound;

	@Override
	public void create() {
		this.assets = new Assets();

		batch = new SpriteBatch();

		this.setScreen(new MainMenu(this)); //va ultimo
	}



	public void resize(int w, int h) {

	}



	@Override
	public void render() {
		super.render(); //llama al render parent

	}

	@Override
	public void dispose() {


	}
}