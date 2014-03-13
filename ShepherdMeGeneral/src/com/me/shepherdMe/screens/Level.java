package com.me.shepherdMe.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.me.shepherdMe.ShepherdMe;
import com.me.shepherdMe.table.LogicaLevel;

public class Level implements Screen {

	private ShepherdMe game;
	private Stage stage;
	private LogicaLevel logica;
	
	public Level(ShepherdMe game){
		Gdx.app.log("LEVEL", "contruye level");
		this.game=game;
		this.stage=new Stage();
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
		//stage.getCamera().translate(-stage.getGutterWidth(),-stage.getGutterHeight(),0);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		Gdx.input.setInputProcessor(stage);
		
		logica = new LogicaLevel(game, this);
		stage.addActor(logica);
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
