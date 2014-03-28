package com.me.shepherdMe.functions;

import java.util.Timer;
import java.util.TimerTask;

import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class Cronometro {

	private int segundos;
	private int minutos;
	private Label label;
	private Timer timer;
	private cronometerTask timerTask;
	
	public Cronometro(Label label)
	{
		this.minutos=0;
		this.segundos=0;
		this.label = label;
	}
	
	public void start()
	{
		timer = new Timer();
		timerTask = new cronometerTask();
		timer.scheduleAtFixedRate(timerTask, 0, 1000);	
	}
	
	public void stop()
	{
		if(timer!=null && timerTask!=null){
			timerTask.cancel();
			timer.cancel();
			timer = null;
			timerTask = null;
		}
		this.segundos=0;
		this.minutos=0;
		setTextToLabel();
	}
	
	public void pause()
	{
		if(timer!=null && timerTask!=null){
			timerTask.cancel();
			timer.cancel();
			timer = null;
			timerTask = null;
		}
	}
	
	public void resume()
	{
		this.start();
	}
	
	public int getMinutos()
	{
		return this.minutos;
	}
	
	public int getSegundos()
	{
		return this.segundos;
	}
	
	class cronometerTask extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			segundos++;
			if(segundos==60)
			{
				segundos=0;
				minutos++;
			}
			setTextToLabel();
		}
	}
	
	private void setTextToLabel(){
		if(segundos<10)
			label.setText(minutos + ":0" + segundos);
		else
			label.setText(minutos + ":" + segundos);
	}
	
}
