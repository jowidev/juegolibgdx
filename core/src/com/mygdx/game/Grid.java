package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class Grid extends Table {
    public Grid() {
        setFillParent(true);

        Skin skin = new Skin(Gdx.files.internal("skin/freezing-ui.json"));

        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 9; col++) {
                add().width(100).height(128); // Customize the width and height as per your requirements
            }
            row(); // Move to the next row in the grid
        }


        setPosition(0, 0);
        setDebug(true);
    }
}
