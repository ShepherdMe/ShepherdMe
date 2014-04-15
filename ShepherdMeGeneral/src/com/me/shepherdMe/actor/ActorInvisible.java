package com.me.shepherdMe.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.me.shepherdMe.ShepherdMe;
import com.me.shepherdMe.table.LogicaLevel;

public class ActorInvisible extends Actor {
	
	private ShepherdMe game;
	private LogicaLevel logica;
	
	public ActorInvisible(ShepherdMe game, final LogicaLevel logica) {
		setPosition(0, 0);
		setHeight(Gdx.app.getGraphics().getHeight());
		setWidth(Gdx.app.getGraphics().getWidth());
		this.game=game;
		this.logica=logica;// TODO Auto-generated constructor stub
	}
	
	public LogicaLevel getLogica()
	{
		return this.logica;
	}

}
