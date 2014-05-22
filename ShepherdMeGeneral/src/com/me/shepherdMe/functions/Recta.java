/*package com.me.shepherdMe.functions;

import com.badlogic.gdx.math.Vector2;

public class Recta {

	private Vector2 origen;
	private Vector2 destino;
	private float m;

	public Recta(Vector2 origen, Vector2 destino) {
		this.origen = origen;
		this.destino = destino;
		CalcularM();
	}

	private void CalcularM() 
	{
		if(this.destino.x==this.origen.x)
		{
			this.m=0;
		}
		else
		{
			this.m = (this.destino.y - this.origen.y)/ (this.destino.x - this.origen.x);
		}
	}
	
	public Vector2 siguientePunto()
	{
		Vector2 v = new Vector2();
		
		if(m==0)
		{
			v.y= this.origen.y;
			v.x=this.origen.x+1;
		}
		else
		{
			if(m<0)
			{
				v.y=this.origen.y-1;
			}
			else
			{
				v.y=this.origen.y+1;					
			}
			v.x=this.origen.x/m;
		}
		return v;
	}

}*/
