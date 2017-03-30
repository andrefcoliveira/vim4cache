package org.academiadecodigo.vim4cache.gameObjects.enemy;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Array;
import org.academiadecodigo.vim4cache.screens.PlayScreen;
import org.academiadecodigo.vim4cache.util.VariablesUtil;


/**
 * Created by codecadet on 30/03/17.
 */
public class MockEnemy extends AbstractMockEnemy {

    private float stateTime;
    private Animation walkAnimation;
    private Array<TextureRegion> frames;

    public MockEnemy(PlayScreen screen, float x, float y) {
        super(screen, x, y);
        frames = new Array<TextureRegion>();
        for (int i = 0; i < 2; i++) {
            frames.add(new TextureRegion(screen.getAtlas().findRegion("MockEnemy"), i * 16, 0, 16, 16));
        }

        walkAnimation = new Animation(0.4f, frames);
        stateTime = 0;

        setBounds(getX(), getY(), 16 / VariablesUtil.PPM, 16 / VariablesUtil.PPM);
    }

    public void update(float delta) {

        stateTime += delta;
        setPosition(b2Body.getPosition().x - getWidth() / 2, b2Body.getPosition().y - getHeight() / 2);
        setRegion(walkAnimation.getKeyFrame(stateTime, true));
    }

    private void setRegion(Object keyFrame) {

    }

    @Override
    protected void defineEnemy() {

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(32 / VariablesUtil.PPM, 32 / VariablesUtil.PPM);
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
        b2Body.createFixture(fixtureDef);
    }
}
