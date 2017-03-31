package org.academiadecodigo.vim4cache.tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import org.academiadecodigo.vim4cache.util.VariablesUtil;

/**
 * Created by codecadet on 30/03/17.
 */
public class B2WorldCreator {

    public B2WorldCreator(World world, TiledMap map){
        
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();
        Body body;
        
        for(MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth()/2)/ VariablesUtil.PPM, (rect.getY() + rect.getHeight()/2)/VariablesUtil.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth()/2 / VariablesUtil.PPM, rect.getHeight()/2/ VariablesUtil.PPM);
            fixtureDef.shape = shape;
            body.createFixture(fixtureDef);
        }

        for(MapObject object : map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth()/2)/ VariablesUtil.PPM, (rect.getY() + rect.getHeight()/2)/VariablesUtil.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth()/2 / VariablesUtil.PPM, rect.getHeight()/2/ VariablesUtil.PPM);
            fixtureDef.shape = shape;
            body.createFixture(fixtureDef);
        }

        for(MapObject object : map.getLayers().get(7).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth()/2)/ VariablesUtil.PPM, (rect.getY() + rect.getHeight()/2)/VariablesUtil.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth()/2 / VariablesUtil.PPM, rect.getHeight()/2/ VariablesUtil.PPM);
            fixtureDef.shape = shape;
            body.createFixture(fixtureDef);


        }
       
    }

}
