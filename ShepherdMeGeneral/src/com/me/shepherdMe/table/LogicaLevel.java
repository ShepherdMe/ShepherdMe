package com.me.shepherdMe.table;


import aurelienribon.tweenengine.equations.Back;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.me.shepherdMe.ShepherdMe;
import com.me.shepherdMe.actor.Background;
import com.me.shepherdMe.actor.Dog;
import com.me.shepherdMe.actor.input.BackgroundUserInput;
import com.me.shepherdMe.screens.Level;

public class LogicaLevel extends Table{
	
	private ShepherdMe game;
	private Background background;
	private Dog dog;
	
	public LogicaLevel(ShepherdMe game, Level screen) {
		setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		setClip(true);
		this.game=game;
		this.background= new Background(game,this);
		this.background.addListener(new BackgroundUserInput(this.background));
		addActor(background);
		this.dog= new Dog(game);
		//this.dog.addListener(new DogUserInput(this.dog));
		addActor(dog);
		
	}
	
	@Override
	public void act(float delta)
	{
		super.act(delta);
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha)
	{
		batch.setColor(Color.WHITE);
		super.draw(batch, parentAlpha);
	}
	
	public Dog getDog()
	{
		return this.dog;
	}
	
	public Background GetBackground()
	{
		return this.background;
	}
}
