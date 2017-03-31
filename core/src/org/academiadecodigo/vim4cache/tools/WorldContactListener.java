package org.academiadecodigo.vim4cache.tools;

import com.badlogic.gdx.physics.box2d.*;
import org.academiadecodigo.vim4cache.gameObjects.InteractiveObjects;
import org.academiadecodigo.vim4cache.gameObjects.player.Character;

/**
 * Created by apm on 31-03-2017.
 */
public class WorldContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if(fixA.getUserData() == null || fixB.getUserData() == null){
            return;
        }

        if (fixA.getUserData().equals("player") && fixB.getUserData().equals("door") ||
                fixA.getUserData().equals("door") && fixB.getUserData().equals("player")) {
            
        }

        if (fixA.getUserData().equals("player") && fixB.getUserData().equals("enemy") || fixA.getUserData().equals("enemy") && fixB.getUserData().equals("player")) {

        }



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
