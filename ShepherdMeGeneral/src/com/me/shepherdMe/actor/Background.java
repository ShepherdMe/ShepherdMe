package com.me.shepherdMe.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.me.shepherdMe.ShepherdMe;
import com.me.shepherdMe.table.LogicaLevel;

public class Background extends Actor {

	ShepherdMe game;
	TextureRegion background;
	Texture Tbackground;
	LogicaLevel logica;
	
	public Background(ShepherdMe game, final LogicaLevel logica)
	{
		this.game=game;
		this.logica=logica;
		setPosition(0, 0);
		setHeight(Gdx.graphics.getHeight());
		setWidth(Gdx.graphics.getWidth());
		
		Tbackground = new Texture(Gdx.files.internal("img/fondo.png"));
		background = new TextureRegion(Tbackground,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

		/*addListener(new InputListener(){
			@Override
		    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) 
		    {
				//Gdx.app.log("Mover", "Reconoce pulsacion en x:"+x+" y:"+y);
				//Gdx.app.log("Mover", "Reconoce pulsacion en x:"+Gdx.input.getX()+" y:"+Gdx.input.getY());
				Vector2 v = event.getListenerActor().localToAscendantCoordinates(getParent(),(new Vector2(x, y)));
				Gdx.app.log("mover en back", v.toString());
				logica.moveDog(v);
				return true;
		    }
		});	*/

	}
	
	public void draw(SpriteBatch batch, float parentApha)
	{
		batch.draw(background,getX(),getY());
	}
	
	public LogicaLevel getLogica()
	{
		return this.logica;
	}
	
}
