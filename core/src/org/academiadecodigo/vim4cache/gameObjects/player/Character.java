package org.academiadecodigo.vim4cache.gameObjects;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import com.sun.scenario.effect.impl.prism.PrImage;
import org.academiadecodigo.vim4cache.screens.PlayScreen;
import org.academiadecodigo.vim4cache.util.VariablesUtil;

/**
 * Created by codecadet on 30/03/17.
 */
public class Character extends Sprite{
    public enum State { STANDING, UP, DOWN, LEFT, RIGHT, LEFTPUNCH, RIGHTPUNCH };
    public  State currentState;
    public State previousState;
    private int health;
    private World world;
    private Screen screen;
    private Body b2body;
    private TextureRegion characterUp;
    private TextureRegion characterDown;
    private TextureRegion characterLeft;
    private TextureRegion characterRight;
    private Animation punchRight;
    private Animation punchLeft;
    private Animation jump;
    private float stateTimer;
    private boolean runningRight;



    public Character(World world, PlayScreen playScreen) {
        super(playScreen.getAtlas().findRegion("player"));
        this.world = world;
        defineCharacter();
        characterRight = new TextureRegion(getTexture(), 312, 0, 40, 70);
        setBounds(0, 0, 60, 100 );
        setRegion(characterRight);
        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        runningRight = true;
    }

    public void update(float delta) {
        setPosition(b2body.getPosition().x-getWidth()/2 , b2body.getPosition().y - getHeight()/2);
    }

    public void defineCharacter() {
        health = 100;
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(32/2, 32/2);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();


        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(15, 30);


        fixtureDef.shape = polygonShape;
        b2body.createFixture(fixtureDef);
    }


    public int getHealth() {
        return health;
    }

    public World getWorld() {
        return world;
    }

    public Screen getScreen() {
        return screen;
    }

    public Body getB2body() {
        return b2body;
    }

    public void setHealth(int health) {
        this.health = health;
    }



}
