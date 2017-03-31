package org.academiadecodigo.vim4cache.gameObjects.player;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import org.academiadecodigo.vim4cache.screens.PlayScreen;
import org.academiadecodigo.vim4cache.util.VariablesUtil;
import sun.management.Sensor;

/**
 * Created by codecadet on 30/03/17.
 */
public class Character extends Sprite {
    public enum State {STANDING, UP, DOWN, LEFT, RIGHT, PUNCH}

    public State currentState;
    public boolean game = true;
    public State previousState;
    private int health;
    private World world;
    private Screen screen;
    private Body b2body;
    private Animation characterUp;
    private Animation characterDown;
    private Animation characterRight;
    private Animation characterLeft;
    private TextureRegion characterStanding;
    private Animation characterPunch;
    private Animation jump;
    private float stateTimer;
    private boolean runningRight;
    private PlayScreen playScreen;
    Fixture fixture;
    private boolean movingRight = false;
    private boolean movingLeft = false;
    private boolean movingUp = false;
    private boolean movingDown = false;

    public Character(World world, PlayScreen playScreen) {
        super(playScreen.getAtlas().findRegion("player"));
        this.playScreen = playScreen;
        this.world = world;
        defineCharacter();
        characterStanding = new TextureRegion(getTexture(), 312, 0, 40, 70);
        setBounds(0, 0, 60, 100);
        setRegion(characterStanding);
        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        runningRight = true;

        Array<TextureRegion> frames = new Array();
        moveRightAnimation();
        moveLeftAnimation();
        moveUpAnimation();
        moveDownAnimation();
        attackAnimation();
    }

    public void moveUpAnimation() {
        Array<TextureRegion> frames = new Array<TextureRegion>();
        for (int i = 8; i < 12; i++)
            frames.add(new TextureRegion(getTexture(), i * 40, 0, 40, 70));
        characterUp = new Animation(0.2f, frames);
        frames.clear();
    }

    public void moveDownAnimation() {
        Array<TextureRegion> frames = new Array<TextureRegion>();
        for (int i = 8; i < 12; i++)
            frames.add(new TextureRegion(getTexture(), i * 40, 0, 40, 70));
        characterDown = new Animation(0.2f, frames);
        frames.clear();
    }

    public void moveRightAnimation() {
        Array<TextureRegion> frames = new Array<TextureRegion>();
        for (int i = 8; i < 12; i++)
            frames.add(new TextureRegion(getTexture(), i * 40, 0, 40, 70));
        characterRight = new Animation(0.2f, frames);
        frames.clear();
    }

    public void moveLeftAnimation() {
        Array<TextureRegion> frames = new Array<TextureRegion>();
        for (int i = 8; i < 12; i++)
            frames.add(new TextureRegion(getTexture(), i * 40, 0, 40, 70));
        characterLeft = new Animation(0.2f, frames);
        frames.clear();
    }

    public void attackAnimation() {
        Array<TextureRegion> frames = new Array<TextureRegion>();
        for (int i = 4; i < 8; i++)
            frames.add(new TextureRegion(getTexture(), i * 42, 80, 40, 70));
        characterPunch = new Animation(0.2f, frames);
        playScreen.setPunching(false);
        frames.clear();
    }

    public void update(float delta) {
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
        setRegion(getFrame(delta));
    }

    private TextureRegion getFrame(float delta) {
        currentState = getState();
        TextureRegion region;
        switch (currentState) {
            case UP:
                region = (TextureRegion) characterRight.getKeyFrame(stateTimer, true);
                if (characterUp.isAnimationFinished(stateTimer)) {
                    playScreen.stopCharacter();
                }
                break;
            case DOWN:
                region = (TextureRegion) characterRight.getKeyFrame(stateTimer, true);
                if (characterDown.isAnimationFinished(stateTimer)) {
                    playScreen.stopCharacter();
                }
                break;
            case RIGHT:
                region = (TextureRegion) characterRight.getKeyFrame(stateTimer, true);
                if (characterRight.isAnimationFinished(stateTimer)) {
                    playScreen.stopCharacter();
                }
                break;
            case LEFT:
                region = (TextureRegion) characterLeft.getKeyFrame(stateTimer, true);
                if (characterLeft.isAnimationFinished(stateTimer)) {
                    playScreen.stopCharacter();
                }
                break;
            case PUNCH:
                region = (TextureRegion) characterPunch.getKeyFrame(stateTimer, true);
                if (characterPunch.isAnimationFinished(stateTimer)) {
                    playScreen.setPunching(false);
                    playScreen.stopCharacter();
                }
                break;

            case STANDING:
            default:
                region = characterStanding;
        }
        if ((b2body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()) {
            region.flip(true, false);
            runningRight = false;
        }
        if ((b2body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()) {
            region.flip(true, false);
            runningRight = true;
        }
        stateTimer = currentState == previousState ? stateTimer + delta : 0;
        previousState = currentState;
        return region;
    }

    public State getState() {
        if (b2body.getLinearVelocity().x > 0) {
            movingRight = true;
            return State.RIGHT;
        }
        if (b2body.getLinearVelocity().x < 0) {
            movingRight = true;
            return State.LEFT;
        }
        if (b2body.getLinearVelocity().y < 0) {
            movingRight = true;
            return State.UP;
        }
        if (b2body.getLinearVelocity().y > 0) {
            movingRight = true;
            return State.DOWN;
        }
        if (playScreen.isPunching() == true) {
            movingRight = true;
            return State.PUNCH;
        }

        return State.STANDING;
    }

    public void defineCharacter() {
        health = 100;
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(200/ VariablesUtil.PPM, 100 / VariablesUtil.PPM);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();


        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(15, 30);
        fixtureDef.filter.categoryBits = VariablesUtil.CHARACTER_BIT;
        fixtureDef.filter.maskBits = VariablesUtil.ENEMY_BIT | VariablesUtil.OBJECT_BIT;


        fixtureDef.shape = polygonShape;
        fixture = b2body.createFixture(fixtureDef);

        EdgeShape frontSensor = new EdgeShape();
        frontSensor.set(new Vector2(-30,40) , new Vector2(30,-40));
        fixtureDef.shape= frontSensor;

        //b2body.createFixture(fixtureDef).setUserData("player");


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

    public int characterAttackDamage() {
        if (playScreen.isPunching() == true) {
            Rectangle rectangel = new Rectangle();
            rectangel.set(b2body.getPosition().x + b2body.getPosition().x, b2body.getPosition().y, 50, 80);

        }
        return 0;
    }



}
