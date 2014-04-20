package com.me.shepherdMe.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;

public class WaterCircle extends Obstacle{

	private Vector2 centro;
	private float radio;

	public WaterCircle(float x, float y, float radio) {

		super(x-radio, y-radio, radio*2,radio*2);
		this.area = new Circle(x, y, radio);
		this.radio = radio;
		this.centro= new Vector2(x,y);
		this.obstacle = new Texture(Gdx.files.internal("img/level/blue.png"));
		this.regionObstacle = new TextureRegion(this.obstacle);
		this.regionObstacle.setRegion(x-radio, y-radio, radio*2, radio*2);

	}

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
