package com.me.shepherdMe.medals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class Medal {

	protected TextureRegion medal;
	
	protected TextureAtlas medalAtlas;
	
	public Medal(){
		medalAtlas = new TextureAtlas(Gdx.files.internal("img/medals.atlas"));
	}
	
	public TextureRegion getTexture(){
		return this.medal;
	}
	
}
