package com.me.shepherdMe.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class Obstacle extends Actor {

	protected Object area;
	protected TextureRegion regionObstacle;
	protected Texture obstacle;

	public Obstacle(float x, float y, float width, float height) {
		super();
		setPosition(x, y);
		setHeight(height);
		setWidth(width);
	}

	public boolean hitArea(float x, float y, float width, float height) {
		
		return false;
	}

	public void draw(SpriteBatch batch, float parentAlpha) {

		batch.draw(regionObstacle, getX(), getY(), getOriginX(), getOriginY(),
				getWidth(), getHeight(), getScaleX(), getScaleY(),
				getRotation());

	}

}
