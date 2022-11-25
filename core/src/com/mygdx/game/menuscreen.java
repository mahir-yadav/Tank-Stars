package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

public class menuscreen implements Screen {
    final tank game;
    private Texture backgroundImage;
    private TextureRegion backgroundTexture;
    OrthographicCamera camera;

    public menuscreen(final tank game) {
        this.game = game;
        backgroundImage = new Texture(Gdx.files.internal("menuingame.png"));
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
//            System.out.println(touchPos.x);
//            System.out.println(touchPos.y);
                if(touchPos.x>495 && touchPos.x<760 && touchPos.y>145 && touchPos.y<205){
                    game.setScreen(new GameScreen (game));
                }
                if(touchPos.x>495 && touchPos.x<760 && touchPos.y>225 && touchPos.y<285){
                    game.setScreen(new SaveGame(game));
                }
                if(touchPos.x>495 && touchPos.x<760 && touchPos.y>310 && touchPos.y<368){
                    game.setScreen(new Sound(game));
                }
                if(touchPos.x>495 && touchPos.x<760 && touchPos.y>375 && touchPos.y<454){
                    game.setScreen(new ExitGame(game));
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
