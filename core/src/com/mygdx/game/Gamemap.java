package com.mygdx.game;

import com.MenuScreens.MainMenuScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Gamemap extends Game {
	public FitViewport viewport;
	public SpriteBatch batch;
	public Assets assets;

	public static Sound sound;

	@Override
	public void create() {
		this.assets = new Assets();

		batch = new SpriteBatch();

		this.setScreen(new MainMenuScreen(this)); //va ultimo
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