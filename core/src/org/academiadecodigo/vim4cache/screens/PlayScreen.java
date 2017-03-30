package org.academiadecodigo.vim4cache.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import org.academiadecodigo.vim4cache.CaGame;
import org.academiadecodigo.vim4cache.util.VariablesUtil;

/**
 * Created by codecadet on 30/03/17.
 */
public class PlayScreen implements Screen{

    private CaGame caGame;

    private OrthographicCamera gameCam;
    private Viewport gamePort;


    private TmxMapLoader mapLoader;
    private TiledMap map;

    public PlayScreen(CaGame caGame) {
        this.caGame = caGame;
        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(VariablesUtil.V_WIDTH, VariablesUtil.V_HEIGHT, gameCam);

        mapLoader = new TmxMapLoader();

        map = mapLoader.load("Mapa"); // tmx file
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

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
