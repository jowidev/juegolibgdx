package com.Troops;

import com.badlogic.gdx.math.Rectangle;

public class BaseTroop {
    protected float hp;
    public Rectangle hitbox = new Rectangle();

    public BaseTroop(int x, int y, float width, float height) {
        hitbox.set(x, y, width, height);
        hp = 100; // Set an initial health value
    }

    public void takeDamage(int damage) {
        hp -= damage;
        if (hp <= 0) {
            System.out.println("a");
        }
    }

    public float getHp() {
        return hp;
    }

    public void setHp(float hp) {
        this.hp = hp;
    }

    // Add other common methods or attributes here
}