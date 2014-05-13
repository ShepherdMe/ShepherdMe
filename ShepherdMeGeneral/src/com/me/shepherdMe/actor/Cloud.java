package com.me.shepherdMe.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;

public class Cloud extends Actor {

	private TextureRegion regionCloud;
	private Texture cloud;
	
	public Cloud(float x, float y, float width, float height) {
		
		super();
		setPosition(x, y);
		this.setX(x);
		this.setY(y);
		setHeight(height);
		setWidth(width);
		setOriginX(getWidth() / 2);
		setOriginY(getHeight() / 2);
	
		
		cloud = new Texture(Gdx.files.internal("img/level/cloud_1.png"));

		regionCloud = new TextureRegion(cloud);
		
		MoveByAction ma = new MoveByAction();
		ma.setAmountX(Gdx.graphics.getWidth());
		ma.setDuration(300f);
		
		this.addAction(ma);
	}

	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.draw(regionCloud, getX(), getY(), getOriginX(), getOriginY(),
				getWidth(), getHeight(), getScaleX(), getScaleY(),
				getRotation());

	}
	
}
