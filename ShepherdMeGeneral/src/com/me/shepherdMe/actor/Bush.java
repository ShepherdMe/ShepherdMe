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

		if ((x + width >= this.getX()+ this.getWidth()/4) && (x <= this.getX() + 3*this.getWidth()/4)) {
			if ((y + height >= this.getY()+this.getHeight()/4)
					&& (y <= this.getY() + 3*this.getHeight()/4)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}
