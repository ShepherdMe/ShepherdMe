package com.me.shepherdMe.sound;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class SoundManager {

	private static boolean musicOn;
	private static boolean effectsOn;

	private static Sound dogSound;
	private static Sound sheepSound1;
	private static Sound sheepSound2;
	private static Music mainMenu;
	
	public SoundManager() 
	{		
	}
	
	public static void asignarValores()
	{
		
		Preferences pref = Gdx.app.getPreferences("Audio");
		
		musicOn = pref.getBoolean("music");
		effectsOn = pref.getBoolean("effects");

	}
	
	public static void playMusicMenu()
	{
		if(musicOn)
		{
			mainMenu = Gdx.audio.newMusic(Gdx.files.getFileHandle("sounds/mainMenu.mp3", FileType.Internal));
			mainMenu.setVolume(1);
			mainMenu.play();
			mainMenu.setLooping(true);
		}
	}
	
	public static void playDog()
	{
		if(effectsOn)
		{
			SoundManager.dogSound.play(1);
		}
	}
	
	public static void playSheep()
	{
		if(effectsOn)
		{
			if(new Random().nextInt()%2==0)
			{
				SoundManager.sheepSound1.play(1);
			}
			else
			{
				SoundManager.sheepSound2.play(1);
			}
		}
	}
	
	public static void stopMusicMenu()
	{
		if(mainMenu.isPlaying())
		{
			mainMenu.stop();
		}
	}
	
	public static void loadAudios()
	{
		if(effectsOn)
		{
			dogSound = Gdx.audio.newSound(Gdx.files.internal("sounds/dog.wav"));
			sheepSound1 = Gdx.audio.newSound(Gdx.files.internal("sounds/balido_1.mp3"));
			sheepSound2 = Gdx.audio.newSound(Gdx.files.internal("sounds/balido_2.mp3"));

		}
	}

	public static boolean getMusicOn()
	{
		return musicOn;
	}
	
	public static void setMusicOn(boolean b)
	{
		musicOn=b;
		
		Preferences pref = Gdx.app.getPreferences("Audio");
		
		pref.putBoolean("music", b);
		
		pref.flush();
		
		if(b)
		{
			SoundManager.playMusicMenu();
		}
		else
		{
			mainMenu.stop();
		}
	}
	
	public static boolean getEffectsOn()
	{
		return effectsOn;
	}
	
	public static void setEffectsOn(boolean b)
	{
		effectsOn=b;
		
		Preferences pref = Gdx.app.getPreferences("Audio");
		
		pref.putBoolean("effects", b);
		
		pref.flush();
		
	}
	

}
