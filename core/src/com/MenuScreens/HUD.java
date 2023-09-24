package com.MenuScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.mygdx.game.Assets;
import com.mygdx.game.Constants;

import static com.mygdx.game.Assets.*;

public class HUD extends Table {
    private final Table slimeTable;
    private final Table timerTable;
    private final Table boulderTable;
    public Skin skin;
    public HUD() {
        skin = Assets.manager.get(SKIN);
        //float floatValue = GameScreen.time; // Assuming GameScreen.time is a float
        //int intValue = Math.round(floatValue); // Round and convert to int
        //Label time = new Label(Integer.toString(intValue), skin);
        //Texture sign = Assets.manager.get();
        Texture slimeTexture = new Texture(String.valueOf(Assets.manager.get(String.valueOf(sign))));
        Texture imageTexture = new Texture(Gdx.files.internal("boulders/boulderCurr.png"));
        Texture slimeTxt = new Texture(Gdx.files.internal("slimes/slimeCurr.png"));
        Texture currencyBg = new Texture(Gdx.files.internal("miscAssets/currBg.png"));
        Image slimeImage = new Image(slimeTxt);
        Image signImg = new Image(slimeTexture);
        Image currBg = new Image(currencyBg);
        Texture troopBG = new Texture(Gdx.files.internal("miscAssets/TroopBg.png"));
        Image troopImage = new Image(troopBG);
        Image image = new Image(imageTexture);


        //stack.add(label);
        //table.add(stack);

        slimeTable = new Table();
        timerTable = new Table();
        boulderTable = new Table();




        //slimeTable.setDebug(true);
        //timerTable.setDebug(true);
        //boulderTable.setDebug(true);

        slimeTable.top().left();
        timerTable.center().top();
        boulderTable.left().top();

        slimeTable.setPosition(0, Gdx.graphics.getHeight());
        timerTable.setPosition((float) Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight());
        boulderTable.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getWidth() * (Constants.pixeltotile * 2f)), Gdx.graphics.getHeight());

        slimeTable.padTop(24);
        timerTable.padTop(16);
        boulderTable.padTop(24);

        Stack slimeCurr = new Stack();
        slimeCurr.add(currBg);
        slimeCurr.add(slimeImage);
        slimeTable.add(slimeCurr).
                width(Gdx.graphics.getWidth() * (Constants.pixeltotile * 1.5f)).height(Gdx.graphics.getHeight() * (Constants.pixeltotile * 2)).
                padLeft(16).padBottom(24);
        slimeTable.row();

        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 1; col++) {
                slimeImage = new Image(slimeTxt); // Create a new Image instance
                Stack stack = new Stack();
                troopImage = new Image(troopBG);

                stack.add(troopImage);
                stack.add(slimeImage);
                slimeTable.add(stack)
                        .width(Gdx.graphics.getWidth() * (Constants.pixeltotile * 1.5f))
                        .height(Gdx.graphics.getHeight() * (Constants.pixeltotile * 2.5f))
                        .padLeft(16);
            }
            slimeTable.row();
        }

        timerTable.add(signImg).width(Gdx.graphics.getWidth() * (Constants.pixeltotile * 2))
                .height(Gdx.graphics.getHeight() * (Constants.pixeltotile * 2)); //ACA SE AÑADE EL TIMER

        Stack boulderCurr = new Stack();
        boulderCurr.add(currBg);
        boulderCurr.add(image);
        boulderTable.add(boulderCurr).width(Gdx.graphics.getWidth() * (Constants.pixeltotile * 1.5f))
                .height(Gdx.graphics.getHeight() * (Constants.pixeltotile * 2)).padRight(16).padBottom(24);
        ; //this line
        boulderTable.row();
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 1; col++) {
                Image boulderImage = new Image(imageTexture);
                Stack boulderLoop = new Stack();
                currBg = new Image(troopBG);
                //boulderCurr = new Image(image);
                boulderLoop.add(currBg);
                boulderLoop.add(boulderImage);
                boulderTable.add(boulderLoop)
                        .width(Gdx.graphics.getWidth() * (Constants.pixeltotile * 1.5f))
                        .height(Gdx.graphics.getHeight() * (Constants.pixeltotile * 2.5f))
                        .padRight(16);
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
