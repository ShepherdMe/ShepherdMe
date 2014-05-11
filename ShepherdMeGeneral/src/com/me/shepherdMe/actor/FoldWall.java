package com.me.shepherdMe.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class FoldWall extends Bush{

	public FoldWall(float x, float y, float width, float height, boolean vertical) {
		super(x, y, width, height);
		if(vertical)
			this.obstacle = new Texture(Gdx.files.internal("img/level/redilV.png"));
		else
			this.obstacle = new Texture(Gdx.files.internal("img/level/redilH.png"));
		this.regionObstacle = new TextureRegion(this.obstacle);
	}

}
