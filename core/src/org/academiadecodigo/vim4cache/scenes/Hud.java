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

    Label countLabel;
    Label timeLabel;
    Label healthLabel;
    Label hpLabel;
    Label scoreLabel;
    Label scoreBoard;

    public Hud(SpriteBatch spriteBatch){

        time = 120;
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
        countLabel = new Label(String.format("%02d", time), new Label.LabelStyle(new BitmapFont(), Color.RED));
        countLabel.setFontScale(2);

        timeLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        timeLabel.setFontScale(4);
        //health set and color
        healthLabel = new Label(String.format("%03d", health), new Label.LabelStyle(new BitmapFont(), Color.RED));
        healthLabel.setFontScale(2);

        hpLabel = new Label("HP", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        hpLabel.setFontScale(4);
        //score set and color
        scoreLabel = new Label(String.format("%05d", score), new Label.LabelStyle(new BitmapFont(), Color.RED));
        scoreLabel.setFontScale(2);

        scoreBoard = new Label("SCORE", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreBoard.setFontScale(4);

        //insert in table all the labels in the table, to see on the screen

        //not sure about the padding
        table.add(timeLabel).expandX().padTop(10);
        table.add(hpLabel).expandX().padTop(10);
        table.add(scoreBoard).expandX().padTop(10);

        table.row();

        table.add(countLabel).expandX();
        table.add(healthLabel).expandX();
        table.add(scoreLabel).expandX();

        stage.addActor(table);
    }
}
