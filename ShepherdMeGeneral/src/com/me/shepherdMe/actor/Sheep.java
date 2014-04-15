package com.me.shepherdMe.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.me.shepherdMe.ShepherdMe;

public class Sheep extends Actor {

	private ShepherdMe game;
	private TextureRegion regionSheep;
	private Texture sheep;
	private RotateToAction rotate;
	private MoveByAction move;
	private double STEP;

	private int i = 1000;
	float amountX, amountY;

	public Sheep(ShepherdMe game, int x, int y) {
		this.game = game;
		setPosition(x, y);
		this.setX(x);
		this.setY(y);
		setHeight(Gdx.graphics.getHeight()/7);
		setWidth(Gdx.graphics.getHeight()/7);
		setOriginX(getWidth() / 2);
		setOriginY(getHeight() / 2);
		
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

	public void setAmount() {

		int N = (int) (Math.random() * 20 + 1);

		if (N <=10) {
			amountX = 0;
			amountY = 0;
		} else {
			double angulo = Math.toRadians(N * 45);// Pasamos a radianes
			Vector2 v = new Vector2();
			amountX = (float) Math.sin(angulo);
			amountY = (float) Math.cos(angulo);

		}
		

	}

	public Vector2 moveSheep() {

		if (i % 200 == 0) {
			setAmount();
			
		}
		float x = this.getX();

		float y = this.getY();

		float CambioX = amountX*1/2;

		float CambioY = amountY*1/2;

		float Alto = Gdx.app.getGraphics().getHeight();

		float Ancho = Gdx.app.getGraphics().getWidth();

		if ((x + CambioX + this.getWidth()) >= Ancho) {

			CambioX = -1*(Ancho - x - this.getWidth());
			setAmount();
		}
		if ((y + CambioY + this.getHeight()) >= Alto) {
			CambioY = -1*(Alto - y - this.getHeight());
			setAmount();
		}
		if ((x + CambioX) < 0) {
			CambioX = 0;
			setAmount();
		}
		if ((y + CambioY) < 0) {
			CambioY = 0;
			setAmount();
		}
		i++;

		Vector2 v = new Vector2();
		v.x = x + CambioX;
		v.y = y + CambioY;
		return v;

	}

	public boolean hitArea(float x, float y, float width, float height) {
		if ((x + width >= this.getX()) && (x <= this.getX() + this.getWidth())) {
			if ((y + height >= this.getY())
					&& (y <= this.getY() + this.getHeight())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
}


