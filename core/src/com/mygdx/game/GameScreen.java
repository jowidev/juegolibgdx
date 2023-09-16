package com.mygdx.game;

import com.Troops.Boulder;
import com.Troops.Slime;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class GameScreen implements Screen {
    private final TiledMap map;
    private final OrthogonalTiledMapRenderer mapRenderer;
    public FitViewport viewport;
    public SpriteBatch batch;
    public Camera camera;
    public com.Troops.Slime Slime;
    public com.Troops.Boulder Boulder;
    private final Music mainsong;
    public Stage stage;
    public Sound sound;
    public Gamemap gamemap; //el de arriba
    @Override
    public void show() {

    }
    public GameScreen(Gamemap gamemap) { //crear boludeces //este
        Grid grid = new Grid();
        stage = new Stage();
        stage.addActor(grid);
        Gdx.input.setInputProcessor(stage);
        mainsong = Gdx.audio.newMusic(Gdx.files.internal("miscAssets/finalbattle.mp3"));
        sound = Gdx.audio.newSound(Gdx.files.internal("slimes/slimeplaced.mp3"));
        mainsong.setLooping(true);
        mainsong.setVolume(.07f);
        this.map = new TmxMapLoader().load("tilemap/tilemap.tmx"); 		// Load the tmp
        mapRenderer = new OrthogonalTiledMapRenderer(map, Constants.pixeltotile, batch); // Create the map renderer
        viewport = new FitViewport(20, 12); //hay que hacerlo de 12x12
        camera = viewport.getCamera();
        camera.position.set(Constants.GAME_WORLD_WIDTH_tile/2, Constants.GAME_WORLD_HEIGHT_tile/2, 0);
        //mainsong.play();
        this.Boulder = new Boulder(gamemap);
        this.gamemap = gamemap; //al de arriba le paso este
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
            //sound.play();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_0)) {
            this.Boulder = new Boulder(gamemap);

        }
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.4f, .6f, .8f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        handleInput();
        viewport.apply();
        batch.setProjectionMatrix(camera.combined);
        mapRenderer.setView(camera.combined, 0,0 ,21, 12);
        mapRenderer.render();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        if(Slime != null) {
            Slime.update();
        }
        Boulder.update();
        batch.begin();
        Boulder.render();
        if(Slime != null) {
            Slime.render();
        }
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
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
        //se llama cuando se cierra el programa
        map.dispose();
        mapRenderer.dispose();
        mainsong.dispose();
        stage.dispose();
    }
}
