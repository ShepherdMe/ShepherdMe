package com.me.shepherdMe.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
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
		setHeight(Gdx.graphics.getHeight());
		setWidth(Gdx.graphics.getWidth());
		
		Tbackground = new Texture(Gdx.files.internal("img/fondo.png"));
		background = new TextureRegion(Tbackground,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

		addListener(new InputListener(){
			@Override
		    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) 
		    {
				Gdx.app.log("Mover", "Reconoce pulsacion en x:"+x+" y:"+y);
				logica.moveDog(x,y);
				return true;
		    }
		});	
	}
	
	public void draw(SpriteBatch batch, float parentApha)
	{
		batch.draw(background,getX(),getY());
	}
	
	
}
