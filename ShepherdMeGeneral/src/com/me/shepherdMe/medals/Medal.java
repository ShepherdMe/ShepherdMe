package com.me.shepherdMe.medals;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class Medal {

	protected TextureRegion medal;
	

	public TextureRegion getTexture(){
		return this.medal;
	}
	
}
