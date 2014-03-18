package com.me.shepherdMe.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.me.shepherdMe.ShepherdMe;


public class Sheep extends Actor {
	
	private ShepherdMe game;
	private TextureRegion regionSheep;
	private Texture sheep;
	private RotateToAction rotate;
	private MoveByAction move;
	private float x,y;
	
	public Sheep(ShepherdMe game, int x,int y)
	{
		this.game=game;
		setPosition(x, y);
		this.x=x;
		this.y=y;
		setHeight(64);
		setWidth(64);
		setOriginX(getWidth()/2);
		setOriginY(getHeight()/2);
		
		
		
		sheep = new Texture(Gdx.files.internal("img/sheep.png"));
		
		regionSheep = new TextureRegion(sheep);
		
		
		
	}
	public Vector2 position()
	{
		return new Vector2(this.x,this.y);
	}
	
	public void draw(SpriteBatch batch, float parentAlpha)
	{
		batch.draw(regionSheep, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
		
		
	}
	
	public void render()
	{
		Vector2 v= new Vector2();
		MoveByAction move;
			move = new MoveByAction();
				move.setAmount(100*v.x, 100*v.y);
			move.setDuration(5f);
		}		
}

