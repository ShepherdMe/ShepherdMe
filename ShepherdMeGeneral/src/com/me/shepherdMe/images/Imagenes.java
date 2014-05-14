package com.me.shepherdMe.images;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public class Imagenes {

	private static TextureAtlas nivel;
	public static AtlasRegion nivelDog;
	public static AtlasRegion nivelSheep;
	public static AtlasRegion nivelBlackSheep;
	public static AtlasRegion nivelCloud;
	public static AtlasRegion nivelBush;
	public static AtlasRegion nivelWater;
	public static AtlasRegion nivelRedilH;
	public static AtlasRegion nivelRedilV;

	
	
	
	public static void cargarImagenes()
	{
		Imagenes.cargarImagenesMain();
		Imagenes.cargarImagenesNivel();
		Imagenes.cargarImagenesChooseLevel();
	}
	
	public static void cargarImagenesNivel()
	{
		Imagenes.nivel = new TextureAtlas(Gdx.files.internal("img/level/level.pack"));
		Imagenes.nivelDog = Imagenes.nivel.findRegion("dog");
		Imagenes.nivelSheep = Imagenes.nivel.findRegion("sheep");
		Imagenes.nivelBlackSheep = Imagenes.nivel.findRegion("blackSheep");
		Imagenes.nivelCloud = Imagenes.nivel.findRegion("cloud");
		Imagenes.nivelBush = Imagenes.nivel.findRegion("bush");
		Imagenes.nivelWater = Imagenes.nivel.findRegion("blue");
		Imagenes.nivelRedilH = Imagenes.nivel.findRegion("redilH");
		Imagenes.nivelRedilV = Imagenes.nivel.findRegion("redilV");	
	}
	
	public static void cargarImagenesMain()
	{
		
	}
	
	public static void cargarImagenesChooseLevel()
	{
		
	}

}
