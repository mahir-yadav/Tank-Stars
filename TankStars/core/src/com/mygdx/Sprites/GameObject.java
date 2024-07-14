package com.mygdx.Sprites;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;

public interface GameObject {
    public void update(float dt);
    public void define(int x,int y);
}