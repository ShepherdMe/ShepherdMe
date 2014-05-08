package com.me.shepherdMe.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.me.shepherdMe.table.LogicaLevel;

public class BlackSheep extends Sheep {

	public BlackSheep(LogicaLevel AI, float x, float y) {
		super(AI, x, y);
		// TODO Auto-generated constructor stub
		this.sheep = new Texture(Gdx.files.internal("img/main/sheep_7.png"));//oveja negra
		this.regionSheep = new TextureRegion(sheep);
	}

}
