package com.Troops;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Gamemap;

import javax.swing.text.View;
import java.util.Random;

public class Boulder {
	private final Gamemap game;
	public float stateTime;
	public Animation<TextureRegion> idleanimation;
	public Vector2 boulderpos;
	private boolean boulderOnMouse;
	Random random = new Random();
	int randomNumber = random.nextInt(5) + 1;


	public Boulder(Gamemap game) {
		stateTime = 0;
		this.game = game;

		idleanimation = new Animation<TextureRegion>(0.5f/7, game.assets.boulderwalk, PlayMode.LOOP);
		boulderpos= new Vector2(17,1.28f*randomNumber);

	}



	public void update(Viewport viewport) {
		if(!boulderOnMouse ) {
			Vector3 position = viewport.unproject(new Vector3(Gdx.input.getX(),Gdx.input.getY(),0));
			boulderpos.x = position.x-1;
			boulderpos.y = position.y-1;
		}
		if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && !boulderOnMouse) {
			boulderOnMouse = true;
			game.assets.boulderPlaced.play();
		}
		boulderpos.x -= 2*Gdx.graphics.getDeltaTime();

	}
	public void show() {

	}
	public void render() {
		stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time
		TextureRegion currentFrame = idleanimation.getKeyFrame(stateTime, true);
		Gamemap.batch.draw(currentFrame, boulderpos.x, boulderpos.y,2,2);
	}


}
