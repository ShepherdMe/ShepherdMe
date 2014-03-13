package com.me.shepherdMe.table;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.me.shepherdMe.ShepherdMe;
import com.me.shepherdMe.actor.Background;
import com.me.shepherdMe.actor.Dog;
import com.me.shepherdMe.actor.Sheep;
import com.me.shepherdMe.screens.Level;

public class LogicaLevel extends Table{
	
	ShepherdMe game;
	Background background;
	Dog dog;
	Sheep sheep1,sheep2;
	
	public LogicaLevel(ShepherdMe game, Level screen) {
		setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		setClip(true);
		this.game=game;
		this.background= new Background(game,this);
		addActor(background);
		
		//this.dog= new Dog(game);
		//addActor(dog);
		
		this.sheep1=new Sheep(game,250,100);
		this.sheep2=new Sheep(game,50,400);
		actionSheep();
		
		addActor(sheep1);
		addActor(sheep2);
		
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
	
	public void moveDog(float x, float y)
	{
		Gdx.app.log("Mover", "Se mueve desde x:"+getX()+" y:"+getY());
		Gdx.app.log("Mover", "Se mueve a x:"+x+" y:"+y);
		this.dog.move(x, y);
	}
	public void actionSheep()
	{
		int with=480;
		int height=800;
		
	}
	

}
