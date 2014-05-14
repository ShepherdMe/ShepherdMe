package com.me.shepherdMe.functions;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.me.shepherdMe.images.Imagenes;

public class Cronometro {

	private int segundos;
	private int minutos;
	//private TextureAtlas atlas;
	public AtlasRegion min1, min2, separator, sec1, sec2;
	private Timer timer;
	private cronometerTask timerTask;
	private boolean running = false;
	private float width, height;
	public boolean ready = false;

	public Cronometro() {
		this.width = Gdx.graphics.getWidth();
		this.height = Gdx.graphics.getHeight();
		this.minutos = 0;
		this.segundos = 0;

		/*atlas = new TextureAtlas(Gdx.files.internal("numeros/numbers.atlas"));
		min2 = atlas.findRegion("cero");
		min1 = atlas.findRegion("cero");
		sec1 = atlas.findRegion("cero");
		
		sec2 = atlas.findRegion("cero");
		
		separator = atlas.findRegion("separator");*/
		
		this.min2 = Imagenes.cero;
		this.min1 = Imagenes.cero;
		this.sec1 = Imagenes.cero;
		this.sec2 = Imagenes.cero;
		this.separator = Imagenes.separator;
		
		ready = true;
	}
	
	public List<AtlasRegion> getImages(){
		List<AtlasRegion> list = new ArrayList<AtlasRegion>();
		list.add(min1);
		list.add(min2);
		list.add(separator);
		list.add(sec1);
		list.add(sec2);
		return list;
	}

	public boolean isRunning() {
		return running;
	}

	public void start() {
		timer = new Timer();
		timerTask = new cronometerTask();
		timer.scheduleAtFixedRate(timerTask, 0, 1000);
		running = true;
	}

	public void stop() {
		running = false;
		if (timer != null && timerTask != null) {
			timerTask.cancel();
			timer.cancel();
			timer = null;
			timerTask = null;
		}
		this.segundos = 0;
		this.minutos = 0;
		sec1 = Imagenes.cero;
		sec2 = Imagenes.cero;
		min2 = Imagenes.cero;
		min1 = null;
	}

	public void pause() {
		running = false;
		if (timer != null && timerTask != null) {
			timerTask.cancel();
			timer.cancel();
			timer = null;
			timerTask = null;
		}
	}

	public void resume() {
		this.start();
	}

	public int getMinutos() {
		return this.minutos;
	}

	public int getSegundos() {
		return this.segundos;
	}

	class cronometerTask extends TimerTask {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			segundos++;
			if (segundos == 60) {
				segundos = 0;
				minutos++;
				
				if(minutos<10){
					min2 = intToString(minutos);
				}
				else{
					int decenas = minutos / 10;
					int unidades = minutos - decenas*10;
					boolean update1 = true;
					if(min1==null){
						min1 = intToString(decenas);
						update1 = false;
					}
					if(update1){
						min1 = intToString(decenas);
					}
					
					min2 = intToString(unidades);
				}
				
			}
			
			if (segundos < 10) {
				sec1 = Imagenes.cero;
				sec2 = intToString(segundos);
			}
			else{
				int decenas = segundos / 10;
				int unidades = segundos - decenas*10;
				sec1 = intToString(decenas);
				sec2 =intToString(unidades);
			}
			
		}
	}
	
	private AtlasRegion intToString(int x){
		AtlasRegion s = null;
		switch (x) {
		case 0:
			s= Imagenes.cero;
			break;
		case 1:
			s= Imagenes.uno;
			break;
		case 2:
			s= Imagenes.dos;
			break;
		case 3:
			s= Imagenes.tres;
			break;
		case 4:
			s= Imagenes.cuatro;
			break;
		case 5:
			s= Imagenes.cinco;
			break;
		case 6:
			s= Imagenes.seis;
			break;
		case 7:
			s= Imagenes.siete;
			break;
		case 8:
			s= Imagenes.ocho;
			break;
		case 9:
			s= Imagenes.nueve;
			break;
			
		default:
			break;
		}
		return s;
	}

}
