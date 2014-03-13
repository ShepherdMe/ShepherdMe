package com.me.shepherdMe.functions;

public class Cronometro {

	private int segundos;
	private int minutos;
	private boolean congelado;
	
	public Cronometro()
	{
		this.minutos=0;
		this.segundos=0;
		this.congelado=false;
	}
	
	public void run()
	{
		while(!congelado)
		{
			this.segundos++;
			if(this.segundos==59)
			{
				this.segundos=0;
				this.minutos++;
			}
		}
	}
	
	public void stop()
	{
		this.congelado=true;
		this.segundos=0;
		this.minutos=0;
	}
	
	public void pause()
	{
		this.congelado=true;
	}
	
	public void resume()
	{
		this.congelado=false;
		this.run();
	}
	
	public int getMinutos()
	{
		return this.minutos;
	}
	
	public int getSegundos()
	{
		return this.segundos;
	}
	
	
	
	
}
