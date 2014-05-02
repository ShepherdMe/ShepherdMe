package com.me.shepherdMe.table;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.me.shepherdMe.ShepherdMe;
import com.me.shepherdMe.actor.ActorInvisible;
import com.me.shepherdMe.actor.Background;
import com.me.shepherdMe.actor.Bush;
import com.me.shepherdMe.actor.Cloud;
import com.me.shepherdMe.actor.Dog;
import com.me.shepherdMe.actor.Obstacle;
import com.me.shepherdMe.actor.Open;
import com.me.shepherdMe.actor.Sheep;
import com.me.shepherdMe.actor.SheepFold;
import com.me.shepherdMe.actor.input.BackgroundUserInput;
import com.me.shepherdMe.screens.Level;
import com.me.shepherdMe.sound.SoundManager;


public class LogicaLevel extends Table {

	private static Timer timer;
	private static TimerTaskSheep timerTask;

	
	private ShepherdMe game;
	private Background background;
	private Dog dog;
	private List<Obstacle> obstacle;
	private BackgroundUserInput bui;
	private List<Sheep> sheeps;
	private List<Cloud> clouds;
	private SheepFold fold;
	private ActorInvisible actorInvisible;
	private Level screen;
	private int AltoPantalla = Gdx.graphics.getHeight();
	private int AnchoPantalla = Gdx.graphics.getWidth();

	
	
	public LogicaLevel(ShepherdMe game, Level screen) {
		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();
		setBounds(0, 0, width, height);
		this.screen = screen;
		setClip(true);
		this.game = game;
		this.background = new Background(game, this);
		this.actorInvisible = new ActorInvisible(this);
		bui = new BackgroundUserInput(this.actorInvisible);
		this.actorInvisible.addListener(bui);
		//this.background.addListener(bui);
		addActor(background);
		
		this.dog = new Dog();
		
		addActor(dog);

		Sheep sheep1 = new Sheep(this, 600, 150);
		Sheep sheep2 = new Sheep(this, 50, 400);

		this.sheeps = new ArrayList<Sheep>();
		this.sheeps.add(sheep1);
		this.sheeps.add(sheep2);
		
		for (Sheep s : sheeps) {
			addActor(s);
		}
		

		this.obstacle = new ArrayList<Obstacle>();
		
		//redil
		fold = new SheepFold(150, 50, 50, 80, Open.LEFT);

		for(Bush b : fold.getFoldObstacles()){
			addActor(b);
		}
		
		
		//this.obstacle.add(new Bush(200, 150, 100, 50));//Hacerlo mejor, recorrer el array
		//this.obstacle.add(new WaterCircle(400, 400, 150));
		//addActor(obstacle.get(0));
		for(Bush b : fold.getFoldObstacles()){
//			this.obstacle.add(b);
		}
//		this.obstacle.add(fold.getGate());
		addActor(fold.getGate());
		//addActor(obstacle.get(1));
		
		//Mover ovejas DEBE SER LO ULTIMO DEL CONSTRUCTOR!
		timer = new Timer();
		timerTask = new TimerTaskSheep();
		timer.scheduleAtFixedRate(timerTask, 0, 15);
		//fin mover ovejas
		
		this.clouds = new ArrayList<Cloud>();
		this.clouds.add(new Cloud(200, 100,100, 200));
		
		for (Cloud s : clouds) {
			addActor(s);
		}
		
		
		addActor(actorInvisible);

	}
	
	public SheepFold getFold(){
		return this.fold;
	}
	
	public void openFold(){
		this.obstacle.remove(fold.getGate());
		fold.open(this);
	}
	
	public void closeFold(){
		this.obstacle.add(fold.getGate());
		fold.close(this);
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
	
	protected void moveSheeps() {
		// TODO Auto-generated method stub
		int sheepsIn=0;
		Sheep oveja;
		
		for (int i=0; i< this.sheeps.size();i++) {
			Vector2 v= this.sheeps.get(i).moveSheep();
			oveja = this.sheeps.get(i);
			
			if(!nearDog(this.sheeps.get(i)))
			{
				if(oveja.estaHuyendo)
				{
					oveja.nuevoPunto=true;
					oveja.estaHuyendo=false;
				}
				System.out.println("Toca? "+oveja.ovejaTocaElemento(v));
				if (!oveja.ovejaTocaElemento(v)) {
					this.sheeps.get(i).setX(v.x);
					this.sheeps.get(i).setY(v.y);
				}
				else
				{
					oveja.nuevoPunto=true;
				}
			}
			else//Si el perro esta cerca.
			{	
				
				if(!oveja.estaHuyendo)
				{
					SoundManager.playSheep();
				}
				
				oveja.estaHuyendo=true;
				Vector2 nuevaPosicion= runAwayDog(oveja);
				if (!oveja.ovejaTocaElemento(nuevaPosicion)) {
					
					this.sheeps.get(i).setX(nuevaPosicion.x);
					this.sheeps.get(i).setY(nuevaPosicion.y);
				}
				else
				{ 
					Vector2 x=new Vector2(nuevaPosicion.x,this.sheeps.get(i).getY());
					Vector2 y=new Vector2(this.sheeps.get(i).getX(),nuevaPosicion.y);
					if(!oveja.ovejaTocaElemento(x)&&!oveja.ovejaTocaElemento(y))
					{
						
						this.sheeps.get(i).setX(this.sheeps.get(i).getX());
						this.sheeps.get(i).setY(this.sheeps.get(i).getY());
						
					}
					else
					{
						if(oveja.ovejaTocaElemento(x))
						{
							
							this.sheeps.get(i).setX(this.sheeps.get(i).getX());
							this.sheeps.get(i).setY(nuevaPosicion.y);
							
						}
						else
						{
							this.sheeps.get(i).setX(nuevaPosicion.x);
							this.sheeps.get(i).setY(this.sheeps.get(i).getY());
							
						}
					}
				}
			}
			//Comprobamos si estan todas en el redil
			if(this.fold.isInFold(this.sheeps.get(i)))
			{
				sheepsIn++;
				
				if(sheepsIn==this.sheeps.size()&&!this.fold.isOpen())
				{
					this.screen.ganar();
					//SACAR CARTEL
				}
				
			}
		}
		
		
	}
	
	public void pauseOvejas()
	{
		if(timerTask!=null)
		{
			timerTask.cancel();
			timerTask=null;
		}
	}
	public void inciarOvejas()
	{
		if(timerTask==null)
		{
			timerTask = new TimerTaskSheep();
			timer.scheduleAtFixedRate(timerTask, 0, 15);
		}
	}
	
	public boolean nearDog(Sheep s)
	{
		Vector2 vector;
		vector=new Vector2 (this.dog.getX()-s.getX() , this.dog.getY()-s.getY());
		if(Math.sqrt(Math.pow(vector.x,2)+Math.pow(vector.y,2))<Gdx.graphics.getWidth()/5)
		{
			return true;
		}
		return false;
	}

	public Vector2 runAwayDog(Sheep s)
	{
		Vector2 vectorN=new Vector2 (s.getX()-this.dog.getX() , s.getY()-this.dog.getY());
				
		int v;
		
		if (vectorN.len()<Gdx.graphics.getWidth()/12)
		{
			v=7;
		}
		else if (vectorN.len()<Gdx.graphics.getWidth()/8)
		{
			v=6;
		}
		else if(vectorN.len()<Gdx.graphics.getWidth()/6)
		{
			v=4;
		}
		else
		{
			v=3;
		}
		
		vectorN.nor();
		vectorN.scl(v);
		
		
		if ((s.getX() + vectorN.x + s.getWidth()) >= AnchoPantalla ||(s.getX() + vectorN.x) <= 0) {
			vectorN.x = 0;
		}
		if ((s.getY() + vectorN.y + s.getHeight()) >= AltoPantalla ||(s.getY() + vectorN.y) <= 0) {
			vectorN.y = 0;
		}
		
		vectorN.x=s.getX()+vectorN.x;
		vectorN.y=s.getY()+vectorN.y;
		return vectorN;		
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
	public Dog getDog() {
		return this.dog;
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
