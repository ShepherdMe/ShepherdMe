package com.me.shepherdMe.table;


import aurelienribon.tweenengine.equations.Back;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
<<<<<<< HEAD
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
=======
>>>>>>> a271b89b6f9eaec3ac3861c2ad681838ebb78ea9
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.me.shepherdMe.ShepherdMe;
import com.me.shepherdMe.actor.Background;
import com.me.shepherdMe.actor.Dog;
<<<<<<< HEAD
import com.me.shepherdMe.actor.Sheep;
=======
import com.me.shepherdMe.actor.input.BackgroundUserInput;
>>>>>>> a271b89b6f9eaec3ac3861c2ad681838ebb78ea9
import com.me.shepherdMe.screens.Level;

public class LogicaLevel extends Table{
	
<<<<<<< HEAD
	ShepherdMe game;
	Background background;
	Dog dog;
	Sheep sheep1,sheep2;
=======
	private ShepherdMe game;
	private Background background;
	private Dog dog;
>>>>>>> a271b89b6f9eaec3ac3861c2ad681838ebb78ea9
	
	public LogicaLevel(ShepherdMe game, Level screen) {
		setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		setClip(true);
		this.game=game;
		this.background= new Background(game,this);
		this.background.addListener(new BackgroundUserInput(this.background));
		addActor(background);
<<<<<<< HEAD
		
		//this.dog= new Dog(game);
		//addActor(dog);
		
		this.sheep1=new Sheep(game,250,100);
		this.sheep2=new Sheep(game,50,400);
		actionSheep();
		
		addActor(sheep1);
		addActor(sheep2);
=======
		this.dog= new Dog(game);
		//this.dog.addListener(new DogUserInput(this.dog));
		addActor(dog);
>>>>>>> a271b89b6f9eaec3ac3861c2ad681838ebb78ea9
		
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
<<<<<<< HEAD
	public void actionSheep()
	{
		int with=480;
		int height=800;
		
	}
	

=======
>>>>>>> a271b89b6f9eaec3ac3861c2ad681838ebb78ea9
}
