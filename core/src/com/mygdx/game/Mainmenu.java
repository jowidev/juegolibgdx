package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.troops.game.Assets;
import jdk.tools.jmod.Main;

public class Mainmenu extends ScreenAdapter { //implements screen
    private Stage stage;
    private Viewport viewport;
    private AssetManager assetManager;
    private Skin skin;
    private Table MainTable;

    public Mainmenu(AssetManager assetManager) {
        this.assetManager= assetManager;
        skin = assetManager.get(Assets.SKIN);
    }
    @Override
    public void show() {
        viewport = new ExtendViewport(800, 600);
        stage = new Stage(viewport);
        MainTable = new Table();
        stage.addActor(MainTable);
        MainTable.setFillParent(true);
    }

    private TextButton addButton(String name){
        TextButton button = new TextButton(name, skin);
        MainTable.add(button);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.1f,.1f,.1f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(); //el act registra clicks, mov mouse, boludeces
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}