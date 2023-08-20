package com.troops.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Array;


public class Assets {
	public final Array<AtlasRegion> slimewalk;
	public final Array<AtlasRegion> boulderwalk;
	private final TextureAtlas atlas;
	public Assets() {
		this.atlas = new TextureAtlas(Gdx.files.internal("game.atlas"));
        this.slimewalk = atlas.findRegions("slime");
        this.boulderwalk = atlas.findRegions("boulder");
	} 

}
