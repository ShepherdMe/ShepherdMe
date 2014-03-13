package com.me.shepherdMe.table;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.me.shepherdMe.ShepherdMe;
import com.me.shepherdMe.actor.Background;
import com.me.shepherdMe.actor.Dog;
import com.me.shepherdMe.actor.Sheep;
import com.me.shepherdMe.actor.input.BackgroundUserInput;
import com.me.shepherdMe.screens.Level;

public class LogicaLevel extends Table {

	private Sheep sheep1, sheep2;

	private ShepherdMe game;
	private Background background;
	private Dog dog;

	public LogicaLevel(ShepherdMe game, Level screen) {
		setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		setClip(true);
		this.game = game;
		this.background = new Background(game, this);
		this.background.addListener(new BackgroundUserInput(this.background));
		addActor(background);
		this.dog = new Dog(game);
		addActor(dog);

		this.sheep1 = new Sheep(game, 250, 100);
		this.sheep2 = new Sheep(game, 50, 400);
		actionSheep();

		addActor(sheep1);
		addActor(sheep2);

	}

	@Override
	public void act(float delta) {
		super.act(delta);
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.setColor(Color.WHITE);
		super.draw(batch, parentAlpha);
	}

	public Dog getDog() {
		return this.dog;
	}

	public Background GetBackground() {
		return this.background;
	}

	public void actionSheep() {
		int with = 480;
		int height = 800;

	}
}
