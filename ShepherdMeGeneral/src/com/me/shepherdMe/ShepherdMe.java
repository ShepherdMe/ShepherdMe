package com.me.shepherdMe;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.me.shepherdMe.screens.Splash;

public class ShepherdMe extends Game {

	public static final String NAME="ShepherdMe",VERSION="0.0.0";
	
	
	
	@Override
	public void create() {
		// TODO Auto-generated method stub
		//Gdx.gl.glEnable(GL10.GL_LINEAR_ATTENUATION);
		Gdx.gl.glEnable(GL10.GL_LINE_SMOOTH);

		setScreen(new Splash(this));
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		super.resize(width, height);
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		super.render();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		super.pause();
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		super.resume();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
	}

}
