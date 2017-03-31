package org.academiadecodigo.vim4cache.tools;

import com.badlogic.gdx.physics.box2d.*;
import org.academiadecodigo.vim4cache.gameObjects.InteractiveObjects;

/**
 * Created by apm on 31-03-2017.
 */
public class WorldContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
