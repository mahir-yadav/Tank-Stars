package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

public class Mainmenu implements Screen {
    final tank game;
    private Texture backgroundImage;
    private TextureRegion backgroundTexture;
    OrthographicCamera camera;

    public Mainmenu(final tank game) {
        this.game = game;
        backgroundImage = new Texture(Gdx.files.internal("menu.png"));
        backgroundTexture = new TextureRegion(backgroundImage, 0, 0, 1920, 1080);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1920, 1080);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 0);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(backgroundTexture, 0,0, 1920, 1080);
        game.batch.end();
        if(Gdx.input.justTouched()){
            if (Gdx.input.isTouched()) {
                Vector3 touchPos = new Vector3();
                touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
//                System.out.println(touchPos.x);
//                System.out.println(touchPos.y);
                if((touchPos.x>475 && touchPos.x<725) && (touchPos.y>30 && touchPos.y<90)){
                    game.setScreen(new selection_tanks(game));
                }
                if((touchPos.x>475 && touchPos.x<725) && (touchPos.y>110 && touchPos.y<170)){
                    game.setScreen(new selection_tanks(game));
                }
                if((touchPos.x>475 && touchPos.x<725) && (touchPos.y>190 && touchPos.y<250)){
                    game.setScreen(new resume_screen(game));
                }
                if((touchPos.x>475 && touchPos.x<725) && (touchPos.y>270 && touchPos.y<330)){
                    Gdx.app.exit();
                }

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
