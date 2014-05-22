package com.me.shepherdMe.actor;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.me.shepherdMe.images.Imagenes;

public class BackgroundFold extends Actor {

	protected TextureRegion regionObstacle;

	
	public BackgroundFold(float x, float y, float width, float height) {
		super();
		setPosition(x, y);
		setHeight(height);
		setWidth(width);
		this.regionObstacle = Imagenes.nivelBGFold;
	}
	
	public void draw(SpriteBatch batch, float parentAlpha) {

		batch.draw(regionObstacle, getX(), getY(), getOriginX(), getOriginY(),
				getWidth(), getHeight(), getScaleX(), getScaleY(),
				getRotation());

	}

}
