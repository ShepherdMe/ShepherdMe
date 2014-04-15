package com.me.shepherdMe.actor;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.me.shepherdMe.ShepherdMe;

public class Cloud extends Actor {

	private ShepherdMe game;
	private TextureRegion regionCloud;
	private Texture cloud;
	private RotateToAction rotate;
	private MoveByAction move;
	
	public Cloud(float x, float y, float width, float height) {
		
		super();
		setPosition(x, y);
		this.setX(x);
		this.setY(y);
		setHeight(width);
		setWidth(height);
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