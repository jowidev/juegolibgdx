package com.MenuScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.*;
import com.Troops.Assets;
import com.mygdx.game.GameScreen;
import com.mygdx.game.Gamemap;

import static com.Troops.Assets.SKIN;

public class MainMenu extends ScreenAdapter  { //implements screen?
    private final Stage stage;
    private final Viewport viewport;
    private Assets assets;
    private final Skin skin;
    private Table MainTable;
    public Gamemap gamemap;
    public MainMenu(final Gamemap gamemap) {
        this.assets = gamemap.assets;
        skin = assets.manager.get(SKIN);
        this.gamemap = gamemap;

        Texture backgroundTexture = new Texture(Gdx.files.internal("miscAssets/fnaf.jpg"));
        Image backgroundImage = new Image(backgroundTexture);

        viewport = new ScreenViewport(); // Set the viewport with the batch
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);

        backgroundImage.setSize(viewport.getScreenWidth(), viewport.getScreenHeight());
        stage.addActor(backgroundImage);

        MainTable = new Table();
        stage.addActor(MainTable); // Add the MainTable to the stage first
        MainTable.setFillParent(true); // Now set FillParent to true

        MainTable.setSize(viewport.getScreenWidth(), viewport.getScreenHeight());
        MainTable.center();
        MainTable.setDebug(true);
    }

    @Override
    public void show() {
        //MainTable.setFillParent(true);
        addButton("Jugar").addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                gamemap.setScreen(new GameScreen(gamemap)); //aca se lo p   asa pq lo usa el boulder
            }
        });

        addButton("Salir").addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                Gdx.app.exit();
            }
        });
    }

    private TextButton addButton(String name){
        TextButton button = new TextButton(name, skin);
        MainTable.add(button).width((float) Gdx.graphics.getWidth() / 2 ).height(80).padBottom(10);
        MainTable.row();
        return button;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.4f,.5f,.7f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        viewport.apply();
        gamemap.batch.setProjectionMatrix(viewport.getCamera().combined);

        stage.act(Gdx.graphics.getDeltaTime()); //el act registra clicks, mov mouse, boludeces
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        // no resizing of MainTable necessary here as the stage handles it
        stage.getViewport().update(width, height, true);

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
        stage.dispose();


    }
}