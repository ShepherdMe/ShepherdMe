package com.me.shepherdMe.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.me.shepherdMe.ShepherdMe;


public class Dog extends Actor {
	
	private ShepherdMe game;
	private TextureRegion regionDog;
	private Texture dog;
	private RotateToAction rotate;
	private MoveByAction move;
	
	public Dog(ShepherdMe game)
	{
		this.game=game;
		setPosition(50, 50);
		setHeight(64);
		setWidth(64);
		setOriginX(getWidth()/2);
		setOriginY(getHeight()/2);
		
		/*move = new MoveByAction();
		
		move.setAmount(50, 0);
		move.setDuration(10f);
		
		this.addAction(move);*/
		
		dog = new Texture(Gdx.files.internal("img/dog.png"));
		
		regionDog = new TextureRegion(dog);
		
		addListener(new InputListener(){
			
		});
		
		
	}
	
	public void draw(SpriteBatch batch, float parentAlpha)
	{
		batch.draw(regionDog, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
	}
	
	public void move(float x, float y)
	{
		//Actions.moveTo(x, y);
		move.setAmount(x - getX(), y - getY());
		addAction(move);
		
		
	}

}
