package com.me.shepherdMe.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.me.shepherdMe.ShepherdMe;

public class Dog extends Actor {

	private ShepherdMe game;
	private TextureRegion regionDog;
	private Texture dog;


	public Dog(ShepherdMe game) {

		this.game = game;
		setPosition(50, 50);
		setHeight(Gdx.graphics.getHeight()/7);
		setWidth(Gdx.graphics.getHeight()/7);

		dog = new Texture(Gdx.files.internal("img/level/dog.png"));

		regionDog = new TextureRegion(dog);

	}

	public void draw(SpriteBatch batch, float parentAlpha) {

		batch.draw(regionDog, getX(), getY(), getOriginX(), getOriginY(),
				getWidth(), getHeight(), getScaleX(), getScaleY(),
				getRotation());

	}

	
}
