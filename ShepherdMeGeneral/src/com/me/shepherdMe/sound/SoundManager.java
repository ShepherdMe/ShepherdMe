package com.me.shepherdMe.sound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class SoundManager {

	private AssetManager assetManager;

	public SoundManager() 
	{
	assetManager = new AssetManager();
	assetManager.load("sounds/prueba2.ogg", Music.class);
	}

	public boolean startMusic() {
		
		while(!assetManager.isLoaded("sounds/prueba2.ogg"))
		{
			System.out.println("Aqui seguimos");
			
		}
		
	    if(assetManager.isLoaded("sounds/prueba2.ogg")) {
	    Music music = assetManager.get("sounds/prueba2.ogg", Music.class);
	    music.play();
	    music.setLooping(true);
	    }else {
	        System.out.println("not loaded yet");
	        return false;
	    }
	    return true;
	}

}
