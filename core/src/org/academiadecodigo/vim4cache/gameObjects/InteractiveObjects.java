package org.academiadecodigo.vim4cache.gameObjects;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import org.academiadecodigo.vim4cache.util.VariablesUtil;

/**
 * Created by apm on 31-03-2017.
 */
public abstract class InteractiveObjects {

    protected World world;
    protected TiledMap map;
    protected TiledMapTile tile;
    protected Rectangle bounds;
    protected Body body;
    protected Fixture fixture;

    public InteractiveObjects(World world, TiledMap map, Rectangle bounds) {
        this.world = world;
        this.map = map;
        this.bounds = bounds;

        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set((bounds.getX() + bounds.getWidth()/2)/ VariablesUtil.PPM, (bounds.getY() + bounds.getHeight()/2)/VariablesUtil.PPM);

        body = world.createBody(bdef);

        shape.setAsBox(bounds.getWidth()/2 / VariablesUtil.PPM, bounds.getHeight()/2/ VariablesUtil.PPM);
        fdef.shape = shape;
        fixture = body.createFixture(fdef);
    }

    public abstract void onHit();

    public void setCategoryFilter(short filterBit){
        Filter filter = new Filter();
        filter.categoryBits = filterBit;
        fixture.setUserData(this);
    }
}
