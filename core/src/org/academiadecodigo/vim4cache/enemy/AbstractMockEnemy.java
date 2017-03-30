package org.academiadecodigo.vim4cache.enemy;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import org.academiadecodigo.vim4cache.screens.PlayScreen;

/**
 * Created by codecadet on 30/03/17.
 */
public abstract class AbstractMockEnemy extends Sprite{

    protected World world;
    protected PlayScreen screen;
    public Body b2Body;

    public AbstractMockEnemy(){}

    public AbstractMockEnemy(PlayScreen screen, float x, float y){
        this.world = screen.getWorld();
        this.screen = screen;
        setPosition(x, y);
        defineEnemy();
    }

    protected abstract void defineEnemy();
}
