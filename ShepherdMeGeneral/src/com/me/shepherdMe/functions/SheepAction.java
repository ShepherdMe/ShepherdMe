package com.me.shepherdMe.functions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.RelativeTemporalAction;

public class SheepAction extends RelativeTemporalAction {
	
	private float amountX, amountY;
	private int i=1;

	protected void updateRelative (float percentDelta) {
		if(i==1000)
		{
			setAmount();
			i=0;
		}
		float x = actor.getX();
		
		float y = actor.getY();
		
		float CambioX=amountX * percentDelta/4;
		
		float CambioY=amountY * percentDelta/4;
		
		float Alto=Gdx.app.getGraphics().getHeight();
		
		float Ancho=Gdx.app.getGraphics().getWidth();
		
		if((x+CambioX+actor.getWidth())>=Ancho)
		{
			
			CambioX = (Ancho-x-actor.getWidth());
			i=0;
		}
		if((y+CambioY+actor.getHeight())>=Alto)
		{
			CambioY = (Alto-y-actor.getHeight());
			i=0;
		}
		if((x+CambioX)<0)
		{
			CambioX = 0;
			i=0;
		}
		if((y+CambioY)<0)
		{
			CambioY = 0;
			i=0;
		}
		
		actor.translate(CambioX,CambioY);
		i++;
	}

	public void setAmount () {
		
		int N=(int)(Math.random()*10 + 1);
		
		if(N==1||N==2)
		{
			amountX = 0;
			amountY = 0;
		}
		else
		{
			double angulo= Math.toRadians(N*45);//Pasamos a radianes
			Vector2 v= new Vector2();
			amountX=(float) Math.sin(angulo);
			amountY=(float) Math.cos(angulo);
			
		}
	}

	public float getAmountX () {
		return amountX;
	}

	public void setAmountX (float x) {
		amountX = x;
	}

	public float getAmountY () {
		return amountY;
	}

	public void setAmountY (float y) {
		amountY = y;
	}
	public void restar()
	{
		
	}

}
