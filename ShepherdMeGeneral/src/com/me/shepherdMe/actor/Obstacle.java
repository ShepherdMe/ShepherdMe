package com.me.shepherdMe.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Obstacle extends Actor {

	private Rectangle area;
	private TextureRegion regionObstacle;
	private Texture obstacle;

	public Obstacle(float x, float y, float width, float height) {
		this.area = new Rectangle(x, y, width, height);
		setPosition(x, y);
		setHeight(height);
		setWidth(width);

		this.obstacle = new Texture(Gdx.files.internal("img/muro.jpg"));
		this.regionObstacle = new TextureRegion(this.obstacle);
	}

	public Rectangle getArea() {
		return this.area;
	}

	public boolean hitArea(float x, float y, float width, float height) {
		if( (x+width>= this.getX()) && (x <= this.getX() + this.getWidth()))
		{
			if ((y+height >= this.getY()) && (y <= this.getY() + this.getHeight())) 
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}

	public void draw(SpriteBatch batch, float parentAlpha) {

		batch.draw(regionObstacle, getX(), getY(), getOriginX(), getOriginY(),
				getWidth(), getHeight(), getScaleX(), getScaleY(),
				getRotation());

	}

}
