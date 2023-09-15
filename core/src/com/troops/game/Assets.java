package com.troops.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;


public class Assets {
	private AssetManager manager = new AssetManager();
	public final Array<AtlasRegion> slimewalk;
	public final Array<AtlasRegion> boulderwalk;
	private final TextureAtlas atlas;
	public static final AssetDescriptor<Skin> SKIN = new AssetDescriptor<Skin>("skin/freezing-ui.json", Skin.class, new SkinLoader.SkinParameter("skin/freezing-ui.atlas"))
	public Assets() {
		manager.load("slimes/slime-idle-0.png", TextureAtlas.class);
		TextureLoader.TextureParameter param = new TextureLoader.TextureParameter();
		 
		this.atlas = new TextureAtlas(Gdx.files.internal("game.atlas"));
        this.slimewalk = atlas.findRegions("slime");
        this.boulderwalk = atlas.findRegions("boulder");
	}
	public void loadAll() {
		manager.load(SKIN);
	}
}
