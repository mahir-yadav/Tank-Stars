package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.Screens.FirstScreen;

public class MyGdxGame extends Game {
	public static final String TITLE = "Tank Stars";
	public SpriteBatch sb;
	public static final int V_HEIGHT = 208;
	public static final int V_WIDTH = 400;
	public static final float PPM = 100;

	@Override
	public void create () {
		sb = new SpriteBatch();
		this.setScreen(new FirstScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		sb.dispose();
	}
}
