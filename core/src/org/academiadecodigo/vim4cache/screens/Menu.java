package org.academiadecodigo.vim4cache.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import org.academiadecodigo.vim4cache.CaGame;
import org.academiadecodigo.vim4cache.util.VariablesUtil;

/**
 * Created by codecadet on 31/03/17.
 */
public class Menu implements Screen {


    private Texture menu;
    private Viewport viewPort;
    private Stage stage;
    private CaGame caGame;
    private int level;


    public Menu(CaGame caGame) {
        this.caGame = caGame;
        viewPort = new FitViewport(VariablesUtil.V_WIDTH/VariablesUtil.PPM, VariablesUtil.V_HEIGHT/VariablesUtil.PPM, new OrthographicCamera());
        menu = new Texture("in5.png");

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        update(delta);

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        caGame.batch.begin();
        caGame.batch.draw(menu , 80 , 80);
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

    private void handleInput() {

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            caGame.setScreen(new PlayScreen(caGame));

        }

    }

    private void update(float delta){
        handleInput();
    }
}
