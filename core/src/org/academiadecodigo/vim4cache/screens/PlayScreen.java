package org.academiadecodigo.vim4cache.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import org.academiadecodigo.vim4cache.CaGame;
import org.academiadecodigo.vim4cache.gameObjects.Character;
import org.academiadecodigo.vim4cache.scenes.Hud;
import org.academiadecodigo.vim4cache.util.VariablesUtil;

/**
 * Created by codecadet on 30/03/17.
 */
public class PlayScreen implements Screen{

    private CaGame caGame;

    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private OrthogonalTiledMapRenderer renderer;


    private TmxMapLoader mapLoader;
    private TiledMap map;

    private Box2DDebugRenderer b2rd;
    private Character player;
    private World world;
    private Hud hud;

    public PlayScreen(CaGame caGame) {

        this.caGame = caGame;
        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(VariablesUtil.V_WIDTH/2, VariablesUtil.V_HEIGHT/2, gameCam);
        hud = new Hud(caGame.batch);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("level.tmx"); // tmx file
        world = new World(new Vector2(0, -10), true);

        renderer = new OrthogonalTiledMapRenderer(map);
        gameCam.position.set(gamePort.getWorldWidth()/2, gamePort.getWorldHeight()/2,0);
        player = new Character(world, this);
        b2rd = new Box2DDebugRenderer();


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
        caGame.batch.setProjectionMatrix(gameCam.combined);

        caGame.batch.begin();
        player.draw(caGame.batch);
        caGame.batch.end();

        caGame.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

    }

    @Override
    public void resize(int width, int height) {

        gamePort.update(width,height);

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

    public void update(float dt){
        handleInput(dt);

        world.step(1/60f, 6 ,2);

        player.update(dt);
        gameCam.position.x = player.getB2body().getPosition().x;

        gameCam.update();
        renderer.setView(gameCam);

    }

    private void handleInput(float dt) {

        if(Gdx.input.isTouched()){
            gameCam.position.x += 100 * dt;

        }
    }


}
