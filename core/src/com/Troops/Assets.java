package com.Troops;

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
	public AssetManager manager = new AssetManager();
	public final Array<AtlasRegion> slimewalk;
	public final Array<AtlasRegion> boulderwalk;
	private final TextureAtlas atlas;
	public static final AssetDescriptor<Skin> SKIN = new AssetDescriptor<Skin>("skin/freezing-ui.json", Skin.class, new SkinLoader.SkinParameter("skin/freezing-ui.atlas"));
	public Assets() {
		manager.load("game.atlas", TextureAtlas.class);
		manager.load(SKIN);
		manager.finishLoading();
		this.atlas = manager.get("game.atlas");
        this.slimewalk = atlas.findRegions("slime");
        this.boulderwalk = atlas.findRegions("boulder");
	}

}
