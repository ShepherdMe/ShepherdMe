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
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.me.shepherdMe.ShepherdMe;
import com.me.shepherdMe.actor.ActorInvisible;
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
	private ActorInvisible actorInvisible;


	public LogicaLevel(ShepherdMe game, Level screen) {
		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();
		setBounds(0, 0, width, height);
		setClip(true);
		this.game = game;
		this.background = new Background(game, this);
		this.actorInvisible = new ActorInvisible(game, this);
		bui = new BackgroundUserInput(this.actorInvisible);
		this.actorInvisible.addListener(bui);
		//this.background.addListener(bui);
		addActor(background);
		this.dog = new Dog(game);
		addActor(dog);

		Sheep sheep1 = new Sheep(game, 600, 150);
		Sheep sheep2 = new Sheep(game, 50, 400);

		this.sheeps = new ArrayList<Sheep>();
		this.sheeps.add(sheep1);
		this.sheeps.add(sheep2);
		
		for (Sheep s : sheeps) {
			addActor(s);
		}
		

		this.obstacle = new ArrayList<Obstacle>();
		
		//redil
		fold = new SheepFold(200, 100, 500, 600, Open.LEFT);
		for(Bush b : fold.getFoldObstacles()){
			addActor(b);
		}
		
		
		//this.obstacle.add(new Bush(200, 150, 100, 50));//Hacerlo mejor, recorrer el array
		//this.obstacle.add(new WaterCircle(400, 400, 150));
		//addActor(obstacle.get(0));
		for(Bush b : fold.getFoldObstacles()){
			this.obstacle.add(b);
		}
		this.obstacle.add(fold.getGate());
		addActor(fold.getGate());
		//addActor(obstacle.get(1));
		
		//Mover ovejas DEBE SER LO ULTIMO DEL CONSTRUCTOR!
		timer = new Timer();
		timerTask = new TimerTaskSheep();
		timer.scheduleAtFixedRate(timerTask, 0, 15);
		//fin mover ovejas
		
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

	public Dog getDog() {
		return this.dog;
	}
	
	protected void moveSheeps() {
		// TODO Auto-generated method stub
		int sheepsIn=0;
		
		for (int i=0; i< this.sheeps.size();i++) {
			Vector2 v= this.sheeps.get(i).moveSheep();
			
			if(!nearDog(this.sheeps.get(i)))
			{
				if (!hitArea(v)&&!hitSheep(v,this.sheeps.get(i))) {
					this.sheeps.get(i).setX(v.x);
					this.sheeps.get(i).setY(v.y);
				}
			}
			
			else//Si el perro esta cerca.
			{
				Vector2 nuevaPosicion= runAwayDog(this.sheeps.get(i));
				if (!hitArea(nuevaPosicion)&&!hitSheep(nuevaPosicion,this.sheeps.get(i))) {
					
					this.sheeps.get(i).setX(nuevaPosicion.x);
					this.sheeps.get(i).setY(nuevaPosicion.y);
				}
				else
				{ 
					Vector2 x=new Vector2(nuevaPosicion.x,this.sheeps.get(i).getY());
					Vector2 y=new Vector2(this.sheeps.get(i).getX(),nuevaPosicion.y);
					if(!hitArea(x)&&!hitArea(y))
					{
						
						this.sheeps.get(i).setX(this.sheeps.get(i).getX());
						this.sheeps.get(i).setY(this.sheeps.get(i).getY());
						System.out.println("choco ambas");
					}
					else
					{
						if(hitArea(x))
						{
							
							this.sheeps.get(i).setX(this.sheeps.get(i).getX());
							this.sheeps.get(i).setY(nuevaPosicion.y);
							System.out.println("choco x");
						}
						else
						{
							this.sheeps.get(i).setX(nuevaPosicion.x);
							this.sheeps.get(i).setY(this.sheeps.get(i).getY());
							System.out.println("choco Y");
						}
					}
				}
			}
			//Comprobamos si est�n todas en el redil
			if(this.fold.isInFold(this.sheeps.get(i)))
			{
				sheepsIn++;
				System.out.println(sheepsIn);
				if(sheepsIn==this.sheeps.size())
				{
					System.out.println("win");
				}
				
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
	public boolean nearDog(Sheep s)
	{
		Vector2 vector;
		vector=new Vector2 (this.dog.getX()-s.getX() , this.dog.getY()-s.getY());
		if(Math.sqrt(Math.pow(vector.x,2)+Math.pow(vector.y,2))<200)
		{
			return true;
		}
		return false;
	}
	public Vector2 runAwayDog(Sheep s)
	{
		Vector2 vectorN=new Vector2 (s.getX()-this.dog.getX() , s.getY()-this.dog.getY());
		Vector2 vector=vectorN.nor();
		
		int v=1;
		
		Vector2 vector2;
		vector2=new Vector2 (this.dog.getX()-s.getX() , this.dog.getY()-s.getY());
		if(Math.sqrt(Math.pow(vector2.x,2)+Math.pow(vector2.y,2))<200)
		{
			v=1;
		}
		if(Math.sqrt(Math.pow(vector2.x,2)+Math.pow(vector2.y,2))<100)
		{
			v=3;
		}
		if(Math.sqrt(Math.pow(vector2.x,2)+Math.pow(vector2.y,2))<50)
		{
			v=6;
		}
		
		
		float Alto = Gdx.app.getGraphics().getHeight();

		float Ancho = Gdx.app.getGraphics().getWidth();

		vector.x=vector.x*v;
		vector.y=vector.y*v;
		
		if ((s.getX() + vector.x + s.getWidth()) >= Ancho) {

			vector.x =(Ancho - s.getX() - s.getWidth());
			
		}
		if ((s.getY() + vector.y + s.getHeight()) >= Alto) {
			vector.y = (Alto - s.getY() - s.getHeight());
			
		}
		if ((s.getX() + vector.x) < 0) {
			vector.x= 0;
			
		}
		if ((s.getY() + vector.y) < 0) {
			vector.y = 0;
			
		}
		
		vector.x=s.getX()+vector.x;
		vector.y=s.getY()+vector.y;
		return vector;		
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
