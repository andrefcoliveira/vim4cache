package org.academiadecodigo.vim4cache.gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import org.academiadecodigo.vim4cache.util.VariablesUtil;

/**
 * Created by apm on 31-03-2017.
 */
public class FinalDoor extends InteractiveObjects {

    private boolean isFinal = false;

    public FinalDoor(World world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds);
        fixture.setUserData("door");

    }

    @Override
    public void onHit() {
        Gdx.app.log("Coin", "Collision");
    }
}
