package com.mygdx.game;

import com.MenuScreens.HUD;
import com.Troops.Boulder;
import com.Troops.Slime;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class GameScreen implements Screen {
    private final TiledMap map;
    private final OrthogonalTiledMapRenderer mapRenderer;
    public FitViewport viewport;
    public OrthographicCamera camera;

    public com.Troops.Slime Slime;
    public com.Troops.Boulder Boulder;
    private final Music mainsong;
    public Stage stage;
    public Gamemap gamemap; //el de arriba
    public Float time;
    public int roundedTime;
    public HUD hud;


    @Override
    public void show() {

    }
    public GameScreen(Gamemap gamemap) {  //este
        this.gamemap = gamemap; //al de arriba le paso este
        Grid grid = new Grid();
        HUD hud = new HUD();
        camera = new OrthographicCamera();
        stage = new Stage();
       stage.addActor(grid);
        stage.addActor(hud.getSlimeTable());
        stage.addActor(hud.getTimerTable());
        stage.addActor(hud.getBoulderHud());
        Gdx.input.setInputProcessor(stage);
        mainsong = gamemap.assets.finalbattle;
        mainsong.setLooping(true);
        mainsong.setVolume(.07f);
        this.map = new TmxMapLoader().load("tilemap/tilemap.tmx"); 		// mandarlo al assetmanager dsp si hincha las bolas
        mapRenderer = new OrthogonalTiledMapRenderer(map, Constants.pixeltotile, gamemap.batch); // Create the map renderer
        this.viewport = new FitViewport(Constants.GAME_WORLD_WIDTH_tile,Constants.GAME_WORLD_HEIGHT_tile, camera); //hay que hacerlo de 12x12
        //camera = viewport.getCamera();
        camera.position.set(Constants.GAME_WORLD_WIDTH_tile/2, Constants.GAME_WORLD_HEIGHT_tile/2, 0);
        //mainsong.play();
        this.Boulder = new Boulder(gamemap);
        time = 300f;
        roundedTime = time.intValue();

    }


    private void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) ) {
            camera.translate(-.09f, 0, 0);
            if (camera.position.x<=camera.viewportWidth / 2) {
                camera.translate(.09f, 0, 0);
            }
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {		//NO PUEDE SER LO HICE SIN CHATGPT OJO ROCKSTAR
            camera.translate(.09f, 0, 0);
            if (camera.position.x>= Constants.GAME_WORLD_WIDTH_tile - camera.viewportWidth / 2) {
                camera.translate(-.09f, 0, 0);
            }
        }

        //MOUSE
        if (Gdx.input.getX() >= Gdx.graphics.getWidth() - 150  &&  Gdx.input.getX() <= Gdx.graphics.getWidth()) {
            camera.translate(.15f, 0, 0);
            if (camera.position.x>= Constants.GAME_WORLD_WIDTH_tile - camera.viewportWidth / 2) {
                camera.translate(-.15f, 0, 0);
            }
        }
        if (Gdx.input.getX() <= 150  &&  Gdx.input.getX() >= 0) {
            camera.translate(-.15f, 0, 0);
            if (camera.position.x <= camera.viewportWidth / 2) {
                camera.translate(.15f, 0, 0);
            }
        }


        //aca vienen inputs de gameplay (mariconadas)
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
            this.Slime = new Slime(gamemap);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_0)) { //SIEMPRE QUE SE USA EL BATCH ES gamemap.batch
            this.Boulder = new Boulder(gamemap);

        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.4f,.6f,.8f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        handleInput();
        time-=Gdx.graphics.getDeltaTime();
        roundedTime = time.intValue();
        hud.updateTimeLabel(roundedTime);
        gamemap.batch.setProjectionMatrix(camera.combined);

        mapRenderer.setView((OrthographicCamera)viewport.getCamera());
        mapRenderer.render();
        if (Slime != null) {
            Slime.update(viewport);
        }
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();



        Boulder.update();

        gamemap.batch.begin();
        Boulder.render();

        if (Slime != null) {
            Slime.render();
        }

        gamemap.batch.end();

    }


    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.position.set(Constants.GAME_WORLD_WIDTH_tile / 2, Constants.GAME_WORLD_HEIGHT_tile / 2, 0);
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

    public int getRoundedTime() {
        return roundedTime;
    }
    @Override
    public void dispose() {
        //se llama cuando se cierra el programa
        map.dispose();
        mapRenderer.dispose();
        mainsong.dispose();
        stage.dispose();
    }
}
