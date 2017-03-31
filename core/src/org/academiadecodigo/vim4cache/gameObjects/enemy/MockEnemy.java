package org.academiadecodigo.vim4cache.gameObjects.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import org.academiadecodigo.vim4cache.screens.PlayScreen;
import org.academiadecodigo.vim4cache.util.VariablesUtil;


/**
 * Created by codecadet on 30/03/17.
 */
public class MockEnemy extends AbstractMockEnemy {

    public enum State {STANDING, UP, DOWN, LEFT, RIGHT, LEFTPUNCH, RIGHTPUNCH}

    private float stateTime;
    private Animation walkAnimation;
    private Array<TextureRegion> frames;

    public MockEnemy(World world, PlayScreen screen) {
        super(world, screen);
        frames = new Array<TextureRegion>();
        frames.add(new TextureRegion(new Texture("coisa.png")));
        for (int i = 0; i < 2; i++) {
            //frames.add(new TextureRegion(screen.getAtlas().findRegion("MockEnemy"), i * 16, 0, 16, 16));

        }

        stateTime = 1;

        walkAnimation = new Animation(0.4f, frames);

        setBounds(getX(), getY(), 160 / VariablesUtil.PPM, 160 / VariablesUtil.PPM);

    }


    @Override
    public void defineEnemy() {

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(300 / VariablesUtil.PPM, 32 / VariablesUtil.PPM);
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

    @Override
    public void update() {
        b2Body.setLinearVelocity(velocity);

        //setPosition(b2Body.getPosition().x - getWidth() / 2, b2Body.getPosition().y - getHeight() / 2);
        //setRegion(walkAnimation.getKeyFrame(stateTime, true));
    }

    @Override
    public void onHit(Character character) {

    }

    @Override
    public void hitByEnemy(AbstractMockEnemy enemy) {

    }

    public Vector2 chase(float charX, float charY) {

        if (charX < this.getBoundingRectangle().getX()) {
            System.out.println("bola à direita");
            return velocity = new Vector2(charX / 3, charY / 3);

        } else {
            System.out.println("bola à esquerda");
            return velocity = new Vector2(-charX / 3, charY / 3);
        }
    }
}
