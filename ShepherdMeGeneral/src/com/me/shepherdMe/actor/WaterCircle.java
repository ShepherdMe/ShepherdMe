package com.me.shepherdMe.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class WaterCircle extends Obstacle implements InterfaceObstacle {

	private double longitud;// Es la altura o anchura del lado de un cuadrado
							// con diagonal el radio
	private Vector2 centro;

	public WaterCircle(float x, float y, float radio) {

		super(x, y, (float) Math.sqrt(Math.pow(radio, 2) / 2) * 2, (float) Math
				.sqrt(Math.pow(radio, 2) / 2) * 2);
		this.area = new Circle(x, y, radio);

		this.longitud = Math.sqrt(Math.pow(radio, 2) / 2);
		this.centro = new Vector2(x + (float) longitud, y + (float) longitud);

		this.obstacle = new Texture(Gdx.files.internal("img/blue.png"));
		this.regionObstacle = new TextureRegion(this.obstacle);
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

	@Override
	public boolean hitArea(float x, float y, float width, float height)
	{
		if (x >= this.centro.x - longitud && x <= this.centro.x) 
		{
			if (y <= this.centro.y)
			{
				return ((Circle) this.area).contains(x + width, y + height);
			}
			else
			{
				return ((Circle) this.area).contains(x + width, y);
			}
		} 
		else
		{
			if (x < this.centro.x + longitud && x > this.centro.x) 
			{
				if (y <= this.centro.y)
				{
					return ((Circle) this.area).contains(x, y + height);
				}
				else
				{
					return ((Circle) this.area).contains(x, y);
				}
			}
			else
			{
				return false;
			}
		}

		}

	}

