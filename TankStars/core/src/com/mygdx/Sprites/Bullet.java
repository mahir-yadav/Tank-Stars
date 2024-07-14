package com.mygdx.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.Screens.PlayScreen;
import com.mygdx.game.MyGdxGame;

public class Bullet extends Sprite implements GameObject{
    public World world;
    public Body b2body;
    private TextureRegion bullet;

    public Bullet(World world, PlayScreen screen, String name, int x, int y, int posx, int posy){
        super(screen.getAtlas().findRegion(name));
        this.world = world;
        define(posx, posy);
        bullet = new TextureRegion(getTexture(), x, y, 16, 16);
        setBounds(x, y, 6/MyGdxGame.PPM, 6/MyGdxGame.PPM);
        setRegion(bullet);
    }

    public void update(float dt){
        setPosition(b2body.getPosition().x, b2body.getPosition().y);
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