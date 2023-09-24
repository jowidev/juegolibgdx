package com.MenuScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Assets;
import com.mygdx.game.Gamemap;

public class TeamSelScreen extends ScreenAdapter {
    private final OrthographicCamera cam;
    private final Table teamTable;
    private final Stage stage;
    public TeamSelScreen(Gamemap gamemap) {
        Assets assets = gamemap.assets;



        cam = new OrthographicCamera();
        cam.setToOrtho(false,800, 600);


        //cam.position.set(Constants.GAME_WORLD_WIDTH_tile / 2, Constants.GAME_WORLD_HEIGHT_tile / 2, 0);


        Viewport viewport = new ScreenViewport(cam);
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);
        teamTable = new Table();
        stage.addActor(teamTable);
        teamTable.setFillParent(true);
        teamTable.setPosition(0,0);

    }
}
