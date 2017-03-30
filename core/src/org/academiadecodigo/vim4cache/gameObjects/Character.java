package org.academiadecodigo.vim4cache.gameObjects;

import com.badlogic.gdx.math.Vector2;

import java.awt.*;

/**
 * Created by codecadet on 30/03/17.
 */
public class Character {
    private Vector2 position;
    private Vector2 velocity;
    private int width;
    private int height;
    private Rectangle characterRectangle;

    public Character(int x, int y, int width, int height){
        this.width = width;
        this.height = height;
        this.position = new Vector2(x, y);
        this.velocity = new Vector2(0, 0);
        this.characterRectangle = new Rectangle(x, y, width, height);
    }

    public void update(float delta) {





    }
}
