package utils;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
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
			levels.set(0, 3);
			for (int i = 1; i < LevelManager.numberOfLevels; i++) 
			{
				levels.set(i, -1);				
			}
		}
	}
	
	public static void comprobarNivel(int numero,Image cartel,List<Image> arrayMedallas,List<Image> arrayCandados)
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
			LevelManager.ponerCandado(cartel,arrayCandados);//habra q tener un array de candados
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
	
	public static void ponerCandado(Image cartel,List<Image> arrayCandados)
	{
		Image lock = new Image(new Texture(Gdx.files.internal("img/chooseLevel/lock.png")));
		
		lock.setBounds(cartel.getX()+cartel.getWidth()/4, cartel.getY()+cartel.getHeight()/4, cartel.getWidth()/2, cartel.getHeight()/2);
		
		arrayCandados.add(lock);
	}

	

}
