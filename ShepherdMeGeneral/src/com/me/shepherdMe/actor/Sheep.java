package com.me.shepherdMe.actor;

import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.me.shepherdMe.ShepherdMe;

public class Sheep extends Actor {

	private ShepherdMe game;
	private ActorInvisible AI;

	private TextureRegion regionSheep;
	private Texture sheep;
	private RotateToAction rotate;
	private MoveByAction move;
	private double STEP;
	
	public boolean estaHuyendo=false;
	public boolean nuevoPunto=false;
	
	private int i = 1000;
	float amountX, amountY;
	
	private Vector2 destino;

	public Sheep(ShepherdMe game, ActorInvisible AI, int x, int y) {
		this.game = game;
		this.AI=AI;
		setPosition(x, y);
		this.setX(x);
		this.setY(y);
		setHeight(Gdx.graphics.getHeight()/7);
		setWidth(Gdx.graphics.getHeight()/7);
		setOriginX(getWidth() / 2);
		setOriginY(getHeight() / 2);
		this.destino= new Vector2(this.getX(), this.getY());
		
		this.STEP=Math.sqrt(Math.pow(Gdx.graphics.getWidth(), 2)+Math.pow(Gdx.graphics.getHeight(), 2))/100;

		sheep = new Texture(Gdx.files.internal("img/sheep.png"));

		regionSheep = new TextureRegion(sheep);

	}
	
	public boolean Equals(Sheep s)
	{
		if(s.getX()==this.getX()||s.getY()==this.getY())
		{
			return true;
		}
		return false;
	}

	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.draw(regionSheep, getX(), getY(), getOriginX(), getOriginY(),
				getWidth(), getHeight(), getScaleX(), getScaleY(),
				getRotation());

	}

	public void render() {
		Vector2 v = new Vector2();
		MoveByAction move;
		move = new MoveByAction();
		move.setAmount((float)this.STEP * v.x,(float)this.STEP * v.y);
		move.setDuration(5f);
	}
	
	/**
	 * Si el punto esta fuera de los limites lo ajusta
	 * @param v
	 * @return
	 */
	private Vector2 limitarAlBorde(Vector2 v)
	{
		if(v.x>Gdx.graphics.getWidth()-this.getWidth())
		{
			v.x=(float) (Gdx.graphics.getWidth()-this.getWidth()-STEP*2);
		}
		else if(v.x<0)
		{
			v.x=(float) (0+STEP*2);
		}
		
		if(v.y>Gdx.graphics.getHeight()-this.getHeight())
		{
			v.y=(float) (Gdx.graphics.getHeight()-this.getHeight()-STEP*2);
		}
		else if (v.y<0)
		{
			v.y=(float) (0+STEP*2);
		}
		
		return v;
	}
	
	
	private Vector2 nuevoDestino()
	{
			
		float x =  (float) (Math.random()*Gdx.graphics.getWidth());
		float y =  (float) (Math.random()* Gdx.graphics.getHeight());
		return limitarAlBorde(new Vector2(x,y));
	}
	
	public boolean estaEnDestino()
	{
		
		if( Math.abs(this.getX()-this.destino.x)<=this.STEP*2)
		{
			return true;
		}
		if( Math.abs(this.getY()-this.destino.y)<this.STEP*2)
		{
			return true;
		}
		
		return false;
	}
	
	public Vector2 moveSheep() {
		
		Vector2 origen = new Vector2(this.getX(), this.getY());
		System.out.println("En moveSheep nuevoPunto "+nuevoPunto);
		if(this.nuevoPunto||estaEnDestino())
		{
			System.out.println("HACE NUEVO DESTINO");
			System.out.println("VIEJO "+this.destino.toString());
			this.destino = nuevoDestino();
			System.out.println("NUEVO "+this.destino.toString());
			this.nuevoPunto=false;
			
		}
		System.out.println(destino.toString());

		Vector2 siguientePunto = siguientePunto(origen, destino);
		System.out.println("El siguiete punto es: "+siguientePunto.toString());
		System.out.println("El destino es : "+destino.toString());
		return siguientePunto;
	}
	
	
	private Vector2 siguientePunto(Vector2 origen, Vector2 destino) {

		Vector2 delta = new Vector2(destino.x - origen.x, destino.y - origen.y);
		delta.nor();
		
		Vector2 position = new Vector2(origen.x + delta.x, origen.y + delta.y);

		if (ovejaTocaElemento(position)) {
			this.nuevoPunto=true;
			return origen;
		} else {
			return position;
		}
	}
	
	
	/**
	 * Comprueba si el siguiente punto de la oveja toca a cualquier otro elemento
	 * @param siguientePunto
	 * @return
	 */
	public boolean ovejaTocaElemento(Vector2 siguientePunto)
	{
		List<Obstacle> obstaculos = this.AI.getLogica().getObstacle();
		for (Obstacle obstacle : obstaculos) {
			if (obstacle.hitArea(siguientePunto.x, siguientePunto.y,this.getWidth(), this.getHeight())) {
				return true;
			}
		}
		List<Sheep> ovejas = this.AI.getLogica().getSheeps();
		for (Sheep oveja : ovejas) {
			if(!oveja.Equals(this)){
				if (oveja.elementoTocaOveja(siguientePunto.x, siguientePunto.y, this.getWidth(), this.getHeight())) {
					return true;
				}
			}
			
		}
		return false;
	
	}

	/**
	 * Comprueba si un elemento toca a la oveja
	 * @param v
	 * @return
	 */
	
	public boolean elementoTocaOveja(float x, float y, float width, float height) {
		System.out.println("EL del this "+ this.getX()+" "+this.getY()+" "+this.getWidth()+" "+this.getHeight());
		System.out.println("Los parametros "+x+" "+y+" "+width+" "+height);
		if ((x + width >= this.getX()) && (x <= this.getX() + this.getWidth())) {
			if ((y + height >= this.getY()) && (y <= this.getY() + this.getHeight())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public double getStep()
	{
		return this.STEP;
	}
	
}


