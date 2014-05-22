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
	public static AtlasRegion nivelBGFold;
	public static AtlasRegion nivelOpen;
	public static AtlasRegion nivelClose;


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
	
	public static TextureAtlas medallas;
	public static AtlasRegion oro;
	public static AtlasRegion plata;
	public static AtlasRegion bronce;
	
	public static TextureAtlas UI;
	public static AtlasRegion cartel;
	public static AtlasRegion botonExit;
	public static AtlasRegion botonContinuar;
	
	public static TextureAtlas main;
	public static TextureAtlas ovejas;
	public static AtlasRegion tittle;
	public static AtlasRegion play;
	public static AtlasRegion sonidoON;
	public static AtlasRegion sonidoOFF;
	public static AtlasRegion musicON;
	public static AtlasRegion musicOFF;
	public static AtlasRegion sol;
	public static AtlasRegion oveja0;
	public static AtlasRegion oveja1;
	public static AtlasRegion oveja2;
	public static AtlasRegion oveja3;
	public static AtlasRegion oveja4;
	public static AtlasRegion oveja5;
	public static AtlasRegion oveja6;
	
	public static TextureAtlas chooseLevel1,chooseLevel2;
	public static AtlasRegion dog1;
	public static AtlasRegion dog2;
	public static AtlasRegion cartelCL;
	public static AtlasRegion AL;
	public static AtlasRegion AR;
	public static AtlasRegion back;
	public static AtlasRegion levelLock;
	public static AtlasRegion n1,n2,n3,n4,n5,n6,n7,n8,n9,n10,n11,n12;

	
	public static void cargarImagenes()
	{
		Imagenes.cargarImagenesMain();
		Imagenes.cargarImagenesNivel();
		Imagenes.cargarImagenesChooseLevel();
		Imagenes.cargarCronometro();
		Imagenes.cargarMedallas();
		Imagenes.cargarUI();

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
		Imagenes.nivelBGFold = Imagenes.nivel.findRegion("BGFold");	
		Imagenes.nivelOpen = Imagenes.nivel.findRegion("open");	
		Imagenes.nivelClose = Imagenes.nivel.findRegion("close");

		

	}
	
	public static void cargarImagenesMain()
	{
		main = new TextureAtlas(Gdx.files.internal("img/main/main.pack"));
		ovejas = new TextureAtlas(Gdx.files.internal("img/main/sheeps.pack"));
		
		tittle = main.findRegion("tittle");
		play = main.findRegion("play");
		sonidoON = main.findRegion("sonidoON");
		sonidoOFF = main.findRegion("sonidoOFF");
		musicON = main.findRegion("musicON");
		musicOFF = main.findRegion("musicOFF");
		sol = main.findRegion("sol");
		
		oveja0 = ovejas.findRegion("sheep0");
		oveja1 = ovejas.findRegion("sheep1");
		oveja2 = ovejas.findRegion("sheep2");
		oveja3 = ovejas.findRegion("sheep3");
		oveja4 = ovejas.findRegion("sheep4");
		oveja5 = ovejas.findRegion("sheep5");
		oveja6 = ovejas.findRegion("sheep6");
	}
	
	public static AtlasRegion getOveja(int x){
		AtlasRegion s = null;
		switch (x) {
		case 0:
			s= Imagenes.oveja0;
			break;
		case 1:
			s= Imagenes.oveja1;
			break;
		case 2:
			s= Imagenes.oveja2;
			break;
		case 3:
			s= Imagenes.oveja3;
			break;
		case 4:
			s= Imagenes.oveja4;
			break;
		case 5:
			s= Imagenes.oveja5;
			break;
		case 6:
			s= Imagenes.oveja6;
			break;
		default:
			break;
		}
		return s;
	}
	
	public static void cargarImagenesChooseLevel()
	{
		Imagenes.chooseLevel1 = new TextureAtlas(Gdx.files.internal("img/chooseLevel/niveles.pack"));
		Imagenes.chooseLevel2 = new TextureAtlas(Gdx.files.internal("img/chooseLevel/niveles2.pack"));
		
		Imagenes.cartelCL = Imagenes.chooseLevel2.findRegion("chooseLevel");
		Imagenes.dog1= Imagenes.chooseLevel2.findRegion("dog1");
		Imagenes.dog2= Imagenes.chooseLevel2.findRegion("dog2");
		Imagenes.AL= Imagenes.chooseLevel2.findRegion("arrowLeft");
		Imagenes.AR= Imagenes.chooseLevel2.findRegion("arrowRight");
		Imagenes.back= Imagenes.chooseLevel2.findRegion("back");
		Imagenes.levelLock= Imagenes.chooseLevel2.findRegion("NLocked");
		
		Imagenes.n1 = Imagenes.chooseLevel1.findRegion("N1");
		Imagenes.n2 = Imagenes.chooseLevel1.findRegion("N2");
		Imagenes.n3 = Imagenes.chooseLevel1.findRegion("N3");
		Imagenes.n4 = Imagenes.chooseLevel1.findRegion("N4");
		Imagenes.n5 = Imagenes.chooseLevel1.findRegion("N5");
		Imagenes.n6 = Imagenes.chooseLevel1.findRegion("N6");
		Imagenes.n7 = Imagenes.chooseLevel2.findRegion("N7");
		Imagenes.n8 = Imagenes.chooseLevel2.findRegion("N8");
		Imagenes.n9 = Imagenes.chooseLevel2.findRegion("N9");
		Imagenes.n10 = Imagenes.chooseLevel2.findRegion("N10");
		Imagenes.n11 = Imagenes.chooseLevel2.findRegion("N11");
		Imagenes.n12 = Imagenes.chooseLevel2.findRegion("N12");

	}
	
	public static AtlasRegion getNivel(int x){
		AtlasRegion s = null;
		switch (x) {
		case 0:
			s= Imagenes.n1;
			break;
		case 1:
			s= Imagenes.n2;
			break;
		case 2:
			s= Imagenes.n3;
			break;
		case 3:
			s= Imagenes.n4;
			break;
		case 4:
			s= Imagenes.n5;
			break;
		case 5:
			s= Imagenes.n6;
			break;
		case 6:
			s= Imagenes.n7;
			break;
		case 7:
			s= Imagenes.n8;
			break;
		case 8:
			s= Imagenes.n9;
			break;
		case 9:
			s= Imagenes.n10;
			break;
		case 10:
			s= Imagenes.n11;
			break;
		case 11:
			s= Imagenes.n12;
			break;
		default:
			break;
		}
		return s;
	}
	
	
	public static void cargarCronometro()
	{
		Imagenes.cronometro = new TextureAtlas(Gdx.files.internal("img/numeros/numbers.atlas"));
		
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
	
	public static void cargarMedallas()
	{
		Imagenes.medallas= new TextureAtlas(Gdx.files.internal("img/medallas/medals.pack"));
		Imagenes.oro = Imagenes.medallas.findRegion("oro");
		Imagenes.plata = Imagenes.medallas.findRegion("plata");
		Imagenes.bronce = Imagenes.medallas.findRegion("bronce");	
	}

	public static void cargarUI()
	{
		Imagenes.UI= new TextureAtlas(Gdx.files.internal("img/ui/ui.pack"));
		Imagenes.cartel = Imagenes.UI.findRegion("cartel");
		Imagenes.botonExit = Imagenes.UI.findRegion("botonExit");
		Imagenes.botonContinuar = Imagenes.UI.findRegion("botonContinuar");
	}

}
