package org.academiadecodigo.vim4cache.gameObjects;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by codecadet on 30/03/17.
 */
public class Character extends Sprite{
    private int health;
    private World world;
    private Screen screen;
    public Body b2body;


    public Character(World world, Screen screen) {
        this.world = world;
        this.screen = screen;
        defineCharacter();
    }

    public void update(float delta) {
        setPosition(b2body.getPosition().x-getWidth()/2 , b2body.getPosition().y - getHeight()/2);
    }

    public void defineCharacter() {
        this.health = 100;
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(200, 600);
        b2body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(50, 25);


        fixtureDef.shape = polygonShape;
        b2body.createFixture(fixtureDef);
    }



}
