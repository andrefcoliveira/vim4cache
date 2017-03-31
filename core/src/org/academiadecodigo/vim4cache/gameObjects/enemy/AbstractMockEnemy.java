package org.academiadecodigo.vim4cache.gameObjects.enemy;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import org.academiadecodigo.vim4cache.gameObjects.player.Character;
import org.academiadecodigo.vim4cache.screens.PlayScreen;

/**
 * Created by codecadet on 30/03/17.
 */
public abstract class AbstractMockEnemy extends Sprite {

    protected World world;
    protected PlayScreen screen;
    public Body b2Body;
    public Vector2 velocity;

    private int health;
    private TextureRegion characterUp;
    private TextureRegion characterDown;
    private TextureRegion characterLeft;
    private TextureRegion characterRight;

    public AbstractMockEnemy() {
    }

    public AbstractMockEnemy(World world, PlayScreen screen) {

        this.world = world;
        this.screen = screen;
        defineEnemy();
        velocity = new Vector2(0, 0);
    }

    public void defineEnemy() {

    }

    public abstract void update();

    public abstract void onHit(Character character);

    public abstract void hitByEnemy(AbstractMockEnemy enemy);

    public void reverseVelocity(boolean x, boolean y) {

        if (x) {
            velocity.x = -velocity.x;
        }

        if (y) {
            velocity.y = -velocity.y;
        }
    }

}
