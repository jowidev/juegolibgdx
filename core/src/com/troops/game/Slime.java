package com.troops.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Gamemap;

public class Slime {
	int health = 100;
	private final Gamemap game;
	public Animation<TextureRegion> idleanimation;
	public float stateTime;
	public Vector2 slimepos;
	private boolean slimeonmouse;
	public Rectangle slimecollider;


	public void update(){
		if(slimeonmouse==false) {
			Vector3 position = game.viewport.unproject(new Vector3(Gdx.input.getX(),Gdx.input.getY(),0));
			slimepos.x = position.x;
			slimepos.y = position.y;
		}
		if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && slimeonmouse==false) {
			slimeonmouse = true;
		}
	}
	
	public Slime(Gamemap game) {
		slimeonmouse = false;
		stateTime = 0;
		this.game = game;
		idleanimation = new Animation<TextureRegion>(0.033f, game.assets.slimewalk, PlayMode.LOOP);
		slimepos= new Vector2(0,0);
	}
	public void render() {
		stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time
		TextureRegion currentFrame = idleanimation.getKeyFrame(stateTime, true);
		game.batch.draw(currentFrame, slimepos.x, slimepos.y,2,2); // Draw current frame at (50, 50)
		
		
	}
}
