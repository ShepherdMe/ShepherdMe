package com.me.shepherdMe.table;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.me.shepherdMe.ShepherdMe;
import com.me.shepherdMe.actor.Background;
import com.me.shepherdMe.actor.Bush;
import com.me.shepherdMe.actor.Dog;
import com.me.shepherdMe.actor.Lake;
import com.me.shepherdMe.actor.Obstacle;
import com.me.shepherdMe.actor.Open;
import com.me.shepherdMe.actor.Sheep;
import com.me.shepherdMe.actor.SheepFold;
import com.me.shepherdMe.actor.WaterCircle;
import com.me.shepherdMe.actor.input.BackgroundUserInput;
import com.me.shepherdMe.functions.SheepAction;
import com.me.shepherdMe.screens.Level;


public class LogicaLevel extends Table {

	private static Timer timer;
	private static TimerTaskSheep timerTask;

	private ShepherdMe game;
	private Background background;
	private Dog dog;
	private List<Obstacle> obstacle;
	private BackgroundUserInput bui;
	private List<Sheep> sheeps;
	private SheepFold fold;


	public LogicaLevel(ShepherdMe game, Level screen) {
		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();
		setBounds(0, 0, width, height);
		setClip(true);
		this.game = game;
		this.background = new Background(game, this);
		bui = new BackgroundUserInput(this.background);
		this.background.addListener(bui);
		addActor(background);
		this.dog = new Dog(game);
		addActor(dog);

		Sheep sheep1 = new Sheep(game, 250, 100);
		Sheep sheep2 = new Sheep(game, 50, 400);

		this.sheeps = new ArrayList<Sheep>();
		this.sheeps.add(sheep1);
		this.sheeps.add(sheep2);
		
		for (Sheep s : sheeps) {
			addActor(s);
		}
		
		//Mover ovejas
		timer = new Timer();
		timerTask = new TimerTaskSheep();
		timer.scheduleAtFixedRate(timerTask, 0, 15);
		//fin mover ovejas
		
		
		this.obstacle = new ArrayList<Obstacle>();
		
		//redil
		fold = new SheepFold(width/2, height/2, 500, 400, Open.LEFT);
		for(Bush b : fold.getFoldObstacles()){
			addActor(b);
		}
		
		//this.obstacle.add(new Bush(200, 150, 100, 50));//Hacerlo mejor, recorrer el array
		this.obstacle.add(new WaterCircle(400, 400, 150));
		addActor(obstacle.get(0));
		for(Bush b : fold.getFoldObstacles()){
			this.obstacle.add(b);
		}
		//addActor(obstacle.get(1));
	}
	
	public SheepFold getFold(){
		return this.fold;
	}
	
	public void setPause(boolean pause){
		if(pause)
		{
			pauseOvejas();
		}
		else
		{
			inciarOvejas();
		}
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
	
	protected void moveSheeps() {
		// TODO Auto-generated method stub
		for (int i=0; i< this.sheeps.size();i++) {
			Vector2 v= this.sheeps.get(i).moveSheep();
			
			if (!hitArea(v)&&!hitSheep(v,this.sheeps.get(i))) {
				this.sheeps.get(i).setX(v.x);
				this.sheeps.get(i).setY(v.y);
			}
		}
	}
	public void pauseOvejas()
	{
		if(this.timerTask!=null)
		{
			this.timerTask.cancel();
			this.timerTask=null;
		}
	}
	public void inciarOvejas()
	{
		if(this.timerTask==null)
		{
			timerTask = new TimerTaskSheep();
			timer.scheduleAtFixedRate(timerTask, 0, 15);
		}
	}
	public Background GetBackground() {
		return this.background;
	}

	public List<Obstacle> getObstacle()
	{
		return this.obstacle;
	}
	public List<Sheep> getSheeps()
	{
		return this.sheeps;
	}
	private boolean hitArea(Vector2 v) {
		List<Obstacle> obstaculos = obstacle;
		for (Obstacle obstacle : obstaculos) {
			if (obstacle.hitArea(v.x, v.y, sheeps.get(0).getWidth(), sheeps.get(0).getHeight())) {
				return true;
			}
		}
		return false;
	}
	private boolean hitSheep(Vector2 v, Sheep s) {
		List<Sheep> oveja = sheeps;
		for (Sheep o : oveja) {
			if(!o.Equals(s))
			{
				if (o.hitArea(v.x, v.y, sheeps.get(0).getWidth(), sheeps.get(0).getHeight())) {
					return true;
				}
			}
		}
		return false;
	}
	class TimerTaskSheep extends TimerTask
	{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			moveSheeps();
		}
		
	}

}
