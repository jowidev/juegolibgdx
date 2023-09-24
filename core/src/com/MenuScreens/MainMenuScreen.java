package com.MenuScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Assets;
import com.mygdx.game.GameScreen;
import com.mygdx.game.Gamemap;

import static com.mygdx.game.Assets.SKIN;

public class MainMenuScreen extends ScreenAdapter  { //implements screen?
    private final Stage stage;
    private final Skin skin;
    private final OrthographicCamera cam;
    private final Table MainTable;
    public Gamemap gamemap;

    public MainMenuScreen(final Gamemap gamemap) {
        Assets assets = gamemap.assets;
        Image bgImg = new Image(assets.bgTxT);
        skin = Assets.manager.get(SKIN);
        this.gamemap = gamemap;
        cam = new OrthographicCamera();
        cam.setToOrtho(false,800, 600);


        //cam.position.set(Constants.GAME_WORLD_WIDTH_tile / 2, Constants.GAME_WORLD_HEIGHT_tile / 2, 0);


        Viewport viewport = new ScreenViewport(cam);  //no pasar  GAME_WORLD_HEIGHT NI WIDTH, no tiene nada que ver con el coso este
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);
        bgImg.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.addActor(bgImg);

        MainTable = new Table();
        stage.addActor(MainTable);
        MainTable.setFillParent(true);

        MainTable.setPosition(0,0);
        //MainTable.setDebug(true);
        MainTable.add(bgImg).width((float) Gdx.graphics.getWidth() /1.5f).height((float) Gdx.graphics.getHeight() /5.5f).padBottom((float) Gdx.graphics.getHeight() /4.5f);

        MainTable.row();
        addButton("Jugar").addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                gamemap.setScreen(new GameScreen(gamemap)); //aca se lo pasa pq lo usa el boulder
            }
        });

        addButton("Exit").addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                Gdx.app.exit();
            }
        });
    }

    private TextButton addButton(String name){
        TextButton button = new TextButton(name, skin);
        MainTable.add(button).width((float) Gdx.graphics.getWidth() / 3).height((float) Gdx.graphics.getHeight() /8).padBottom(10);
        MainTable.row();
        return button;
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.4f,.5f,.7f,1);
        cam.update();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Gamemap.batch.setProjectionMatrix(cam.combined);

        stage.act(Gdx.graphics.getDeltaTime()); //el act registra clicks, mov mouse, boludeces
        stage.draw();
        handleInput();
    }

    public void handleInput () {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }

    }
    @Override
    public void resize(int width, int height) {
        cam.viewportWidth = width;
        cam.viewportHeight = height;
        cam.position.set(cam.viewportWidth / 2, cam.viewportHeight / 2, 0);
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}