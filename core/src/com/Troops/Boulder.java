package com.Troops;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.BaseTroop;
import com.mygdx.game.Gamemap;

import javax.swing.text.View;
import java.util.Random;

public class Boulder extends BaseTroop {
	private final Gamemap game;
	public float stateTime;
	public Animation<TextureRegion> idleanimation;
	private boolean boulderOnMouse;
	float boulderW, boulderH;


	public Rectangle boulderHitbox = new Rectangle(); //shape crea el coso
	// shaperenderer muestra el coso
	public Boulder(Gamemap game, int x, int y) {
		super(x, y);
		stateTime = 0;
		this.game = game;
		boulderW = 1;
		boulderH = 1;
		idleanimation = new Animation<TextureRegion>(0.5f/7, game.assets.boulderwalk, PlayMode.LOOP);
		boulderHitbox.set(x,y, boulderW, boulderH);
	}



	public void HitboxCheck(Slime slime) {

		if (boulderHitbox.overlaps(slime.slimeHitbox)) {
				boulderHitbox.x -=.0f*Gdx.graphics.getDeltaTime();
		}
		else {
			boulderHitbox.x -= 2*Gdx.graphics.getDeltaTime();
		}
	}

	public void update(Viewport viewport, Slime slime) {
		if(!boulderOnMouse) {
			Vector3 position = viewport.unproject(new Vector3(Gdx.input.getX(),Gdx.input.getY(),0));
			boulderHitbox.x = position.x;
			boulderHitbox.y = position.y;
			boulderHitbox.setPosition(position.x, position.y);
		}
		if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && !boulderOnMouse) {
			boulderOnMouse = true;
			game.assets.boulderPlaced.play();
		}
		HitboxCheck(slime);
	}


	public void render() {
		stateTime += Gdx.graphics.getDeltaTime();
		TextureRegion currentFrame = idleanimation.getKeyFrame(stateTime, true);
		Gamemap.batch.draw(currentFrame, boulderHitbox.x, boulderHitbox.y,2,2);
	}


}
