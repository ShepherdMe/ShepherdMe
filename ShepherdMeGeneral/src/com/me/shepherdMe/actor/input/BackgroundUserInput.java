package com.me.shepherdMe.actor.input;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.me.shepherdMe.actor.Background;
import com.me.shepherdMe.actor.Dog;
import com.me.shepherdMe.actor.Obstacle;
import com.me.shepherdMe.functions.Recta;

public class BackgroundUserInput extends InputListener {

	private Background BG;

	private float deltaY;
	private float deltaX;
	private float destinoX;
	private float destinoY;

	private float toX;

	private float toY;
	private static Thread hilo= new Thread();
	private static Timer timer;
	private static TimerTask timerTask;
	private static boolean running = false;
	private static double speed = 1;
	

	public BackgroundUserInput(Background BG) {
		this.BG = BG;
		
	}

	
	 @Override public boolean touchDown(final InputEvent event, float x, float y,
	  int pointer, int button) 
	  {
		 
		 if(running){
			 timerTask.cancel();
			 running = false;
		 }
		 toX = event.getStageX();
		 toY = event.getStageY();
		 if(!running){
			 running = true;
			 timer = new Timer();
			 timerTask = new TimerTask() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
//					System.out.println("Moving dog to : " + toX  + " , " + toY);
					Dog dog = BG.getLogica().getDog();
					float x = dog.getX();
					float y = dog.getY();
					double distance = Math.sqrt((toX-x)*(toX-x) + (toY-y)*(toY-y));
					speed =  8*distance/Gdx.graphics.getHeight();
					if(speed<1) speed = 1;
					moveDog();
				}
			};
			timer.scheduleAtFixedRate(timerTask, 0, 20);
		 }
		 return true; 
	  }
	 
	 @Override
	 public void touchUp(InputEvent event, float x, float y,
			  int pointer, int button) 
	 {
//		 if(running){
//			 timerTask.cancel();
//			 running = false;
//		 }
	 }
	 
	
	 @Override
		public void touchDragged(final InputEvent event, float x, float y, int pointer) {
			toX = event.getStageX();
			toY = event.getStageY();

		}
	 
	 private void moveDog(){
		 Dog dog = BG.getLogica().getDog();

			Vector2 origen = new Vector2(dog.getX() , dog.getY());
			Vector2 destino = new Vector2(toX, toY);

			Vector2 siguientePunto = siguientePunto(origen, destino);
			System.out.println(siguientePunto.toString());
			
				dog.setY(siguientePunto.y);
				dog.setX(siguientePunto.x);
				origen.set(siguientePunto);
										
			/*while(!origen.equals(destino))
			{
				siguientePunto = siguientePunto(origen, destino);
				dog.setY(siguientePunto.y);
				dog.setX(siguientePunto.x);
				origen.set(siguientePunto);
			}*/
	 }
	 

	private Vector2 siguientePunto(Vector2 origen, Vector2 destino) {
		// double distance = Math.sqrt(Math.pow(destino.x - origen.x,2) +
		// Math.pow(destino.y - origen.y, 2));

		Vector2 delta = new Vector2(destino.x - origen.x, destino.y - origen.y);
		delta.nor();
		delta.set((float)speed*delta.x, (float)speed*delta.y);
		
		Vector2 position = new Vector2(origen.x + delta.x, origen.y + delta.y);
		
		if(hitArea(position))
		{
			return origen;
		}
		else
		{
			return position;
		}
		//return delta;
	}
	
	private boolean hitArea(Vector2 v)
	{
		List<Obstacle> obstaculos = this.BG.getLogica().getObstacle();
		for (Obstacle obstacle : obstaculos) {
			if(obstacle.hitArea(v.x, v.y,this.BG.getLogica().getDog().getWidth(),this.BG.getLogica().getDog().getHeight()))
			{
				return true;
			}
		}
		return false;
	}

}
