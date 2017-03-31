package org.academiadecodigo.vim4cache.gameObjects.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import org.academiadecodigo.vim4cache.screens.PlayScreen;
import org.academiadecodigo.vim4cache.util.VariablesUtil;

/**
 * Created by codecadet on 30/03/17.
 */
public class MockEnemy extends Sprite {
    public enum State {STANDING, UP, DOWN, LEFT, RIGHT, PUNCH}

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

    // Awesome randomness with the initial position of the enemies
    public MockEnemy(World world, PlayScreen screen) {
        super(screen.getEnemyAtlas().findRegion("enemy)"));
        this.playScreen = screen;
        this.world = world;
        velocity = new Vector2((int) (Math.random() * 100), (int) (Math.random() * 100));

        defineEnemy();
        enemyStand = new TextureRegion(getTexture(), 0, 0, 17, 40);
        setBounds(0, 0, 60 , 100 );
        setRegion(enemyStand);
        setBounds(getX(), getY(), 160 / VariablesUtil.PPM, 160 / VariablesUtil.PPM);

        stateTime = 0;

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
            frames.add(new TextureRegion(getTexture(), i * 40, 0, 17, 40));
        enemyLeft = new Animation(0.2f, frames);
        frames.clear();
    }




    public void defineEnemy() {

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(500 / VariablesUtil.PPM, 100 / VariablesUtil.PPM);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        b2Body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(6 / VariablesUtil.PPM);
        fixtureDef.filter.categoryBits = VariablesUtil.ENEMY_BIT;
        fixtureDef.filter.maskBits = VariablesUtil.GROUND_BIT |
                VariablesUtil.ENEMY_BIT |
                VariablesUtil.OBJECT_BIT |
                VariablesUtil.CHARACTER_BIT;

        fixtureDef.shape = shape;
        bodyDef.linearVelocity.set(new Vector2(0, 0));

        b2Body.createFixture(fixtureDef);
        b2Body.setActive(true);
    }

    public void update() {
        b2Body.setLinearVelocity(velocity);

        setPosition(b2Body.getPosition().x - getWidth() / 2, b2Body.getPosition().y - getHeight() / 2);
        //setRegion(walkAnimation.getKeyFrame(stateTime, true));
    }


    public void onHit(Character character) {

    }


    public void hitByEnemy(AbstractMockEnemy enemy) {

    }

    // Awesome randomness with the enemies velocity
    public Vector2 chase(float charX, float charY) {

        if (charX > this.getBoundingRectangle().getX()) {

            return velocity = new Vector2((charX / 10), (charY / 10));


        } else {

            return velocity = new Vector2((-charX / 10), (-charY / 10));


        }
    }
}
