package com.me.shepherdMe.actor;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.me.shepherdMe.images.Imagenes;


public class Bush extends Obstacle{

	public Bush(float x, float y, float width, float height) {

		super(x, y, width, height);
		this.area = new Rectangle(x, y, width, height);

		//this.obstacle = new Texture(Gdx.files.internal("img/level/bush.png"));
		this.regionObstacle = new TextureRegion(Imagenes.nivelBush);

	}

	@Override
	public boolean hitArea(float x, float y, float width, float height) {

		if ((x + width >= this.getX()) && (x+ 3*width/5 <= this.getX() + this.getWidth())) {
			if ((y + height >= this.getY())
					&& (y+ 3*height/5 <= this.getY() + this.getHeight())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}
