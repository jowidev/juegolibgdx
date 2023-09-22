package com.MenuScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Assets;
import com.mygdx.game.Constants;
import com.mygdx.game.GameScreen;
import com.mygdx.game.Gamemap;

import static com.mygdx.game.Assets.SKIN;

public class MainMenuScreen extends ScreenAdapter  { //implements screen?
    private final Stage stage;
    private final Viewport viewport;
    private Assets assets;
    private final Skin skin;
    private final OrthographicCamera cam;
    private final Table MainTable;
    public Gamemap gamemap;

    public MainMenuScreen(final Gamemap gamemap) {
        this.assets = gamemap.assets;
        Texture backgroundTexture = new Texture(Gdx.files.internal("miscAssets/fnaf.jpg"));
        Image bgImg = new Image(backgroundTexture);
        skin = assets.manager.get(SKIN);

        this.gamemap = gamemap;
        cam = new OrthographicCamera();
        cam.setToOrtho(false,800, 600);


        //cam.position.set(Constants.GAME_WORLD_WIDTH_tile / 2, Constants.GAME_WORLD_HEIGHT_tile / 2, 0);



        viewport = new FitViewport(640,480, cam);  //no pasar  GAME_WORLD_HEIGHT NI WIDTH, no tiene nada que ver con el coso este
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);
        bgImg.setSize(640, 480);
        stage.addActor(bgImg);

        MainTable = new Table();
        stage.addActor(MainTable);
        MainTable.setFillParent(true);

        MainTable.setSize(viewport.getScreenWidth(), viewport.getScreenHeight());
        MainTable.setPosition(0,0);
        //MainTable.setDebug(true);
    }

    @Override
    public void show() {
        //MainTable.setFillParent(true);
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
    }

    private TextButton addButton(String name){
        TextButton button = new TextButton(name, skin);
        MainTable.add(button).width(MainTable.getWidth() / 2).height(80).padBottom(10);
        MainTable.row();
        return button;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.4f,.5f,.7f,1);
        cam.update();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gamemap.batch.setProjectionMatrix(cam.combined);

        stage.act(Gdx.graphics.getDeltaTime()); //el act registra clicks, mov mouse, boludeces
        stage.draw();
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }
    }

    @Override
    public void resize(int width, int height) {
        //viewport.update(width, height);
        cam.position.set(cam.viewportWidth/2, cam.viewportHeight / 2f, 0);
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