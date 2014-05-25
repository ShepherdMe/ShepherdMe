package utils;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.me.shepherdMe.medals.BronzeMedal;
import com.me.shepherdMe.medals.GoldMedal;
import com.me.shepherdMe.medals.SilverMedal;

public class LevelManager {

	private static int numberOfLevels = 12;
	private static ArrayList<Integer> levels = new ArrayList<Integer>();
	private static ArrayList<Integer> levelsLock = new ArrayList<Integer>();
	//private static int medals = 0;

	/***
	 * Los niveles tienen -1 si no estan abiertos 0 abierto sin medalla 1 cobre
	 * 2 plata 3 oro
	 * 
	 */
	public static void loadLevels() {
		Preferences level = Gdx.app.getPreferences("Levels");
		Preferences levelLock = Gdx.app.getPreferences("LevelsLock");

		for (int i = 0; i < LevelManager.numberOfLevels; i++) {
			levels.add(level.getInteger(Integer.toString(i)));
			levelsLock.add(levelLock.getInteger(Integer.toString(i)));
			
		}

		if (levels.get(0) == 0 && levels.get(1) == 0)// Caso en el que se
														// ejecuta la primera
														// vez la app
		{
			levels.set(0, 0);
			for (int i = 1; i < LevelManager.numberOfLevels; i++) {
				levels.set(i, -1);
			}

			// Cambiar el numero de medallas necesarias
			levelsLock.set(0, 0);
			levelsLock.set(1, 1);//1
			levelsLock.set(2, 3);//3
			levelsLock.set(3, 5);//5
			levelsLock.set(4, 7);//7
			levelsLock.set(5, 9);//9
			levelsLock.set(6, 14);//14
			levelsLock.set(7, 16);//16
			levelsLock.set(8, 19);//19
			levelsLock.set(9, 23);//23
			levelsLock.set(10, 27);//27
			levelsLock.set(11, 31);//31

			
//			levelsLock.set(0, 0);
//			levelsLock.set(1, 0);//1
//			levelsLock.set(2, 0);//3
//			levelsLock.set(3, 0);//5
//			levelsLock.set(4, 0);//7
//			levelsLock.set(5, 0);//9
//			levelsLock.set(6, 0);//14
//			levelsLock.set(7, 0);//16
//			levelsLock.set(8, 0);//19
//			levelsLock.set(9, 0);//23
//			levelsLock.set(10,0);//27
//			levelsLock.set(11, 0);//31
			
		}
	}
	
	public static boolean esPrimeraPartida1(){
		Preferences pref = Gdx.app.getPreferences("Primera");
		
		boolean primera1 = pref.getBoolean("primera1", true);
		
		return primera1;
//		return true;
	}
	public static boolean esPrimeraPartida2(){
		Preferences pref = Gdx.app.getPreferences("Primera");
		
		boolean primera2 = pref.getBoolean("primera2", true);
		
		return primera2;
//		return true;
	}
	
	public static void setPrimeraPartida1(boolean primera){//Nivel 1
		Preferences pref = Gdx.app.getPreferences("Primera");
		
		pref.putBoolean("primera1", primera);

		pref.flush();
	}
	
	public static void setPrimeraPartida2(boolean primera){//Nivel 6
		Preferences pref = Gdx.app.getPreferences("Primera");
		
		pref.putBoolean("primera2", primera);

		pref.flush();
	}

	public static void comprobarNivel(int numero, Image cartel,
			List<Image> arrayMedallas) {
		int medallas = LevelManager.levels.get(numero);

		System.out.println("NUMERO " + numero);
		System.out.println(medallas);

		if (medallas > -1) {
			LevelManager.ponerMedallas(medallas, cartel, arrayMedallas);
		}
		/*
		 * else { //arrayImagenes.add(lock);//imagenDeBloqueado }
		 */
	}

	public static void ponerMedallas(int medallas, Image cartel,
			List<Image> arrayMedallas) {
		Image b, p, o;

		if (medallas > 0)// ponerBronce
		{
			b = new Image(new BronzeMedal().getTexture());
			b.setBounds(cartel.getX() + cartel.getWidth() / 15, cartel.getY(),
					cartel.getWidth() / 4, cartel.getHeight() / 4);
			arrayMedallas.add(b);

			if (medallas > 1)// ponerPlata
			{
				p = new Image(new SilverMedal().getTexture());
				p.setBounds(b.getX() + b.getWidth() * 1.2f, cartel.getY(),
						cartel.getWidth() / 4, cartel.getHeight() / 4);
				arrayMedallas.add(p);

				if (medallas > 2)// ponerOro
				{
					o = new Image(new GoldMedal().getTexture());
					o.setBounds(p.getX() + p.getWidth() * 1.2f, cartel.getY(),
							cartel.getWidth() / 4, cartel.getHeight() / 4);
					arrayMedallas.add(o);
				}
			}
		}
	}

	public static boolean bloqueado(int nivel) {
		if (levels.get(nivel) != -1) {
			return false;
		} else {
			return true;
		}
	}

	public static void actualizarNivel(int nivel, int medalla) {
		System.out.println("Numero de nivel "+nivel);
		System.out.println("MeDALLA "+medalla);
		if(levels.get(nivel-1)<medalla)
		{
			levels.set(nivel-1, medalla);
		}
		LevelManager.guardarResultados();
		
	}

	public static void desbloquearNiveles() {
		for (int i = 0; i < LevelManager.numberOfLevels; i++) {
			if (levels.get(i) == -1) {
				if (levelsLock.get(i) <= numeroMedallas()) {
					actualizarNivel(i+1, 0);
				}
			}
		}
		//System.out.println(levels.toString());
		//System.out.println("Medallas " + numeroMedallas());
	}

	private static int numeroMedallas() {
		int n = 0;
		for (int i : levels) {
			if (i != -1) {
				n = n + i;
			}
		}
		return n;
	}

	public static void guardarResultados()
	{
		Preferences level = Gdx.app.getPreferences("Levels");
		Preferences levelLock = Gdx.app.getPreferences("LevelsLock");
		
		for(int i=0;i<LevelManager.numberOfLevels;i++)
		{
			level.putInteger(i+"", LevelManager.levels.get(i));
		}
		for(int i=0;i<LevelManager.numberOfLevels;i++)
		{
			levelLock.putInteger(i+"", LevelManager.levelsLock.get(i));
		}
		
		level.flush();
		levelLock.flush();
	}
}
