package com.Troops;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Gamemap;

public class Slime extends BaseTroop {

	private final Gamemap game;
	public Animation<TextureRegion> idleanimation;
	public float stateTime;
	public boolean slimeOnMouse;
	public float SlimeW, SlimeH;

	public Rectangle slimeHitbox = new Rectangle();

	public Slime(Gamemap game, int x, int y) {
		super(x, y, 2, 2);
		slimeOnMouse = false;
		stateTime = 0;
		this.game = game;
		idleanimation = new Animation<TextureRegion>(0.033f, game.assets.slimewalk, PlayMode.LOOP);
	}
	public void update(Viewport viewport) {
		if (!slimeOnMouse) {
			Vector3 pos = viewport.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
			slimeHitbox.x = pos.x -1;
			slimeHitbox.y = pos.y -1;

		}
		if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && !slimeOnMouse) {
			slimeOnMouse = true;
			game.assets.slimeplaced.play();
			slimeHitbox.set(slimeHitbox.x,slimeHitbox.y, 1, 1.25f);

		}

	}

	public void render() {
		stateTime += Gdx.graphics.getDeltaTime();
		TextureRegion currentFrame = idleanimation.getKeyFrame(stateTime, true);
		Gamemap.batch.draw(currentFrame, slimeHitbox.x, slimeHitbox.y, 2, 2);


	}
}
