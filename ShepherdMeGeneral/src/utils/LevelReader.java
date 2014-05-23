package utils;

import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.me.shepherdMe.actor.BlackSheep;
import com.me.shepherdMe.actor.Bush;
import com.me.shepherdMe.actor.Cloud;
import com.me.shepherdMe.actor.Dog;
import com.me.shepherdMe.actor.Obstacle;
import com.me.shepherdMe.actor.Open;
import com.me.shepherdMe.actor.Sheep;
import com.me.shepherdMe.actor.SheepFold;
import com.me.shepherdMe.actor.WaterCircle;
import com.me.shepherdMe.table.LogicaLevel;

public class LevelReader {

	private static Document datos;
	private static float width = Gdx.graphics.getWidth(), height = Gdx.graphics.getHeight();
	
	private static int gold, silver, bronze;
	
	public static int getGold() {
		return gold;
	}

	public static int getSilver() {
		return silver;
	}

	public static int getBronze() {
		return bronze;
	}

	public static void readXML(String path) {
		try {
			FileHandle fxmlFile = Gdx.files.internal(path);
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fxmlFile.read());
			doc.getDocumentElement().normalize();
			datos=doc;		

			Element root = (Element) doc.getDocumentElement();
			gold = Integer.parseInt(root.getAttribute("gold"));
			silver = Integer.parseInt(root.getAttribute("silver"));
			bronze = Integer.parseInt(root.getAttribute("bronze"));
			
		} catch (Exception e) {
			System.out.println("ERROR DE LA LECHE!!");
			e.printStackTrace();
		}
	}

	public static Dog cargarPerro() {
		
		System.out.println("Ha cargado bien? " + datos);
		Element perro = (Element) datos.getElementsByTagName("dog").item(0);
		float x = Float.parseFloat(perro.getElementsByTagName("x").item(0).getTextContent());
		float y = Float.parseFloat(perro.getElementsByTagName("y").item(0).getTextContent());
	
		Dog dog = new Dog(x*width,y*height);
		return dog;
	}

	public static ArrayList<Sheep> cargarOvejas(LogicaLevel LL)
	 {
		NodeList nList = datos.getElementsByTagName("sheep");
		
		ArrayList<Sheep> sheeps = new ArrayList<Sheep>();
		float x,y;
		String color;
		Element aux;
				
		for (int i = 0; i < nList.getLength(); i++) {
			aux= (Element) nList.item(i);
			x = Float.parseFloat(aux.getElementsByTagName("x").item(0).getTextContent());
			y = Float.parseFloat(aux.getElementsByTagName("y").item(0).getTextContent());
			color =aux.getElementsByTagName("color").item(0).getTextContent().toString();
			if(color.equals("n"))
			{
				sheeps.add(new BlackSheep(LL, x*width, y*height));
			}
			else
			{
				sheeps.add(new Sheep(LL, x*width, y*height));
			}
			
		}
		
		return sheeps;
		
	 }
	
	public static ArrayList<Cloud> cargarNubes()
	{
		NodeList nList = datos.getElementsByTagName("cloud");
		
		ArrayList<Cloud> clouds = new ArrayList<Cloud>();
		float x,y,w,h;
		Element aux;
				
		for (int i = 0; i < nList.getLength(); i++) {
			aux= (Element) nList.item(i);
			x = Float.parseFloat(aux.getElementsByTagName("x").item(0).getTextContent());
			y = Float.parseFloat(aux.getElementsByTagName("y").item(0).getTextContent());
			w = Float.parseFloat(aux.getElementsByTagName("width").item(0).getTextContent());
			h = Float.parseFloat(aux.getElementsByTagName("height").item(0).getTextContent());
			clouds.add(new Cloud(x*width, y*height, w*width, h*height));
		}
		
		return clouds;
	}
	
	public static SheepFold cargarRedil(){
		Element redil = (Element) datos.getElementsByTagName("redil").item(0);
		float x = Float.parseFloat(redil.getAttribute("x"));
		float y = Float.parseFloat(redil.getAttribute("y"));
		float w = Float.parseFloat(redil.getAttribute("width"));
		float h = Float.parseFloat(redil.getAttribute("height"));
		String o = redil.getAttribute("open");
		Open open;
		if(o.equals("TOP"))
			open = Open.TOP;
		else if(o.equals("BOTTOM"))
			open = Open.BOTTOM;
		else if(o.equals("LEFT"))
			open = Open.LEFT;
		else
			open = Open.RIGHT;
		
		SheepFold sf = new SheepFold(x*width, y*height, w*width, h*height, open);
		return sf;
	}
	
	public static ArrayList<Obstacle> cargarObstaculos()
	{
		NodeList nList = datos.getElementsByTagName("obstacle");
		
		ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
		float x,y,w,h,radio;
		Element aux;
		Obstacle obs=null;
				
		for (int i = 0; i < nList.getLength(); i++) {
			aux= (Element) nList.item(i);
			x = Float.parseFloat(aux.getElementsByTagName("x").item(0).getTextContent());
			y = Float.parseFloat(aux.getElementsByTagName("y").item(0).getTextContent());
			
			
			if(aux.getAttribute("type").equals("Bush"))
			{
				w = Float.parseFloat(aux.getElementsByTagName("width").item(0).getTextContent());
				h = Float.parseFloat(aux.getElementsByTagName("height").item(0).getTextContent());
				obs = new Bush(x*width,y*height,w*width,h*height);
			}
			else if(aux.getAttribute("type").equals("Water"))
			{
				radio = Float.parseFloat(aux.getElementsByTagName("radio").item(0).getTextContent());
				obs = new WaterCircle(x*width, y*height, radio*width);
			}
			
			obstacles.add(obs);
		}
		
		return obstacles;
	}
	

}
