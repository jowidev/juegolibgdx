package com.Troops;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Gamemap;

public class Slime {

	private final Gamemap game;
	public Animation<TextureRegion> idleanimation;
	public float stateTime;
	public Vector2 slimepos;
	public boolean slimeOnMouse;
	public float SlimeW, SlimeH;

	public Rectangle slimeHitbox = new Rectangle();


	public Slime(Gamemap game) {
		slimeOnMouse = false;
		stateTime = 0;
		this.game = game;
		idleanimation = new Animation<TextureRegion>(0.033f, game.assets.slimewalk, PlayMode.LOOP);
		slimepos= new Vector2(0,0);
		slimeHitbox.set(slimepos.x, slimepos.y, SlimeW, SlimeH);

	}
	public void Hitbox() {
		//slimeHitbox.set(slimepos.x, slimepos.y, SlimeW, SlimeH);

	}

	public void update(Viewport viewport){
		if(!slimeOnMouse) {
			Vector3 position = viewport.unproject(new Vector3(Gdx.input.getX(),Gdx.input.getY(),0));
			slimepos.x = position.x-1;
			slimepos.y = position.y-1;
		}
		if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && !slimeOnMouse) {
			slimeOnMouse = true;
			game.assets.slimeplaced.play();
		}
	}


	public void render() {
		stateTime += Gdx.graphics.getDeltaTime();
		TextureRegion currentFrame = idleanimation.getKeyFrame(stateTime, true);
		Gamemap.batch.draw(currentFrame, slimepos.x, slimepos.y,2,2);
	}
}