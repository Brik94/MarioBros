package com.brik.mariobros.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.brik.mariobros.MarioBros;
import com.brik.mariobros.Sprites.Brick;
import com.brik.mariobros.Sprites.Coin;

/**
 * Created by Bri on 7/3/2017.
 *
 * Sets up the Fixtures or objects that Mario will interact with.
 * Note: These are all Static objects as they don't move.
 */

public class B2WorldCreator {
    public B2WorldCreator(World world, TiledMap map){
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        //Create ground bodies/fixtures
        for(MapObject object: map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) { //Ground layer (2)
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody; //non moving
            bdef.position.set((rect.getX() + rect.getWidth()/2) / MarioBros.PPM, (rect.getY() + rect.getHeight()/2) / MarioBros.PPM);
            body = world.createBody(bdef); //Add to our Box2d world

            shape.setAsBox(rect.getWidth()/2 / MarioBros.PPM, rect.getHeight()/2 / MarioBros.PPM);
            fdef.shape = shape;
            body.createFixture(fdef); //Add body to our fixture.
        }

        //create pipe bodies/fixtures
        for(MapObject object: map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) { //Pipes layer (3)
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody; //non moving
            bdef.position.set((rect.getX() + rect.getWidth()/2) / MarioBros.PPM, (rect.getY() + rect.getHeight()/2) / MarioBros.PPM);
            body = world.createBody(bdef); //Add to our Box2d world

            shape.setAsBox(rect.getWidth()/2 / MarioBros.PPM, rect.getHeight()/2 / MarioBros.PPM);
            fdef.shape = shape;
            body.createFixture(fdef); //Add body to our fixture.
        }

        //Create brick bodies/fixtures
        for(MapObject object: map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)) { //Brick layer (5)
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Brick(world, map, rect);
        }

        //Create coin bodies/fixtures
        for(MapObject object: map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) { //Coin layer (5)
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Coin(world, map, rect);
        }
    }
}
