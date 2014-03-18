package com.me.shepherdMe.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
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

	public Dog(ShepherdMe game) {

		this.game = game;
		setPosition(50, 50);
		setHeight(64);
		setWidth(64);
		/*setX(getWidth() / 2);
		setY(getHeight() / 2);*/

		dog = new Texture(Gdx.files.internal("img/dog.png"));

		regionDog = new TextureRegion(dog);

		
	}

	public void draw(SpriteBatch batch, float parentAlpha) {

		batch.draw(regionDog, getX(), getY(), getOriginX(), getOriginY(),
				getWidth(), getHeight(), getScaleX(), getScaleY(),
				getRotation());

	}

	
}
