package utils;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.me.shepherdMe.actor.Bush;
import com.me.shepherdMe.actor.Cloud;
import com.me.shepherdMe.actor.Dog;
import com.me.shepherdMe.actor.Obstacle;
import com.me.shepherdMe.actor.Sheep;
import com.me.shepherdMe.actor.WaterCircle;
import com.me.shepherdMe.table.LogicaLevel;

public class LevelReader {

	private static Document datos;

	public LevelReader(String level) {

	}

	public static void readXML(String path) {
		try {

			File fXmlFile = new File(path);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

			datos=doc;		

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Dog cargarPerro() {
		
		Element perro = datos.getElementById("dog");
		float x = Float.parseFloat(perro.getElementsByTagName("x").item(0).getTextContent());
		float y = Float.parseFloat(perro.getElementsByTagName("y").item(0).getTextContent());
		
		// Habria que cambiar el constructor para que pudieramos colocarlo en el
		// centro del redil por ejemplo
	
		Dog dog = new Dog();

		return dog;
	}

	public static ArrayList<Sheep> cargarOvejas(LogicaLevel LL)
	 {
		NodeList nList = datos.getElementsByTagName("sheep");
		
		ArrayList<Sheep> sheeps = new ArrayList<Sheep>();
		float x,y;
		Element aux;
				
		for (int i = 0; i < nList.getLength(); i++) {
			aux= (Element) nList.item(i);
			x = Float.parseFloat(aux.getElementsByTagName("x").item(0).getTextContent());
			y = Float.parseFloat(aux.getElementsByTagName("y").item(0).getTextContent());
			sheeps.add(new Sheep(LL, x, y));
		}
		
		return sheeps;
		
	 }
	
	public static ArrayList<Cloud> cargarNubes()
	{
		NodeList nList = datos.getElementsByTagName("cloud");
		
		ArrayList<Cloud> clouds = new ArrayList<Cloud>();
		float x,y,width,height;
		Element aux;
				
		for (int i = 0; i < nList.getLength(); i++) {
			aux= (Element) nList.item(i);
			x = Float.parseFloat(aux.getElementsByTagName("x").item(0).getTextContent());
			y = Float.parseFloat(aux.getElementsByTagName("y").item(0).getTextContent());
			width = Float.parseFloat(aux.getElementsByTagName("width").item(0).getTextContent());
			height = Float.parseFloat(aux.getElementsByTagName("height").item(0).getTextContent());
			clouds.add(new Cloud(x, y, width, height));
		}
		
		return clouds;
	}
	
	public static ArrayList<Obstacle> cargarObstaculos()
	{
		NodeList nList = datos.getElementsByTagName("obstacle");
		
		ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
		float x,y,width,height,radio;
		Element aux;
		Obstacle obs=null;
				
		for (int i = 0; i < nList.getLength(); i++) {
			aux= (Element) nList.item(i);
			x = Float.parseFloat(aux.getElementsByTagName("x").item(0).getTextContent());
			y = Float.parseFloat(aux.getElementsByTagName("y").item(0).getTextContent());
			
			
			if(aux.getAttribute("type")=="Bush")
			{
				width = Float.parseFloat(aux.getElementsByTagName("width").item(0).getTextContent());
				height = Float.parseFloat(aux.getElementsByTagName("height").item(0).getTextContent());
				obs = new Bush(x,y,width,height);
			}
			else if(aux.getAttribute("type")=="Water")
			{
				radio = Float.parseFloat(aux.getElementsByTagName("radio").item(0).getTextContent());
				obs = new WaterCircle(x, y, radio);
			}
			
			obstacles.add(obs);
		}
		
		return obstacles;
	}
	

}
