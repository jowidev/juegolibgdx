package com.MenuScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.Assets;
import com.mygdx.game.Constants;
import com.mygdx.game.GameScreen;

import static com.mygdx.game.Assets.SKIN;

public class HUD extends Table {
    private Table slimeTable;
    private Table timerTable;
    private Table boulderTable;
    public Skin skin;
    public HUD() {
         skin = Assets.manager.get(SKIN);
        //float floatValue = GameScreen.time; // Assuming GameScreen.time is a float
        //int intValue = Math.round(floatValue); // Round and convert to int
        //Label time = new Label(Integer.toString(intValue), skin);
        Texture imageTexture = new Texture(Gdx.files.internal("miscAssets/AngyBowler.png"));
        Image image = new Image(imageTexture);
        Label label = new Label("ha", skin);

        slimeTable = new Table();
        timerTable = new Table();
        boulderTable = new Table();

        slimeTable.setDebug(true);
        timerTable.setDebug(true);
        boulderTable.setDebug(true);

        slimeTable.top().left();
        timerTable.center().top();
        boulderTable.left().top();

        slimeTable.setPosition(0, Gdx.graphics.getHeight());
        timerTable.setPosition((float) Gdx.graphics.getWidth() /2, Gdx.graphics.getHeight());
        boulderTable.setPosition(Gdx.graphics.getWidth()-(Gdx.graphics.getWidth()*(Constants.pixeltotile*2f)),Gdx.graphics.getHeight());

        slimeTable.padTop(24);
        timerTable.padTop(16);
        boulderTable.padTop(24);
        

        slimeTable.add().width(Gdx.graphics.getWidth()*(Constants.pixeltotile*1.5f)).height(Gdx.graphics.getHeight()*(Constants.pixeltotile*2)).padLeft(16).padBottom(24);; //this line
        slimeTable.row();

        for (int row = 0; row < 5; row++) { //slime
            for (int col = 0; col < 1; col++) {
                slimeTable.add().width(Gdx.graphics.getWidth()*(Constants.pixeltotile*1.5f)).height(Gdx.graphics.getHeight()*(Constants.pixeltotile*2.5f)).padLeft(16);
            }
            slimeTable.row();
        }

        timerTable.add(image).width(Gdx.graphics.getWidth()*(Constants.pixeltotile*2)).height(Gdx.graphics.getHeight()*(Constants.pixeltotile*2)); //ACA SE AÑADE EL TIMER

        boulderTable.add().width(Gdx.graphics.getWidth()*(Constants.pixeltotile*1.5f)).height(Gdx.graphics.getHeight()*(Constants.pixeltotile*2)).padRight(16).padBottom(24);; //this line
        boulderTable.row();
        for (int row = 0; row < 5; row++) { //boulder
            for (int col = 0; col < 1; col++) {
                boulderTable.add().width(Gdx.graphics.getWidth()*(Constants.pixeltotile*1.5f)).height(Gdx.graphics.getHeight()*(Constants.pixeltotile*2.5f)).padRight(16);
            }
            boulderTable.row();
        }
    }


    public Table getSlimeTable() {
        return slimeTable;
    }
    public Table getTimerTable() {
        return timerTable;
    }
    public Table getBoulderHud() {
        return boulderTable;
    }
}
