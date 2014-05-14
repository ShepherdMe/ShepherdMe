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

	public static TextureAtlas cronometro;
	public static AtlasRegion cero;
	public static AtlasRegion uno;
	public static AtlasRegion dos;
	public static AtlasRegion tres;
	public static AtlasRegion cuatro;
	public static AtlasRegion cinco;
	public static AtlasRegion seis;
	public static AtlasRegion siete;
	public static AtlasRegion ocho;
	public static AtlasRegion nueve;
	public static AtlasRegion separator;
	
	
	public static void cargarImagenes()
	{
		Imagenes.cargarImagenesMain();
		Imagenes.cargarImagenesNivel();
		Imagenes.cargarImagenesChooseLevel();
		Imagenes.cargarCronometro();

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
	
	public static void cargarCronometro()
	{
		Imagenes.cronometro = new TextureAtlas(Gdx.files.internal("numeros/numbers.atlas"));
		
		Imagenes.cero = Imagenes.cronometro.findRegion("cero");
		Imagenes.uno = Imagenes.cronometro.findRegion("uno");
		Imagenes.dos = Imagenes.cronometro.findRegion("dos");
		Imagenes.tres = Imagenes.cronometro.findRegion("tres");
		Imagenes.cuatro = Imagenes.cronometro.findRegion("cuatro");
		Imagenes.cinco = Imagenes.cronometro.findRegion("cinco");
		Imagenes.seis = Imagenes.cronometro.findRegion("seis");
		Imagenes.siete = Imagenes.cronometro.findRegion("siete");
		Imagenes.ocho = Imagenes.cronometro.findRegion("ocho");
		Imagenes.nueve = Imagenes.cronometro.findRegion("nueve");
		Imagenes.separator = Imagenes.cronometro.findRegion("separator");
	}

}
