package org.academiadecodigo.vim4cache.gameObjects.enemy;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import org.academiadecodigo.vim4cache.gameObjects.player.Character;
import org.academiadecodigo.vim4cache.screens.PlayScreen;
import org.academiadecodigo.vim4cache.util.VariablesUtil;

/**
 * Created by codecadet on 30/03/17.
 */
public class MockEnemy extends Sprite {
    public enum EnemyState {STANDING, UP, DOWN, LEFT, RIGHT,}

    public EnemyState currentState;
    public EnemyState previousState;
    private final World world;
    private float stateTime;
    private Array<TextureRegion> frames;
    private TextureRegion enemyStand;
    private Body b2Body;
    private Vector2 velocity;
    private PlayScreen playScreen;
    private Animation enemyUp;
    private Animation enemyDown;
    private Animation enemyRight;
    private Animation enemyLeft;
    private boolean runningRight;


    // Awesome randomness with the initial position of the enemies
    public MockEnemy(World world, PlayScreen screen) {
        super(screen.getEnemyAtlas().findRegion("enemy)"));
        this.playScreen = screen;
        this.world = world;
        velocity = new Vector2((int) (Math.random() * 100), (int) (Math.random() * 100));

        defineEnemy();
        enemyStand = new TextureRegion(getTexture(), 10, 10, 17, 40);
        setBounds(0, 0, 60, 100);
        setRegion(enemyStand);
        setBounds((int) Math.random() * 100, (int) Math.random() * 100, 160 / VariablesUtil.PPM, 160 / VariablesUtil.PPM);

        stateTime = 0;
        runningRight = true;
        moveUpAnimation();
        moveDownAnimation();
        moveLeftAnimation();
        moveRightAnimation();

    }

    public void moveUpAnimation() {
        Array<TextureRegion> frames = new Array<TextureRegion>();
        for (int i = 0; i < 5; i++)
            frames.add(new TextureRegion(getTexture(), i * 17, 0, 40, 70));
        enemyUp = new Animation(0.2f, frames);
        frames.clear();
    }

    public void moveDownAnimation() {
        Array<TextureRegion> frames = new Array<TextureRegion>();
        for (int i = 0; i < 5; i++)
            frames.add(new TextureRegion(getTexture(), i * 17, 0, 40, 70));
        enemyDown = new Animation(0.2f, frames);
        frames.clear();
    }

    public void moveRightAnimation() {
        Array<TextureRegion> frames = new Array<TextureRegion>();
        for (int i = 0; i < 5; i++)
            frames.add(new TextureRegion(getTexture(), i * 17, 0, 17, 40));
        enemyRight = new Animation(0.2f, frames);
        frames.clear();
    }

    public void moveLeftAnimation() {
        Array<TextureRegion> frames = new Array<TextureRegion>();
        for (int i = 0; i < 5; i++)
            frames.add(new TextureRegion(getTexture(), i * 17, 0, 17, 40));
        enemyLeft = new Animation(0.2f, frames);
        frames.clear();
    }

    public void defineEnemy() {

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set((int) (Math.random() * 1000) / VariablesUtil.PPM, (int) (Math.random() * 100) / VariablesUtil.PPM);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        b2Body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(6 / VariablesUtil.PPM);
        //fixtureDef.filter.categoryBits = VariablesUtil.ENEMY_BIT;
        /*fixtureDef.filter.maskBits = VariablesUtil.GROUND_BIT |
                VariablesUtil.ENEMY_BIT |
                VariablesUtil.OBJECT_BIT | VariablesUtil.CHARACTER_BIT;*/

        fixtureDef.shape = shape;
        bodyDef.linearVelocity.set(new Vector2(0, 0));

        b2Body.createFixture(fixtureDef).setUserData("enemy");
        b2Body.setActive(true);
    }

    public void update(float delta) {
        b2Body.setLinearVelocity(velocity);

        setPosition(b2Body.getPosition().x - getWidth() / 2, b2Body.getPosition().y - getHeight() / 2);
        setRegion(getFrame(delta));
    }

    private TextureRegion getFrame(float delta) {
        currentState = getState();
        TextureRegion region = new TextureRegion();
        if (b2Body.getLinearVelocity().y < 0) {
            region = (TextureRegion) enemyUp.getKeyFrame(stateTime, true);
        }

        if (b2Body.getLinearVelocity().y > 0) {
            region = (TextureRegion) enemyDown.getKeyFrame(stateTime, true);
        }

        if (b2Body.getLinearVelocity().x < 0) {
            region = (TextureRegion) enemyLeft.getKeyFrame(stateTime, true);
        }

        if (b2Body.getLinearVelocity().x > 0) {
            region = (TextureRegion) enemyRight.getKeyFrame(stateTime, true);
        }
        if ((b2Body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()) {
            region.flip(true, false);
            runningRight = false;
        }
        if ((b2Body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()) {
            region.flip(true, false);
            runningRight = true;
        }
        stateTime = currentState == previousState ? stateTime + delta : 0;
        previousState = currentState;
        return region;
    }

    public EnemyState getState() {
        if (b2Body.getLinearVelocity().x > 0) {
            return EnemyState.RIGHT;
        }
        if (b2Body.getLinearVelocity().x < 0) {
            return EnemyState.LEFT;
        }
        if (b2Body.getLinearVelocity().y < 0) {
            return EnemyState.UP;
        }
        if (b2Body.getLinearVelocity().y > 0) {
            return EnemyState.DOWN;
        }

        return EnemyState.STANDING;
    }

    public void onHit(Character character) {

    }


    public void hitByEnemy(MockEnemy enemy) {

    }

    // Awesome randomness with the enemies velocity
    public Vector2 chase(float charX, float charY) {

        if (charX > this.getBoundingRectangle().getX()) {

            return velocity = new Vector2((charX / 20), (charY / 20));


        } else {

            return velocity = new Vector2((-charX / 20), (-charY / 20));


        }
    }

    public void setCategoryFilter(short filterBit) {
        Filter filter = new Filter();
        filter.categoryBits = filterBit;
        //fixture.setUserData(this);
    }
}
