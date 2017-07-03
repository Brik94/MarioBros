package com.brik.mariobros.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.brik.mariobros.MarioBros;
import com.brik.mariobros.Screens.PlayScreen;

/**
 * Created by Bri on 7/3/2017.
 *
 * Mario Sprite, a Dynamic Body (moving). I will first define as a circle object and then branch
 * our from there.
 */

public class Mario extends Sprite {
    public World world;
    public Body b2body;
    private TextureRegion marioStand;

    public Mario(World world, PlayScreen screen){
        //super(screen.getAtlas().findRegion("little_mario"));
        this.world = world;
        defineMario();
        //marioStand = new TextureRegion(getTexture(), 0, 0, 16, 16); //represents a standing mario
        marioStand = new TextureRegion(screen.getAtlas().findRegion("little_mario"), 0, 0, 16, 16);
        setBounds(0, 0, 16 / MarioBros.PPM, 16 / MarioBros.PPM);
        setRegion(marioStand);
    }

    public void update(float dt){
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() /2);
    }

    public void defineMario(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(32 / MarioBros.PPM, 32 / MarioBros.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody; //Moving Body, MARIO! :D
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5 / MarioBros.PPM);

        fdef.shape = shape;
        b2body.createFixture(fdef);
    }
}
