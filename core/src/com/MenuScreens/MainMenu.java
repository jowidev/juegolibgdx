package com.MenuScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.Troops.Assets;
import com.mygdx.game.GameScreen;
import com.mygdx.game.Gamemap;

import static com.Troops.Assets.SKIN;

public class MainMenu extends ScreenAdapter { //implements screen
    private Stage stage;
    private Viewport viewport;
    private Assets assets;
    private Skin skin;
    private Table MainTable;
    public Gamemap gamemap;
    public MainMenu(Gamemap gamemap) {
       this.assets =  gamemap.assets;
        skin = assets.manager.get(SKIN);
        this.gamemap = gamemap;

    }
    @Override
    public void show() {
        viewport = new ExtendViewport(800, 600);
        stage = new Stage(viewport);
        MainTable = new Table();
        stage.addActor(MainTable);
        MainTable.setFillParent(true);

        addButton("Jugar").addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                gamemap.setScreen(new GameScreen(gamemap)); //aca se lo pasa pq lo usa el boulder
            }
        });
        addButton("Salir").addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                Gdx.app.exit();
            }
        });

        Gdx.input.setInputProcessor(stage);
    }

    private TextButton addButton(String name){
        TextButton button = new TextButton(name, skin);
        MainTable.add(button).width((float) Gdx.graphics.getWidth() /2).padBottom(10);
        MainTable.row();
        return button;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.4f,.5f,.3f,1);
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