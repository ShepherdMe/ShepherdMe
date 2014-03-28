package com.me.shepherdMe.screens;

import java.util.List;

import utils.GraphicManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Array;
import com.me.shepherdMe.ShepherdMe;
import com.me.shepherdMe.actor.Sheep;
import com.me.shepherdMe.functions.Cronometro;
import com.me.shepherdMe.table.LogicaLevel;

public class Level implements Screen {

	private ShepherdMe game;
	private Stage stage;
	private LogicaLevel logica;
	private Label cronometerLabel;
	private Cronometro cronometer;

	public Level(ShepherdMe game) {
		Gdx.app.log("LEVEL", "contruye level");
		this.game = game;
		this.stage = new Stage();

	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		stage.draw();

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		// stage.getCamera().translate(-stage.getGutterWidth(),-stage.getGutterHeight(),0);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		Gdx.input.setInputProcessor(stage);

		logica = new LogicaLevel(game, this);
		stage.addActor(logica);

		// Cronometer
		GraphicManager.initialize();
		LabelStyle headingStyle = new LabelStyle(GraphicManager.getWhiteFont(),
				Color.WHITE);
		cronometerLabel = new Label("0:0", headingStyle);
		cronometerLabel.setZIndex(1000);
		cronometerLabel.setX(7 * Gdx.graphics.getWidth() / 8);
		cronometerLabel.setY(8 * Gdx.graphics.getHeight() / 9);
		cronometerLabel.setSize(50, 50);
		cronometerLabel.setFontScale(3);
		cronometer = new Cronometro(cronometerLabel);
		cronometer.start();
		stage.addActor(cronometerLabel);

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		Gdx.input.setInputProcessor(null);

	}

}
