package com.me.shepherdMe.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.me.shepherdMe.ShepherdMe;
import com.me.shepherdMe.table.LogicaLevel;

public class Background extends Actor {

	ShepherdMe game;
	TextureRegion background;
	Texture Tbackground;
	LogicaLevel logica;
	
	public Background(ShepherdMe game, final LogicaLevel logica)
	{
		this.game=game;
		this.logica=logica;
		setPosition(0, 0);
		setHeight(Gdx.app.getGraphics().getHeight());
		setWidth(Gdx.app.getGraphics().getWidth());
		
		Tbackground = new Texture(Gdx.files.internal("img/level/background.jpg"));
		Tbackground.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
		background = new TextureRegion(Tbackground,Gdx.app.getGraphics().getWidth(),Gdx.app.getGraphics().getHeight());
		
	}
	
	public void draw(SpriteBatch batch, float parentApha)
	{
		batch.draw(background,getX(),getY());
	}
	
	public LogicaLevel getLogica()
	{
		return this.logica;
	}
	
}
