package com.Troops;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;


public class Assets {
	public AssetManager manager = new AssetManager();
	public final Array<AtlasRegion> slimewalk;
	public final Array<AtlasRegion> boulderwalk;
	public final Music trumpsong;
	public final Sound slimeplaced;
	private final TextureAtlas atlas;
	public static final AssetDescriptor<Skin> SKIN = new AssetDescriptor<Skin>("skin/freezing-ui.json", Skin.class, new SkinLoader.SkinParameter("skin/freezing-ui.atlas"));
	public Assets() {
		manager.load("game.atlas", TextureAtlas.class);
		manager.load(SKIN);
		manager.load("miscAssets/trumpsong.mp3", Music.class);
		manager.load("miscAssets/finalbattle.mp3", Music.class);
		manager.load("slimes/slimeplaced.mp3", Sound.class);
		manager.finishLoading();

		this.atlas = manager.get("game.atlas");
        this.slimewalk = atlas.findRegions("slimes/planta");
        this.boulderwalk = atlas.findRegions("boulders/boulder");
		assert boulderwalk.size!=0:"no se cargo el boulder moya boton";
		this.trumpsong= manager.get("miscAssets/trumpsong.mp3");
		this.slimeplaced = manager.get("slimes/slimeplaced.mp3");
	//hacer todo lo de los assets aca
	}

}
