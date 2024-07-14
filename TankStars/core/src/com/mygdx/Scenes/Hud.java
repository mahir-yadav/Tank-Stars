package com.mygdx.Scenes;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.Screens.P1GameOver;
import com.mygdx.Screens.P2GameOver;
import com.mygdx.game.MyGdxGame;


public class Hud implements Screen {

    public Stage stage;
    private Viewport viewport;

    private MyGdxGame game;

    private Integer score_P1;
    private Integer score_P2;
    private float angle1;
    private float angle2;
    private int power1;
    private int power2;
    private int fuel1;
    private int fuel2;

    private Label p1Label;
    private Label p2Label;
    private Label p1ScoreLabel;
    private Label p2ScoreLabel;
    private Label p1AngleLabel;
    private Label p2AngleLabel;
    private Label p1Angle;
    private Label p2Angle;
    private Label p1PowerLabel;
    private Label p2PowerLabel;
    private Label p1Power;
    private Label p2Power;
    private Label p1FuelLabel;
    private Label p2FuelLabel;
    private Label p1Fuel;
    private Label p2Fuel;

    public Hud(MyGdxGame game){
        score_P1 = 100;
        score_P2 = 100;
        angle1 = 0f;
        angle2 = 0f;
        power1 = 200;
        power2 = 200;
        fuel1 = 200;
        fuel2 = 200;

        this.game = game;

        viewport = new FitViewport(MyGdxGame.V_WIDTH,
                MyGdxGame.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, game.sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        p1ScoreLabel = new Label(String.format("%03d", score_P1),
                new Label.LabelStyle(new BitmapFont(), Color.BLUE));
        p2ScoreLabel = new Label(String.format("%03d", score_P2),
                new Label.LabelStyle(new BitmapFont(), Color.BLUE));
        p1Label = new Label("PLAYER 1",
                new Label.LabelStyle(new BitmapFont(), Color.GREEN));
        p2Label = new Label("PLAYER 2",
                new Label.LabelStyle(new BitmapFont(), Color.GREEN));
        p1AngleLabel = new Label(String.format("%02f", angle1),
                new Label.LabelStyle(new BitmapFont(), Color.BLUE));
        p2AngleLabel = new Label(String.format("%02f", angle2),
                new Label.LabelStyle(new BitmapFont(), Color.BLUE));
        p1Angle = new Label("PLAYER 1 Angle",
                new Label.LabelStyle(new BitmapFont(), Color.GREEN));
        p2Angle = new Label("PLAYER 2 Angle",
                new Label.LabelStyle(new BitmapFont(), Color.GREEN));
        p1PowerLabel = new Label(String.format("%03d", power1),
                new Label.LabelStyle(new BitmapFont(), Color.BLUE));
        p2PowerLabel = new Label(String.format("%03d", power2),
                new Label.LabelStyle(new BitmapFont(), Color.BLUE));
        p1Power = new Label("PLAYER 1 Power",
                new Label.LabelStyle(new BitmapFont(), Color.GREEN));
        p2Power = new Label("PLAYER 2 Power",
                new Label.LabelStyle(new BitmapFont(), Color.GREEN));
        p1FuelLabel = new Label(String.format("%03d", fuel1),
                new Label.LabelStyle(new BitmapFont(), Color.BLUE));
        p2FuelLabel = new Label(String.format("%03d", fuel2),
                new Label.LabelStyle(new BitmapFont(), Color.BLUE));
        p1Fuel = new Label("PLAYER 1 Fuel",
                new Label.LabelStyle(new BitmapFont(), Color.GREEN));
        p2Fuel = new Label("PLAYER 2 Fuel",
                new Label.LabelStyle(new BitmapFont(), Color.GREEN));

        p1Label.setFontScale(0.75f);
        p2Label.setFontScale(0.75f);
        p1Angle.setFontScale(0.5f);
        p2Angle.setFontScale(0.5f);
        p1AngleLabel.setFontScale(0.5f);
        p2AngleLabel.setFontScale(0.5f);
        p1ScoreLabel.setFontScale(0.75f);
        p2ScoreLabel.setFontScale(0.75f);
        p1PowerLabel.setFontScale(0.5f);
        p2PowerLabel.setFontScale(0.5f);
        p1Power.setFontScale(0.5f);
        p2Power.setFontScale(0.5f);
        p1FuelLabel.setFontScale(0.5f);
        p2FuelLabel.setFontScale(0.5f);
        p1Fuel.setFontScale(0.5f);
        p2Fuel.setFontScale(0.5f);

        table.add(p1Label).expandX().padTop(10).colspan(3);
        table.add(p2Label).expandX().padTop(10).colspan(3);
        table.row();
        table.add(p1ScoreLabel).expandX().colspan(3);
        table.add(p2ScoreLabel).expandX().colspan(3);
        table.row();
        table.add(p1Angle).expandX();
        table.add(p1Power).expandX();
        table.add(p1Fuel).expandX();
        table.add(p2Angle).expandX();
        table.add(p2Power).expandX();
        table.add(p2Fuel).expandX();
        table.row();
        table.add(p1AngleLabel).expandX();
        table.add(p1PowerLabel).expandX();
        table.add(p1FuelLabel).expandX();
        table.add(p2AngleLabel).expandX();
        table.add(p2PowerLabel).expandX();
        table.add(p2FuelLabel).expandX();

        stage.addActor(table);
    }

    public int addP1Score(float val, float dt){
        if(Math.abs(val)<30){
            score_P1 -= (50-(int)Math.abs(val));
            p1ScoreLabel.setText(String.format("%03d", score_P1));
        }
        render(dt);
        return score_P1;
    }

    public int addP2Score(float val, float dt){
        if(Math.abs(val)<30){
            score_P2 -= (50-(int)Math.abs(val));
            p2ScoreLabel.setText(String.format("%03d", score_P2));
        }
        render(dt);
        return score_P2;
    }

    public void setHealth1(){
        score_P1 = 100;
        p1ScoreLabel.setText(String.format("%03d", score_P1));
    }

    public void setHealth2(){
        score_P2 = 100;
        p2ScoreLabel.setText(String.format("%03d", score_P2));
    }

    public void pAngle1(float angle){
        angle1 = angle;
        p1AngleLabel.setText(String.format("%02f", angle1));
    }

    public void pAngle2(float angle){
        angle2 = angle;
        p2AngleLabel.setText(String.format("%02f", angle2));
    }

    public void pPower1(int power){
        power1 = power;
        p1PowerLabel.setText(String.format("%03d", power1));
    }

    public void pPower2(int power){
        power2 = power;
        p2PowerLabel.setText(String.format("%03d", power2));
    }

    public void pFuel1(int fuel){
        fuel1 = fuel;
        p1FuelLabel.setText(String.format("%03d", fuel1));
    }

    public void pFuel2(int fuel){
        fuel2 = fuel;
        p2FuelLabel.setText(String.format("%03d", fuel2));
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if(score_P1 <= 0){
            game.setScreen(new P2GameOver(game));
        }
        if(score_P2 <= 0){
            game.setScreen(new P1GameOver(game));
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
