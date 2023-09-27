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
	float boulderW, boulderH;

	public Rectangle boulderHitbox = new Rectangle(); //shape crea el coso
	// shaperenderer muestra el coso
	public Boulder(Gamemap game) {
		stateTime = 0;
		this.game = game;

		idleanimation = new Animation<TextureRegion>(0.5f/7, game.assets.boulderwalk, PlayMode.LOOP);
		boulderpos= new Vector2(17,1.28f*randomNumber);

	}


	public void Hitbox() {
		boulderHitbox.set(boulderpos.x, boulderpos.y, boulderW, boulderH);
	}

	public void HitboxCheck(Slime slime) {
		// Update the boulder's hitbox
		Hitbox();

		// Check for collision with the slime
		if (boulderHitbox.overlaps(slime.slimeHitbox)) {
				boulderpos.x -= 0;
		}
		else {
			boulderpos.x -= 2*Gdx.graphics.getDeltaTime();

		}
	}

	public void update(Viewport viewport, Slime slime) {
		if(!boulderOnMouse ) {
			Vector3 position = viewport.unproject(new Vector3(Gdx.input.getX(),Gdx.input.getY(),0));
			boulderpos.x = position.x-1;
			boulderpos.y = position.y-1;
		}
		if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && !boulderOnMouse) {
			boulderOnMouse = true;
			game.assets.boulderPlaced.play();
		}
		HitboxCheck(slime);
	}


	public void show() {

	}
	public void render() {
		stateTime += Gdx.graphics.getDeltaTime();
		TextureRegion currentFrame = idleanimation.getKeyFrame(stateTime, true);
		Gamemap.batch.draw(currentFrame, boulderpos.x, boulderpos.y,2,2);
	}


}
