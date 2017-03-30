package org.academiadecodigo.vim4cache.gameObjects;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by codecadet on 30/03/17.
 */
public class Character extends Sprite {
    private int health;
    private World world;
    private Screen screen;
    private Body b2body;
    private float x = 300;
    private float y = 300;
    private float speed;


    public Character(World world, Screen screen) {
        this.world = world;
        this.screen = screen;
        defineCharacter();
    }

    public void update(float delta) {
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
    }

    public void defineCharacter() {
        BodyDef bodyDef = new BodyDef();
        this.speed = 10.0f;
        this.health = 100;
        bodyDef.position.set(x, y);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(50, 25);

        fixtureDef.shape = polygonShape;
        b2body.createFixture(fixtureDef);

    }


    public int getHealth() {
        return health;
    }



    public Body getB2body() {
        return b2body;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public void setX(float x) {
        this.x = x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public void setY(float y) {
        this.y = y;
    }
}
