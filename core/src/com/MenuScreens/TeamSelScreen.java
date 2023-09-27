package com.MenuScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Assets;
import com.mygdx.game.GameScreen;
import com.mygdx.game.Gamemap;

import static com.mygdx.game.Assets.SKIN;
import static com.mygdx.game.Gamemap.batch;

public class TeamSelScreen implements Screen {
    private final OrthographicCamera cam;
    private final Stage stage;
    public Skin skin;
    private Table teamTable;
    public TeamSelScreen(final Gamemap gamemap) {
        skin = Assets.manager.get(SKIN);
        Texture bando = gamemap.assets.bando;
        Image bandoImg = new Image(bando);


        cam = new OrthographicCamera();
        cam.setToOrtho(false,800, 600);



        final Viewport viewport = new ScreenViewport(cam);
        stage = new Stage(viewport, batch);
        Gdx.input.setInputProcessor(stage);
        teamTable = new Table();
        stage.addActor(teamTable);
        teamTable.setFillParent(true);
        teamTable.setPosition(0,0);
        teamTable.center();
        teamTable.add(bandoImg);
        teamTable.row();
        teamTable.add().
                width(Gdx.graphics.getWidth()/2).
                height(Gdx.graphics.getHeight()/2).
                padBottom(Gdx.graphics.getHeight()/4);
        addButton("Slimes").
                addListener(
                        new ClickListener(){
                            @Override
                            public void clicked(InputEvent event, float x, float y){
                                gamemap.setScreen(new GameScreen(gamemap)); //aca se lo pasa pq lo usa el boulder

                            }
                        });
        addButton("Boulders").
                addListener(
                        new ClickListener(){
                            @Override
                            public void clicked(InputEvent event, float x, float y){
                                gamemap.setScreen(new GameScreen(gamemap));
                            }
                        }
                );

        //teamTable.setDebug(true);

    }
    private TextButton addButton(String name){
        TextButton button = new TextButton(name, skin);
        teamTable.add(button).
                width((float) Gdx.graphics.getWidth() / 5)
                .height((float) Gdx.graphics.getHeight() /5)
                .center();
        return button;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.4f,.5f,.7f,1);
        cam.update();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
        batch.setProjectionMatrix(cam.combined);
    }

    @Override
    public void resize(int width, int height) {

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
