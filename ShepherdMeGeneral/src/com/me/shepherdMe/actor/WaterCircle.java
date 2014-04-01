package com.me.shepherdMe.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class WaterCircle extends Obstacle implements InterfaceObstacle {

	private double longitud;// Es la altura o anchura del lado de un cuadrado
							// con diagonal el radio
	private Vector2 centro;
	private float radio;

	public WaterCircle(float x, float y, float radio) {

		super(x-radio, y-radio, radio*2,radio*2);
		this.area = new Circle(x, y, radio);
		this.radio = radio;
		//this.longitud = Math.sqrt(Math.pow(radio, 2)*2);
		//this.centro = new Vector2(x + (float) longitud, y + (float) longitud);
		this.centro= new Vector2(x,y);
		this.obstacle = new Texture(Gdx.files.internal("img/blue.png"));
		this.regionObstacle = new TextureRegion(this.obstacle);
		this.regionObstacle.setRegion(x-radio, y-radio, radio*2, radio*2);
		;

		this.obstacle = new Texture(Gdx.files.internal("img/blue.png"));// Cambiarle
																		// la
																		// foto
																		// y
																		// poner
																		// un
																		// width
																		// y
																		// height
																		// determinado
		this.regionObstacle = new TextureRegion(this.obstacle);
	}

	/*
	 * @Override public boolean hitArea(float x, float y, float width, float
	 * height) { if (x >= this.centro.x - longitud && x <= this.centro.x) { if
	 * (y <= this.centro.y) { return ((Circle) this.area).contains(x + width, y
	 * + height); } else { return ((Circle) this.area).contains(x + width, y); }
	 * } else { if (x < this.centro.x + longitud && x > this.centro.x) { if (y
	 * <= this.centro.y) { return ((Circle) this.area).contains(x, y + height);
	 * } else { return ((Circle) this.area).contains(x, y); } } else { return
	 * false; } }
	 * 
	 * }
	 */

	// @Override
	// public boolean hitArea(float x, float y, float width, float height) {
	// if (((Circle) this.area).contains(x, y)
	// || ((Circle) this.area).contains(x + width, y)
	// || ((Circle) this.area).contains(x, y + height)
	// || ((Circle) this.area).contains(x + width, y + height)) {
	//
	// return true;
	//
	// } else {
	// return false;
	// }
	//
	// }

	// @Override
	// public boolean hitArea(float x, float y, float width, float height) {
	//
	// if (((Math.abs(centro.x - x)) <= radio || (Math.abs(centro.x - x +
	// width)) <= radio)
	// && ((Math.abs(centro.y - y)) <= radio || (Math.abs(centro.y - y +
	// height)) <= radio))
	// {
	// return true;
	// } else {
	// return false;
	// }
	// }
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {

		batch.draw(regionObstacle, getX(), getY(), getOriginX(), getOriginY(),
				getWidth(), getHeight(), getScaleX(), getScaleY(),
				getRotation());

	}
	
	
	@Override
	public boolean hitArea(float x, float y, float width, float height) {

		Vector2 v0 = new Vector2(x, y);
		Vector2 v1 = new Vector2(x + width, y);
		Vector2 v2 = new Vector2(x + width, y + height);
		Vector2 v3 = new Vector2(x, y + height);

		if ((Intersector.distanceLinePoint(v0, v1, this.centro) <= this.radio)
				|| ((Intersector.distanceLinePoint(v1, v2, this.centro) <= this.radio))
				|| ((Intersector.distanceLinePoint(v2, v3, this.centro) <= this.radio))
				|| ((Intersector.distanceLinePoint(v3, v1, this.centro) <= this.radio))) {
			return true;
		} else {
			return false;
		}

	}
}
