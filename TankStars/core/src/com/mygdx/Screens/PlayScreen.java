package com.mygdx.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.Scenes.Hud;
import com.mygdx.Sprites.*;
import com.mygdx.game.MyGdxGame;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class PlayScreen implements Screen {

    private final MyGdxGame game;
    private final TextureAtlas atlas;

    private final OrthographicCamera gameCam;
    private final Viewport gamePort;
    private final Hud hud;

    private final World world;
    private final Box2DDebugRenderer b2dr;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private final OrthogonalTiledMapRenderer renderer;

    private final Tank tank1;
    private final Tank tank2;

    private Bullet bullet1;
    private Bullet bullet2;

    private Fuel f1;
    private Fuel f2;

    private Health h1;
    private Health h2;

    private int power1 = 200;
    private int power2 = 200;

    private int fuel1 = 200;
    private int fuel2 = 200;

    private int health1 = 100;
    private int health2 = 100;

    private String flag = "P1";

    private int fuelCheckP1 = 0;
    private int fuelCheckP2 = 0;

    private int healthCheckP1 = 0;
    private int healthCheckP2 = 0;
    private int cnt=0;
//    private int cnt2=0;
    public PlayScreen(MyGdxGame game){
        this.game = game;

        atlas = new TextureAtlas("Tanks.pack");

        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(MyGdxGame.V_WIDTH/MyGdxGame.PPM,
                MyGdxGame.V_HEIGHT/MyGdxGame.PPM, gameCam);

        hud = new Hud(game);
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("Map.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1/MyGdxGame.PPM);
        gameCam.position.set(gamePort.getWorldWidth()/2, gamePort.getWorldHeight()/2, 0);

        world = new World(new Vector2(0, -3), true);
        b2dr = new Box2DDebugRenderer();

        tank1 = new Tank(world, this, "Tank1", 1, 1, 80, 80);
        tank2 = new Tank(world, this, "Tank2", 51, 1, 350, 80);
        bullet1 = new Bullet(world, this, "Bullet1", 101, 33, 0, 0);
        bullet2 = new Bullet(world, this, "Bullet2", 101, 15, 0, 0);
        f1 = new Fuel(world, 500, -100);
        f2 = new Fuel(world, -100, -100);
        h1 = new Health(world, 500, -100);
        h2 = new Health(world, -100, -100);

        initialize();

    }

    public TextureAtlas getAtlas(){
        return atlas;
    }

    public void initialize(){
        Collection c = new ArrayList();

        for(PolylineMapObject obj : map.getLayers().get(1).getObjects().getByType(PolylineMapObject.class)) c.add(obj);

        Iterator iterator = c.iterator();

        while(iterator.hasNext()){
            Shape shape;
            shape = createPolyLine((PolylineMapObject) iterator.next());
            Body body;
            BodyDef bdef = new BodyDef();
            bdef.type = BodyDef.BodyType.StaticBody;
            body = world.createBody(bdef);
            body.createFixture(shape, 1.0f);
            shape.dispose();
        }
    }

    private static ChainShape createPolyLine(PolylineMapObject polyline){
        float[] vertices = polyline.getPolyline().getTransformedVertices();
        Vector2[] worldVertices = new Vector2[vertices.length/2];

        for(int i=0; i<worldVertices.length; i++){
            worldVertices[i] = new Vector2(vertices[i*2]/MyGdxGame.PPM, vertices[i*2 + 1]/MyGdxGame.PPM);
        }
        ChainShape cs = new ChainShape();
        cs.createChain(worldVertices);
        return cs;
    }

    public void bulletCollisions(float dt){

        if(bullet1.b2body.getPosition().x > 2 && bullet1.b2body.getPosition().y < 0.55 && cnt%2==0 ) {
            health2 = hud.addP2Score((bullet1.b2body.getPosition().x - tank2.getX())*100, dt);
            bullet1.b2body.setTransform(-10,-10,0);
            bullet1.b2body.setActive(false);
        }
        if(bullet2.b2body.getPosition().x < 2 && bullet2.b2body.getPosition().y < 0.55 && cnt%2!=0){
            health1 = hud.addP1Score((bullet2.b2body.getPosition().x - tank1.getX())*100, dt);
            bullet2.b2body.setTransform(-10,-10,0);
            bullet2.b2body.setActive(false);
        }
        if(health1 < 30 && healthCheckP1 == 0){
            h1 = new Health(world, 100, 200);
            healthCheckP1 = 1;
        }
        if(health2 < 30 && healthCheckP2 == 0){
            h2 = new Health(world, 300, 200);
            healthCheckP2 = 1;
        }
    }

    public void fuelCollisions(){
        if(Math.abs((f1.b2body.getPosition().x - tank1.b2body.getPosition().x)*100) < 5 &&
                Math.abs((f1.b2body.getPosition().y - tank1.b2body.getPosition().y)*100) < 5){
            fuel1 = 200;
            hud.pFuel1(fuel1);
            f1.b2body.setTransform(500,-100,0);
            f1.b2body.setActive(false);
        }
        if((Math.abs(f2.b2body.getPosition().x - tank2.b2body.getPosition().x)*100) < 5 &&
                Math.abs((f2.b2body.getPosition().y - tank2.b2body.getPosition().y)*100) < 5){
            fuel2 = 200;
            hud.pFuel2(fuel2);
            f2.b2body.setTransform(-100,-100,0);
            f2.b2body.setActive(false);
        }
    }

    public void healthCollisions(){
        if(Math.abs((h1.b2body.getPosition().x - tank1.b2body.getPosition().x)*100) < 5 &&
                Math.abs((h1.b2body.getPosition().y - tank1.b2body.getPosition().y)*100) < 5){
            health1 = 100;
            hud.setHealth1();
            h1.b2body.setTransform(500,-100,0);
            h1.b2body.setActive(false);
        }
        if((Math.abs(h2.b2body.getPosition().x - tank2.b2body.getPosition().x)*100) < 5 &&
                Math.abs((h2.b2body.getPosition().y - tank2.b2body.getPosition().y)*100) < 5){
            health2 = 100;
            hud.setHealth2();
            h2.b2body.setTransform(-100,-100,0);
            h2.b2body.setActive(false);
        }
    }

    public void handleInput(){

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && tank1.b2body.getLinearVelocity().x <= 0.37
                && cnt%2==0){
            fuel1 -= 2;
            if(fuel1 <= 40 && fuelCheckP1 == 0) {
                f1 = new Fuel(world, 50, 200);
                fuelCheckP1 = 1;
            }
            if(fuel1 <= 0) fuel1 = 0;
            hud.pFuel1(fuel1);

            if(fuel1 > 0) tank1.b2body.applyLinearImpulse(new Vector2(0.1f, 0), tank1.b2body.getWorldCenter(), true);
//            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) flag = "P2";
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && tank1.b2body.getLinearVelocity().x >= -0.37
                & cnt%2==0) {
            fuel1 -= 2;
            if(fuel1 <= 40 && fuelCheckP1 == 0) {
                f1 = new Fuel(world, 50, 200);
                fuelCheckP1 = 1;
            }
            if(fuel1 <= 0) fuel1 = 0;
            hud.pFuel1(fuel1);
            if(fuel1 > 0) tank1.b2body.applyLinearImpulse(new Vector2(-0.1f, 0), tank1.b2body.getWorldCenter(), true);
//            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) flag = "P2";
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP) && cnt%2==0){
            tank1.b2body.setTransform(tank1.b2body.getPosition(), (float) ((tank1.b2body.getAngle())+0.017));
            hud.pAngle1((float)(((tank1.b2body.getAngle()) * 57.295))%360);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && cnt%2==0) {
            tank1.b2body.setTransform(tank1.b2body.getPosition(), (float) ((tank1.b2body.getAngle())-0.017));
            hud.pAngle1((float)(((tank1.b2body.getAngle()) * 57.295))%360);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.Z) && cnt%2==0) {
            power1 += 2;
            if(power1 >= 500) power1 = 200;
            hud.pPower1(power1);

        }
        if (Gdx.input.isKeyPressed(Input.Keys.X) && cnt%2==0) {
            power1 -= 2;
            if(power1 <= 200) power1 = 500;
            hud.pPower1(power1);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER) && cnt%2==0) {
            bullet1 = new Bullet(world, this, "Bullet1", 101, 33, 70, 80);
            bullet1.b2body.setTransform(tank1.b2body.getPosition(), tank1.b2body.getAngle());
            Vector2 force = new Vector2((float) (Math.cos(bullet1.b2body.getAngle())*power1),
                    (float) (Math.sin(bullet1.b2body.getAngle())*power1));
            bullet1.b2body.applyForce(force, bullet1.b2body.getPosition(), true);
            cnt++;
//            if(cnt%2!=0) flag="P2";



        }

        if (Gdx.input.isKeyPressed(Input.Keys.D) && tank2.b2body.getLinearVelocity().x <= 0.37 && cnt%2!=0){
            fuel2 -= 2;
            if(fuel2 <= 40 && fuelCheckP2 == 0) {
                f2 = new Fuel(world, 350, 200);
                fuelCheckP2 = 1;
            }
            if(fuel2 <= 0) fuel2 = 0;
            hud.pFuel2(fuel2);
            if(fuel2 > 0) tank2.b2body.applyLinearImpulse(new Vector2(0.1f, 0), tank2.b2body.getWorldCenter(), true);
//            if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) flag = "P1";

        }

        if (Gdx.input.isKeyPressed(Input.Keys.A) && tank2.b2body.getLinearVelocity().x >= -0.37
                && cnt%2!=0) {
            fuel2 -= 2;
            if(fuel2 <= 40 && fuelCheckP2 == 0) {
                f2 = new Fuel(world, 350, 200);
                fuelCheckP2 = 1;
            }
            if(fuel2 <= 0) fuel2 = 0;
            hud.pFuel2(fuel2);
            if(fuel2 > 0) tank2.b2body.applyLinearImpulse(new Vector2(-0.1f, 0), tank2.b2body.getWorldCenter(), true);
//            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) flag = "P1";
        }

        if (Gdx.input.isKeyPressed(Input.Keys.W) && cnt%2!=0){
            tank2.b2body.setTransform(tank2.b2body.getPosition(), (float) ((tank2.b2body.getAngle())+0.017));
            hud.pAngle2((float)((((tank2.b2body.getAngle()) * 57.295))+360)%360);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S) && cnt%2!=0) {
            tank2.b2body.setTransform(tank2.b2body.getPosition(), (float) ((tank2.b2body.getAngle())-0.017));
            hud.pAngle2((float)((((tank2.b2body.getAngle()) * 57.295))+360)%360);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.Z) && cnt%2!=0) {
            power2 += 2;
            if(power2 >= 500) power2 = 200;
            hud.pPower2(power2);

        }
        if (Gdx.input.isKeyPressed(Input.Keys.X) && cnt%2!=0) {
            power2 -= 2;
            if(power2 <= 200) power2 = 500;
            hud.pPower2(power2);

        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.SHIFT_LEFT) && cnt%2!=0) {
            bullet2 = new Bullet(world, this, "Bullet2", 101, 15, 360, 80);
            bullet2.b2body.setTransform(tank2.b2body.getPosition().add((float) -0.005, 0), (float) (3.14-tank2.b2body.getAngle()));
            Vector2 force = new Vector2((float) (Math.cos(bullet2.b2body.getAngle())*power2),
                    (float) (Math.sin(bullet2.b2body.getAngle())*power2));
            bullet2.b2body.applyForce(force, bullet2.b2body.getPosition(), true);
            cnt++;
//            if(cnt%2==0) flag="P1";

        }
    }

    public void update(float dt){

        world.step(1/60f, 6, 2);
        handleInput();
        tank1.update(dt);
        tank2.update(dt);
        f1.update(dt);
        f2.update(dt);
        h1.update(dt);
        h2.update(dt);
        bullet1.update(dt);
        bullet2.update(dt);
        bulletCollisions(dt);
        fuelCollisions();
        healthCollisions();
        gameCam.update();
        renderer.setView(gameCam);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();

//        b2dr.render(world, gameCam.combined);

        game.sb.setProjectionMatrix(gameCam.combined);
        game.sb.begin();
        tank1.draw(game.sb);
        tank2.draw(game.sb);
        bullet1.draw(game.sb);
        bullet2.draw(game.sb);
        f1.draw(game.sb);
        f2.draw(game.sb);
        h1.draw(game.sb);
        h2.draw(game.sb);
        game.sb.end();

        game.sb.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
        
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
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
