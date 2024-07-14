package com.mygdx.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.Screens.FirstScreen;
import com.mygdx.game.MyGdxGame;

import static java.lang.System.exit;

public class MainMenu implements Screen {

    final MyGdxGame game;
    OrthographicCamera camera;
    Texture mainMenuScreen;


    public MainMenu(MyGdxGame game){
        this.game = game;

        mainMenuScreen = new Texture("mainMenuScreen.png");

        camera = new OrthographicCamera();
        camera.setToOrtho(false, mainMenuScreen.getWidth(), mainMenuScreen.getHeight());
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
        game.sb.draw(mainMenuScreen, 0, 0);
        game.sb.end();

        if (Gdx.input.justTouched()) {
            if(Gdx.input.getX() > 510 && Gdx.input.getX() < 1088
                    && Gdx.input.getY() > 490 && Gdx.input.getY() < 590){
                game.setScreen(new P1_Choose(game));
                dispose();
            }
            else if(Gdx.input.getX() > 1 && Gdx.input.getX() < 202
                    && Gdx.input.getY() > 8 && Gdx.input.getY() < 90){
                game.setScreen(new FirstScreen(game));
                dispose();
            }
            else if(Gdx.input.getX() > 508 && Gdx.input.getX() < 1086
                    && Gdx.input.getY() > 622 && Gdx.input.getY() < 740){
                game.setScreen(new LoadGame(game));
                dispose();
            }
            else if(Gdx.input.getX() > 510 && Gdx.input.getX() < 1088
                    && Gdx.input.getY() > 770 && Gdx.input.getY() < 872){
                dispose();
                exit(0);

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
