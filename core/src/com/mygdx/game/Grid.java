package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class Grid extends Table {
    public Grid() {
        setFillParent(true);
        setDebug(true);
        pad(10); // Set padding for the entire grid
    public 	Skin = new Skin(Gdx.files.internal("ui/glassy-ui.json"));

        for (int row = 0; row < 5; row++) {
            row();
            for (int col = 0; col < 9; col++) {
                add().expand();
            }
        }
    }
}
