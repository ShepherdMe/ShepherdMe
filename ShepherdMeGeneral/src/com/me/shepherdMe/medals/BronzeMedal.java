package com.me.shepherdMe.medals;

public class BronzeMedal extends Medal{

	public BronzeMedal(){
		super();
		this.medal = this.medalAtlas.findRegion("bronze");
	}
	
}
