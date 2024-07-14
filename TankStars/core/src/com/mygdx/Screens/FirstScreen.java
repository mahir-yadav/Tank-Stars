package com.mygdx.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MyGdxGame;

public class FirstScreen implements Screen {

    final MyGdxGame game;
    OrthographicCamera camera;
    Texture firstScreen;

    public FirstScreen(MyGdxGame game){
        this.game = game;

        firstScreen = new Texture("firstScreen.png");

        camera = new OrthographicCamera();
        camera.setToOrtho(false, firstScreen.getWidth(), firstScreen.getHeight());
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        ScreenUtils.clear(0, 0, 0, 1);

        camera.update();
        game.sb.setProjectionMatrix(camera.combined);

        game.sb.begin();
        game.sb.draw(firstScreen, 0, 0);
        game.sb.end();

        if (Gdx.input.justTouched()) {
            game.setScreen(new MainMenu(game));
            dispose();
        }

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
