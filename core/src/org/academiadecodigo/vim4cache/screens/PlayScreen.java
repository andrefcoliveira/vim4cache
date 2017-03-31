package org.academiadecodigo.vim4cache.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import org.academiadecodigo.vim4cache.CaGame;
import org.academiadecodigo.vim4cache.gameObjects.player.Character;
import org.academiadecodigo.vim4cache.gameObjects.enemy.MockEnemy;
import org.academiadecodigo.vim4cache.scenes.Hud;
import org.academiadecodigo.vim4cache.tools.B2WorldCreator;
import org.academiadecodigo.vim4cache.util.VariablesUtil;

/**
 * Created by codecadet on 30/03/17.
 */
public class PlayScreen implements Screen{

    private CaGame caGame;
    private boolean debug = false;
    private boolean punching = false;
    private boolean dayScreen=false;

    private OrthographicCamera gameCam;
    private Viewport gamePort;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private Box2DDebugRenderer b2rd;
    private Days days;

    private OrthogonalTiledMapRenderer renderer;
    private Character player;
    private MockEnemy enemy;
    private MockEnemy enemy1;
    private MockEnemy enemy2;
    private MockEnemy enemy3;
    private World world;
    private Hud hud;
    private TextureAtlas atlas;
    private TextureAtlas enemyAtlas;
    private Texture menuEnd;

    public PlayScreen(CaGame caGame) {

        atlas = new TextureAtlas("characterAnimations.pack");
        enemyAtlas = new TextureAtlas("Enemies.pack");
        this.caGame = caGame;
        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(VariablesUtil.V_WIDTH / VariablesUtil.PPM, VariablesUtil.V_HEIGHT / VariablesUtil.PPM, gameCam);
        hud = new Hud(caGame.batch);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("level1.tmx"); // tmx file

        renderer = new OrthogonalTiledMapRenderer(map);
        gameCam.position.set(gamePort.getWorldWidth() / VariablesUtil.PPM, gamePort.getWorldHeight() / VariablesUtil.PPM, 0);

        world = new World(new Vector2(0, 0), true);
        b2rd = new Box2DDebugRenderer();

        player = new Character(world, this);
        enemy = new MockEnemy(world, this);
        enemy1 = new MockEnemy(world, this);
        enemy2 = new MockEnemy(world, this);
        enemy3 = new MockEnemy(world, this);

        new B2WorldCreator(world, map);

        world.setContactListener(new WorldContactListener());
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();

        if(dayScreen){

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            caGame.setScreen(new PlayScreen(caGame));
            dayScreen=false;
        }


        if (debug) {
            b2rd.render(world, gameCam.combined);
        }

        caGame.batch.setProjectionMatrix(gameCam.combined);
        caGame.batch.begin();

        player.draw(caGame.batch);
        enemy.draw(caGame.batch);
        enemy1.draw(caGame.batch);
        enemy2.draw(caGame.batch);
        enemy3.draw(caGame.batch);
        enemy.chase(player.getBoundingRectangle().getX(), player.getBoundingRectangle().getY());
        enemy1.chase(player.getBoundingRectangle().getX(), player.getBoundingRectangle().getY());
        enemy2.chase(player.getBoundingRectangle().getX(), player.getBoundingRectangle().getY());
        enemy3.chase(player.getBoundingRectangle().getX(), player.getBoundingRectangle().getY());
        enemy.onHit(player);
        enemy1.onHit(player);
        enemy2.onHit(player);
        enemy3.onHit(player);

        caGame.batch.end();

        caGame.batch.setProjectionMatrix(hud.stage.getCamera().combined);
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

        map.dispose();
        renderer.dispose();
        world.dispose();
        b2rd.dispose();
    }

    public void update(float dt) {

        handleInput();
        collisionListener();
        player.update(dt);
        hud.update(dt);
        world.step(1 / 60f, 6, 2);
        enemy.update(dt);
        enemy1.update(dt);
        enemy2.update(dt);
        enemy3.update(dt);
        world.step(1 / 60f, 6, 2);
        gameCam.position.x = player.getB2body().getPosition().x; // Initial position

        gameCam.update();
        renderer.setView(gameCam);
    }

    private void handleInput() {

        if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            punching = true;
            player.getB2body().applyLinearImpulse(new Vector2(0, 0), player.getB2body().getWorldCenter(), true);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
            punching = false;
            player.getB2body().applyLinearImpulse(new Vector2(40, 0), player.getB2body().getWorldCenter(), true);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.A)) {
            punching = false;
            player.getB2body().applyLinearImpulse(new Vector2(-40, 0), player.getB2body().getWorldCenter(), true);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            punching = false;
            player.getB2body().applyLinearImpulse(new Vector2(0, 40), player.getB2body().getWorldCenter(), true);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            punching = false;
            player.getB2body().applyLinearImpulse(new Vector2(0, -40), player.getB2body().getWorldCenter(), true);
        }
    }

    private void collisionListener(){

        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                Fixture fixA = contact.getFixtureA();
                Fixture fixB = contact.getFixtureB();

                if(fixA.getUserData() == null || fixB.getUserData() == null){
                    return;
                }

                if (fixA.getUserData().equals("player") && fixB.getUserData().equals("door") ||
                        fixA.getUserData().equals("door") && fixB.getUserData().equals("player")) {
                        caGame.setLevel(caGame.getLevel()+1);
                        days = new Days(caGame);
                        days.setLevel(caGame.getLevel());
                        caGame.setScreen(days);
                        dayScreen = true;
                    System.out.println("oix");

                }

                if (fixA.getUserData().equals("player") && fixB.getUserData().equals("enemy") || fixA.getUserData().equals("enemy") && fixB.getUserData().equals("player")) {

                }

            }

            @Override
            public   void endContact(Contact contact) {

            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });

    }

    public World getWorld() {
        return world;
    }

    public TextureAtlas getAtlas() {
        return atlas;
    }

    public TextureAtlas getEnemyAtlas() {
        return enemyAtlas;
    }

    public boolean isPunching() {
        return punching;
    }

    public void setPunching(boolean punching) {
        this.punching = punching;
    }

    public void stopCharacter() {
        player.getB2body().setLinearVelocity(0, 0);
    }
}
