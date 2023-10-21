package com.mygdx.game;

import com.badlogic.gdx.math.Rectangle;

public class BaseTroop {
    private int hp;

    public Rectangle hitbox = new Rectangle();

    public BaseTroop(int x, int y) {
        hitbox.set(x, y, 2, 2);
    }
    //falta meter el coso de las animaciones
}
