package com.mygdx.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.Screens.MainMenu;
import com.mygdx.game.MyGdxGame;

public class P1_Choose implements Screen {

    final MyGdxGame game;
    OrthographicCamera camera;
    Texture P1Choose;

    public P1_Choose(MyGdxGame game) {
        this.game = game;

        P1Choose = new Texture("P1Choose.png");

        camera = new OrthographicCamera();
        camera.setToOrtho(false, P1Choose.getWidth(), P1Choose.getHeight());
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
        game.sb.draw(P1Choose, 0, 0);
        game.sb.end();

        if (Gdx.input.justTouched()) {
            if (Gdx.input.getX() > 92 && Gdx.input.getX() < 478
                    && Gdx.input.getY() > 342 && Gdx.input.getY() < 725) {
                game.setScreen(new P2_Choose(game));
                dispose();
            }
            else if (Gdx.input.getX() > 606 && Gdx.input.getX() < 992
                    && Gdx.input.getY() > 342 && Gdx.input.getY() < 725) {
                game.setScreen(new P2_Choose(game));
                dispose();
            }
            else if(Gdx.input.getX() > 1125 && Gdx.input.getX() < 1503
                    && Gdx.input.getY() > 342 && Gdx.input.getY() < 725){
                game.setScreen(new P2_Choose(game));
                dispose();
            }
            else if(Gdx.input.getX() > 1320 && Gdx.input.getX() < 1563
                    && Gdx.input.getY() > 776 && Gdx.input.getY() < 863){
                game.setScreen(new MainMenu(game));
                dispose();

            }

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
