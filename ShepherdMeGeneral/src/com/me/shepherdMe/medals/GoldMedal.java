package com.me.shepherdMe.medals;

public class GoldMedal extends Medal{

	public GoldMedal(){
		super();
		this.medal = this.medalAtlas.findRegion("gold");
	}
	
}
