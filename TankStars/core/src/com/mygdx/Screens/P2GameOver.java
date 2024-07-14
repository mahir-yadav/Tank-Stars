package com.mygdx.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MyGdxGame;

import static java.lang.System.exit;

public class P2GameOver implements Screen {

    final MyGdxGame game;
    OrthographicCamera camera;
    Texture P2GameOver;


    public P2GameOver(MyGdxGame game){
        this.game = game;

        P2GameOver = new Texture("P2GameOver.png");

        camera = new OrthographicCamera();
        camera.setToOrtho(false, P2GameOver.getWidth(), P2GameOver.getHeight());
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
        game.sb.draw(P2GameOver, 0, 0);
        game.sb.end();

        if (Gdx.input.justTouched()) exit(0);
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
