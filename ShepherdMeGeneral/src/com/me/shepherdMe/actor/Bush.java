package com.me.shepherdMe.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;


public class Bush extends Obstacle{

	public Bush(float x, float y, float width, float height) {

		super(x, y, width, height);
		this.area = new Rectangle(x, y, width, height);

		this.obstacle = new Texture(Gdx.files.internal("img/level/muro.jpg"));
		this.regionObstacle = new TextureRegion(this.obstacle);

	}

	@Override
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
