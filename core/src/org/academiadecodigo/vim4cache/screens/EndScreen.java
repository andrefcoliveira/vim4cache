package org.academiadecodigo.vim4cache.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import org.academiadecodigo.vim4cache.CaGame;
import org.academiadecodigo.vim4cache.util.VariablesUtil;


/**
 * Created by codecadet on 31/03/17.
 */
public class EndScreen implements Screen {

    private final Texture menuEnd;
    private Viewport viewPort;
    private Stage stage;
    private CaGame caGame;
    private TextureAtlas textureAtlas;

    public EndScreen(CaGame caGame) {
        this.caGame = caGame;
        viewPort = new FitViewport(VariablesUtil.V_WIDTH, VariablesUtil.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewPort, caGame.batch);

        menuEnd = new Texture("gameOver.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        caGame.batch.begin();
        caGame.batch.draw(menuEnd, 0, 0);
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
}
