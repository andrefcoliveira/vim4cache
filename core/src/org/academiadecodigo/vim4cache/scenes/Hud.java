package org.academiadecodigo.vim4cache.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import org.academiadecodigo.vim4cache.util.VariablesUtil;

/**
 * Created by codecadet on 30/03/17.
 */
public class Hud {

    public Stage stage;

    private Viewport viewPort;
    private Integer time;
    private Integer health;
    private Integer score;

    Label timerLabel;
    Label healthLabel;
    Label scoreLabel;

    public Hud(SpriteBatch spriteBatch){
        time = 60;
        health = 100;
        score = 0;

        viewPort = new FitViewport(VariablesUtil.V_WIDTH, VariablesUtil.V_HEIGHT, new OrthographicCamera());

        stage = new Stage(viewPort, spriteBatch);

        //Create an instance that receives all info about the character
        Table table = new Table();
        table.top();
        table.setFillParent(true);

        //colors of our text

        //timer set and color
        timerLabel = new Label(String.format("%02d", time), new Label.LabelStyle(new BitmapFont(), Color.BLACK));

        //health set and color
        healthLabel = new Label(String.format("%03d", health), new Label.LabelStyle(new BitmapFont(), Color.BLACK));

        //score set and color
        scoreLabel = new Label(String.format("%05d", score), new Label.LabelStyle(new BitmapFont(), Color.BLACK));

        //insert in table all the labels in the table, to see on the screen

        //not sure about the padding
        table.add(timerLabel).expandX();
        table.add(healthLabel).expandX();
        table.add(scoreLabel).expandX();

        stage.addActor(table);
    }
}
