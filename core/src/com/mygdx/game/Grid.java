package com.mygdx.game;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class Grid extends Table {
    public Grid() {
        setFillParent(true);
        setDebug(true);

        // Create a 9x5 grid
        for (int row = 0; row < 5; row++) {
            row();
            for (int col = 0; col < 9; col++) {
                add().expand().fill();
            }
        }
    }
}
