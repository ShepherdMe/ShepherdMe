package com.me.shepherdMe.actor;

import java.util.ArrayList;
import java.util.List;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;

public class SheepFold{
	
	private static final int THICKNESS_FACTOR = 20;
	
	private List<Bush> obstacles;
	private Bush openable;
	private float screenWidth, screenHeight;
	private float x, y, width, height;
	private Open open;
	private Vector2 bottomLeft, bottomRight, topLeft, topRight;
	private boolean isOpen = false;

	
	public SheepFold(float x, float y, float width, float heigth, Open open){
		obstacles = new ArrayList<Bush>();
		this.x = x;
		this.y = y;
		this.height = heigth;
		this.width = width;
		this.screenWidth = Gdx.graphics.getWidth();
		this.screenHeight = Gdx.graphics.getHeight();
		this.open = open;
		
		//Corners of the fold
		this.bottomLeft = new Vector2(this.getX(), this.getY());
		this.bottomRight = new Vector2(this.getX() + this.getWidth(), this.getY());
		this.topRight = new Vector2(this.getX() + this.getWidth(), this.getY() + this.getHeight());
		this.topLeft = new Vector2(this.getX(), this.getY() + this.getHeight());
		
		if(open == Open.TOP){
			obstacles.add(new Bush(x, y, screenWidth/THICKNESS_FACTOR, this.height)); //left
			obstacles.add(new Bush(x, y, this.width, screenHeight/THICKNESS_FACTOR)); //bottom
			obstacles.add(new Bush(x+this.width-screenWidth/THICKNESS_FACTOR, y, screenWidth/THICKNESS_FACTOR, this.height));//right
			openable = new Bush(x, y+this.height-screenHeight/THICKNESS_FACTOR, this.width, screenHeight/THICKNESS_FACTOR); //top
		}
		else if(open == Open.BOTTOM){
			obstacles.add(new Bush(x, y, screenWidth/THICKNESS_FACTOR, this.height)); //left
			obstacles.add(new Bush(x+this.width-screenWidth/THICKNESS_FACTOR, y, screenWidth/THICKNESS_FACTOR, this.height));//right
			obstacles.add(new Bush(x, y+this.height-screenHeight/THICKNESS_FACTOR, this.width, screenHeight/THICKNESS_FACTOR)); //top
			openable = new Bush(x, y, this.width, screenHeight/THICKNESS_FACTOR);
		}
		else if(open == Open.LEFT){
			obstacles.add(new Bush(x+this.width-screenWidth/THICKNESS_FACTOR, y, screenWidth/THICKNESS_FACTOR, this.height));//right
			obstacles.add(new Bush(x, y+this.height-screenHeight/THICKNESS_FACTOR, this.width, screenHeight/THICKNESS_FACTOR)); //top
			obstacles.add(new Bush(x, y, this.width, screenHeight/THICKNESS_FACTOR)); //bottom
			openable = new Bush(x, y, screenWidth/THICKNESS_FACTOR, this.height);
		}
		else if(open == Open.RIGHT){
			obstacles.add(new Bush(x, y+this.height-screenHeight/THICKNESS_FACTOR, this.width, screenHeight/THICKNESS_FACTOR)); //top
			obstacles.add(new Bush(x, y, this.width, screenHeight/THICKNESS_FACTOR)); //bottom
			obstacles.add(new Bush(x, y, screenWidth/THICKNESS_FACTOR, this.height)); //left
			openable = new Bush(x+this.width-screenWidth/THICKNESS_FACTOR, y, screenWidth/THICKNESS_FACTOR, this.height);
		}
	}
	
	public boolean isInFold(Actor actor){
		
		Array<Vector2> polygon = new Array<Vector2>();
		
		if(open == Open.TOP){
			polygon.add(bottomLeft);
			polygon.add(bottomRight);
			polygon.add(new Vector2(bottomRight.x,topRight.y-height/2));
			polygon.add(new Vector2(bottomLeft.x,topRight.y-height/2));
		}
		else if(open == Open.BOTTOM){
			polygon.add(topLeft);
			polygon.add(topRight);
			polygon.add(new Vector2(topLeft.x,topLeft.y - height/2));
			polygon.add(new Vector2(topRight.x,topLeft.y - height/2));
		}
		else if(open == Open.LEFT){
			polygon.add(topRight);
			polygon.add(bottomRight);
			polygon.add(new Vector2(topLeft.x+width/2,topLeft.y));
			polygon.add(new Vector2(topLeft.x+width/2,bottomLeft.y));
		}
		else if(open == Open.RIGHT){
			polygon.add(topLeft);
			polygon.add(bottomLeft);
			polygon.add(new Vector2(topLeft.x+width/2,topLeft.y));
			polygon.add(new Vector2(topLeft.x+width/2,bottomLeft.y));
		}
		

		Vector2 point = new Vector2(actor.getX()+actor.getWidth()/2, actor.getY() + actor.getHeight()/2);
		
		if(Intersector.isPointInPolygon(polygon, point))
			return true;
		return false;
	}
	
	public List<Bush> getFoldObstacles(){
		return this.obstacles;
	}
	
	public Bush getGate(){
		return openable;
	}
	
	public void open(Table stage){
		if(!isOpen){
			stage.removeActor(openable);
			isOpen = true;
		}
	}
	
	public void close(Table stage){
		if(isOpen){
			stage.addActor(openable);
			isOpen = false;
		}
	}
	
	public boolean isOpen(){
		return isOpen;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}
}
