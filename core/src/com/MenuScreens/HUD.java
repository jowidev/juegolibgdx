package com.MenuScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.Constants;

public class HUD extends Table {
    private Table slimeTable;
    private Table timerTable;
    private Table boulderTable;
    public HUD() {
        //setFillParent(true);
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

        slimeTable.padTop(16);
        timerTable.padTop(16);
        boulderTable.padTop(16);


        slimeTable.add().width(Gdx.graphics.getWidth()*(Constants.pixeltotile*1.5f)).height(Gdx.graphics.getHeight()*(Constants.pixeltotile*2)).padLeft(16).padBottom(24);; //this line
        slimeTable.row();

        for (int row = 0; row < 5; row++) { //slime
            for (int col = 0; col < 1; col++) {
                slimeTable.add().width(Gdx.graphics.getWidth()*(Constants.pixeltotile*1.5f)).height(Gdx.graphics.getHeight()*(Constants.pixeltotile*2.5f)).padLeft(16);
            }
            slimeTable.row();
        }

        timerTable.add().width(Gdx.graphics.getWidth()*(Constants.pixeltotile*3)).height(Gdx.graphics.getHeight()*(Constants.pixeltotile*2));

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
