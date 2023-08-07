package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.math.Vector2;

public class Boulder {
	private final Gamemap game; 
	public float stateTime;
	public Animation<TextureRegion> idleanimation;
	public Vector2 boulderpos;
	
	public Boulder(Gamemap game) {
		
		stateTime = 0;
		this.game = game;
		idleanimation = new Animation<TextureRegion>(0.5f/7, game.assets.boulderwalk, PlayMode.LOOP);
		boulderpos= new Vector2(19,4);
		
	}
	public void update() {
		boulderpos.x -= 0.03;
		
	}
	
	public void render() {
		stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time
		TextureRegion currentFrame = idleanimation.getKeyFrame(stateTime, true);
		game.batch.draw(currentFrame, boulderpos.x, boulderpos.y,2,2); // Draw current frame at (50, 50)
	}
	

}
