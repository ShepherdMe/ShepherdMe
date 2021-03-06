package com.me.shepherdMe.actor;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.me.shepherdMe.images.Imagenes;

public class FoldWall extends Bush{

	public FoldWall(float x, float y, float width, float height, boolean vertical) {
		super(x, y, width, height);
		if(vertical)
		{
			this.regionObstacle = new TextureRegion(Imagenes.nivelRedilV);
		}
		else
		{
			this.regionObstacle = new TextureRegion(Imagenes.nivelRedilH);
		}
			//this.obstacle = new Texture(Gdx.files.internal("img/level/redilH.png"));
		//this.regionObstacle = new TextureRegion(this.obstacle);
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
