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
	public boolean slimeOnMouse;
	public float SlimeW, SlimeH;

	public Rectangle slimeHitbox = new Rectangle();
	//private final ShapeRenderer shR;

	public Slime(Gamemap game, int x, int y) {
		slimeOnMouse = false;
		stateTime = 0;
		this.game = game;
		idleanimation = new Animation<TextureRegion>(0.033f, game.assets.slimewalk, PlayMode.LOOP);
		slimeHitbox.set(x,y, SlimeW, SlimeH);
	}
	public void update(Viewport viewport) {
		if (!slimeOnMouse) {
			Vector3 pos = viewport.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
			slimeHitbox.x = pos.x - 1;
			slimeHitbox.y = pos.y -1;
			slimeHitbox.setPosition(slimeHitbox.x, slimeHitbox.y); // Update hitbox position

		}     if (slimeOnMouse) {
			slimeHitbox.setPosition(slimeHitbox.x, slimeHitbox.y); // Update hitbox position to follow the slime
		}
		if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && !slimeOnMouse) {
			slimeOnMouse = true;
			game.assets.slimeplaced.play();
		}

	}

	public void render() {
		stateTime += Gdx.graphics.getDeltaTime();
		TextureRegion currentFrame = idleanimation.getKeyFrame(stateTime, true);
		Gamemap.batch.draw(currentFrame, slimeHitbox.x, slimeHitbox.y, 2, 2);


	}
}
