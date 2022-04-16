package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.Actors.LoginActor;
import com.mygdx.game.Actors.LogoActor;
import com.mygdx.game.MyGdxGame;

/**
 * Created by Ntcarter on 11/4/2017.
 */

public class Offline implements Screen {

    private MyGdxGame game;
    private Skin skin;
    private Stage stage;
    private SpriteBatch batch;
    private Sprite sprite;
    private Table table;

    public Offline(MyGdxGame game){
        this.game = game;
    }
    @Override
    public void show() {
        ScreenViewport viewPort = new ScreenViewport();
        stage = new Stage(viewPort);
        skin = new Skin(Gdx.files.internal("uiskin.json"));

        table = new Table();
        table.setWidth(stage.getWidth());
        table.align(Align.center|Align.top);
        table.setPosition(0, Gdx.graphics.getHeight()/2-50);


        final TextButton backButton = new TextButton("      back      ", skin, "default");
        backButton.setWidth(50);
        backButton.setHeight(25);
        backButton.setPosition(580,10);
        backButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.setLoginAndRegisterScreen();
            }
        });


        final TextButton campaignButton = new TextButton("Campaign", skin, "default");
        campaignButton.setWidth(200);
        campaignButton.setHeight(100);
        campaignButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.SetCampaignScreen();
            }
        });

        final TextButton myMapsButton = new TextButton("My Maps", skin, "default");
        myMapsButton.setWidth(200);
        myMapsButton.setHeight(100);
        myMapsButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                //here goes to List of my maps
            }
        });


        table.add(myMapsButton).padRight(30);
        table.add(campaignButton);
        stage.addActor(table);
        stage.addActor(backButton);
        LogoActor logo = new LogoActor();
        stage.addActor(logo);
        batch = new SpriteBatch();
        sprite = new Sprite(new Texture(Gdx.files.internal("B2.png")));
        sprite.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        sprite.draw(batch);
        batch.end();
        stage.draw();

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
