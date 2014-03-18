package com.me.shepherdMe.actor.input;

import java.util.List;

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
	private static Thread hilo= new Thread();

	public BackgroundUserInput(Background BG) {
		this.BG = BG;
		
	}

	
	 @Override public boolean touchDown(InputEvent event, float x, float y,
	  int pointer, int button) 
	  {
		 return true; 
	  }
	 
	 @Override
	 public void touchUp(InputEvent event, float x, float y,
			  int pointer, int button) 
	 {
		 hilo.interrupt();
	 }
	 

	
	 @Override
		public void touchDragged(final InputEvent event, float x, float y, int pointer) {
			
			
								
					Dog dog = BG.getLogica().getDog();

					Vector2 origen = new Vector2(dog.getX() , dog.getY());
					Vector2 destino = new Vector2(event.getStageX(), event.getStageY());

					Vector2 siguientePunto = siguientePunto(origen, destino);
					
					
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
