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

	private static int numberOfLevels=12;
	private static ArrayList<Integer> levels = new ArrayList<Integer>();
	
	/***
	 * Los niveles tienen
	 * -1 si no estan abiertos
	 * 0 abierto sin medalla
	 * 1 cobre
	 * 2 plata
	 * 3 oro
	 * 
	 */
	public static void loadLevels()
	{
		Preferences level = Gdx.app.getPreferences("Levels");
		
		for (int i = 0; i < LevelManager.numberOfLevels; i++) 
		{
			levels.add(level.getInteger(Integer.toString(i)));
		}
		if(levels.get(0)==0 && levels.get(1)==0)//Caso en el que se ejecuta la primera vez la app
		{
			levels.set(0, 3);//quitar esta linea era para pruebas
			for (int i = 1; i < LevelManager.numberOfLevels; i++) 
			{
				levels.set(i, -1);				
			}
		}
	}
	
	public static void comprobarNivel(int numero,Image cartel,List<Image> arrayMedallas)
	{
		int medallas = LevelManager.levels.get(numero);
		
		System.out.println("NUMERO "+numero);
		System.out.println(medallas);
		
		if(medallas>-1)
		{
			LevelManager.ponerMedallas(medallas,cartel,arrayMedallas);
		}
		else
		{
			//arrayImagenes.add(lock);//imagenDeBloqueado
		}
	}
	
	public static void ponerMedallas(int medallas, Image cartel,List<Image> arrayMedallas)
	{
		Image b,p,o;
		
		if(medallas>0)//ponerBronce
		{
			b = new Image( new BronzeMedal().getTexture());
			b.setBounds(cartel.getX()+cartel.getWidth()/15, cartel.getY(), cartel.getWidth()/4, cartel.getHeight()/4);
			arrayMedallas.add(b);
			
			if(medallas>1)//ponerPlata
			{
				p = new Image( new SilverMedal().getTexture());
				p.setBounds(b.getX()+b.getWidth()*1.2f, cartel.getY(), cartel.getWidth()/4, cartel.getHeight()/4);
				arrayMedallas.add(p);
				
				if(medallas>2)//ponerOro
				{
					o = new Image( new GoldMedal().getTexture());
					o.setBounds(p.getX()+p.getWidth()*1.2f, cartel.getY(), cartel.getWidth()/4, cartel.getHeight()/4);
					arrayMedallas.add(o);
				}
			}
		}
	}
	
	public static boolean bloqueado (int nivel){
		if(levels.get(nivel)!=-1)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public static void actualizarNivel (int nivel, int medalla)
	{
		levels.set(nivel, medalla);
	}
	
	

	
	

}
