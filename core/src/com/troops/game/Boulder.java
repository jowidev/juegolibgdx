package com.troops.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Gamemap;

import java.util.Random;

public class Boulder {
	public int health;
	private final Gamemap game;
	public float stateTime;
	public Animation<TextureRegion> idleanimation;
	public Vector2 boulderpos;
	Random random = new Random();
	int randomNumber = random.nextInt(5) + 1;


	public Boulder(Gamemap game) {
		stateTime = 0;
		this.game = game;

		idleanimation = new Animation<TextureRegion>(0.5f/7, game.assets.boulderwalk, PlayMode.LOOP);
		boulderpos= new Vector2(19,1.28f*randomNumber);

	}



	public void update() {
		boulderpos.x -= .5f*Gdx.graphics.getDeltaTime();
	}
	public void show() {

	}
	public void render() {
		stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time
		TextureRegion currentFrame = idleanimation.getKeyFrame(stateTime, true);
		game.batch.draw(currentFrame, boulderpos.x, boulderpos.y,2,2);
	}


}
