package com.mygdx.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.MyGdxGame;

public class Fuel extends Sprite {
    public World world;
    public Body b2body;
    private Texture fuel;

    public Fuel(World world, int posx, int posy){
        this.world = world;
        define(posx, posy);
        fuel = new Texture("Fuel.png");
        setBounds(0, 0, 10/MyGdxGame.PPM, 10/MyGdxGame.PPM);
        setRegion(fuel);
    }

    public void update(float dt){
        setPosition(b2body.getPosition().x,
                b2body.getPosition().y);
    }

    public void define(int x, int y){
        BodyDef bdef = new BodyDef();
        bdef.position.set(x/MyGdxGame.PPM, y/MyGdxGame.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef  = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(1/MyGdxGame.PPM);

        fdef.shape = shape;
        b2body.createFixture(fdef);
    }

}