package com.me.shepherdMe.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Ellipse;

public class Lake extends Obstacle {

	public Lake(float x, float y, float width, float height) {
		super(x, y, width, height);
		
		this.area = new Ellipse(x, y, width, height);
		
		this.obstacle = new Texture(Gdx.files.internal("img/muro.jpg"));
		this.regionObstacle = new TextureRegion(this.obstacle);;

		this.obstacle = new Texture(Gdx.files.internal("img/rojo.jpg"));//Cambiarle la foto y poner un width y height determinado
		this.regionObstacle = new TextureRegion(this.obstacle);	
		
	}
	
	@Override
	public boolean hitArea(float x, float y, float width, float height)
	{
		return (((Ellipse) this.area).contains(x,y)||((Ellipse) this.area).contains(x+width,y+height) );
	}

}
