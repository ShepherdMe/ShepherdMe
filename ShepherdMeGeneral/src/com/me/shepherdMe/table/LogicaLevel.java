package com.me.shepherdMe.table;

import java.awt.Label;
import java.util.ArrayList;
import java.util.List;

import utils.GraphicManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.me.shepherdMe.ShepherdMe;
import com.me.shepherdMe.actor.Background;
import com.me.shepherdMe.actor.Bush;
import com.me.shepherdMe.actor.Dog;
import com.me.shepherdMe.actor.Lake;
import com.me.shepherdMe.actor.Obstacle;
import com.me.shepherdMe.actor.Sheep;
import com.me.shepherdMe.actor.WaterCircle;
import com.me.shepherdMe.actor.input.BackgroundUserInput;
import com.me.shepherdMe.functions.Cronometro;
import com.me.shepherdMe.functions.SheepAction;
import com.me.shepherdMe.screens.Level;

public class LogicaLevel extends Table {

	private Sheep sheep1, sheep2;

	private ShepherdMe game;
	private Background background;
	private Dog dog;
	private List<Obstacle> obstacle;
	private BackgroundUserInput bui;


	public LogicaLevel(ShepherdMe game, Level screen) {
		setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		setClip(true);
		this.game = game;
		this.background = new Background(game, this);
		bui = new BackgroundUserInput(this.background);
		this.background.addListener(bui);
		addActor(background);
		this.dog = new Dog(game);
		addActor(dog);

		this.sheep1 = new Sheep(game, 250, 100);
		this.sheep2 = new Sheep(game, 50, 400);
		
		SheepAction ac= new SheepAction();
		ac.setAmount();
		RepeatAction repeat;
        repeat = new RepeatAction();
        repeat.setCount(repeat.FOREVER);
        repeat.setAction(ac);
        
		//sheep1.addAction(repeat);
		sheep2.addAction(repeat);


		//addActor(sheep1);
		//addActor(sheep2);
		
		this.obstacle = new ArrayList<Obstacle>();
		//this.obstacle.add(new Bush(200, 150, 100, 50));//Hacerlo mejor, recorrer el array
		this.obstacle.add(new WaterCircle(400, 200, 80));
		addActor(obstacle.get(0));
		//addActor(obstacle.get(1));
		

		
	}
	
	public void setPause(boolean pause){
		bui.setPause(pause);
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

	public List<Obstacle> getObstacle()
	{
		return this.obstacle;
	}
	
	public void actionSheep() {
		int with = 480;
		int height = 800;

	}
}
