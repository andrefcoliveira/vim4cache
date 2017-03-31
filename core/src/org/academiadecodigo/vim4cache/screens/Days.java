package org.academiadecodigo.vim4cache.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import org.academiadecodigo.vim4cache.CaGame;
import org.academiadecodigo.vim4cache.util.VariablesUtil;

/**
 * Created by codecadet on 31/03/17.
 */
public class Days implements Screen {

    private Texture day;
    private Viewport viewPort;
    private Stage stage;
    private CaGame caGame;
    private int level;


    public Days(CaGame caGame) {
        this.caGame = caGame;
        viewPort = new FitViewport(VariablesUtil.V_WIDTH, VariablesUtil.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewPort, caGame.batch);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        day = new Texture("day" + level + ".png");
        caGame.batch.begin();
        caGame.batch.draw(day, 0, 0);
        caGame.batch.end();
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Texture getDay() {
        return day;
    }
}
