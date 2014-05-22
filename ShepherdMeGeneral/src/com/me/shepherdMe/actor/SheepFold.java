package com.me.shepherdMe.actor;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;

public class SheepFold {

	private static final int THICKNESS_FACTOR = 20;

	private List<Bush> obstacles;
	private BackgroundFold BF;
	private Bush openable;
	private float screenWidth, screenHeight;
	private float x, y, width, height;
	private Open open;
	private Vector2 bottomLeft, bottomRight, topLeft, topRight;
	private boolean isOpen = false;

	public SheepFold(float x, float y, float width, float heigth, Open open) {
		this.BF = new BackgroundFold(x, y, width, heigth);
		obstacles = new ArrayList<Bush>();
		this.x = x;
		this.y = y;
		this.height = heigth;
		this.width = width;
		this.screenWidth = Gdx.graphics.getWidth();
		this.screenHeight = Gdx.graphics.getHeight();
		this.open = open;

		// Corners of the fold
		this.bottomLeft = new Vector2(this.getX(), this.getY());
		this.bottomRight = new Vector2(this.getX() + this.getWidth(),
				this.getY());
		this.topRight = new Vector2(this.getX() + this.getWidth(), this.getY()
				+ this.getHeight());
		this.topLeft = new Vector2(this.getX(), this.getY() + this.getHeight());

		if (open == Open.TOP) {
			obstacles.add(new FoldWall(x, y, screenWidth / THICKNESS_FACTOR,
					this.height, true)); // left
			obstacles.add(new FoldWall(x, y, this.width, screenHeight
					/ THICKNESS_FACTOR, false)); // bottom
			obstacles.add(new FoldWall(x + this.width - screenWidth
					/ THICKNESS_FACTOR, y, screenWidth / THICKNESS_FACTOR,
					this.height, true));// right
			openable = new FoldWall(x, y + this.height - screenHeight
					/ THICKNESS_FACTOR, this.width, screenHeight
					/ THICKNESS_FACTOR, false); // top
		} else if (open == Open.BOTTOM) {
			obstacles.add(new FoldWall(x, y, screenWidth / THICKNESS_FACTOR,
					this.height, true)); // left
			obstacles.add(new FoldWall(x + this.width - screenWidth
					/ THICKNESS_FACTOR, y, screenWidth / THICKNESS_FACTOR,
					this.height, true));// right
			obstacles.add(new FoldWall(x, y + this.height - screenHeight
					/ THICKNESS_FACTOR, this.width, screenHeight
					/ THICKNESS_FACTOR, false)); // top
			openable = new FoldWall(x, y, this.width, screenHeight
					/ THICKNESS_FACTOR, false);
		} else if (open == Open.LEFT) {
			obstacles.add(new FoldWall(x + this.width - screenWidth
					/ THICKNESS_FACTOR, y, screenWidth / THICKNESS_FACTOR,
					this.height, true));// right
			obstacles.add(new FoldWall(x, y + this.height - screenHeight
					/ THICKNESS_FACTOR, this.width, screenHeight
					/ THICKNESS_FACTOR, false)); // top
			obstacles.add(new FoldWall(x, y, this.width, screenHeight
					/ THICKNESS_FACTOR, false)); // bottom
			openable = new FoldWall(x, y, screenWidth / THICKNESS_FACTOR,
					this.height, true);
		} else if (open == Open.RIGHT) {
			obstacles.add(new FoldWall(x, y + this.height - screenHeight
					/ THICKNESS_FACTOR, this.width, screenHeight
					/ THICKNESS_FACTOR, false)); // top
			obstacles.add(new FoldWall(x, y, this.width, screenHeight
					/ THICKNESS_FACTOR, false)); // bottom
			obstacles.add(new FoldWall(x, y, screenWidth / THICKNESS_FACTOR,
					this.height, true)); // left
			openable = new FoldWall(x + this.width - screenWidth
					/ THICKNESS_FACTOR, y, screenWidth / THICKNESS_FACTOR,
					this.height, true);
		}
	}

	public boolean chocaConPuerta(Actor actor) {

		if (openable.hitArea(actor.getX(), actor.getY(),
				actor.getWidth(), actor.getHeight())) {
			return true;
		}

		return false;
	}

	public boolean isInFold(Actor actor) {

		Array<Vector2> polygon = new Array<Vector2>();

		polygon.add(topLeft);
		// polygon.add(topRight);
		// polygon.add(bottomLeft);
		polygon.add(bottomRight);

		Vector2 point = new Vector2(actor.getX() + actor.getWidth() / 2,
				actor.getY() + actor.getHeight() / 2);
		if (puntoEnPoligono(polygon, point)) {
			return true;
		}

		return false;
	}

	public boolean puntoEnPoligono(Array<Vector2> poligono, Vector2 punto) {
		float XMIN = poligono.get(0).x;
		float XMAX = poligono.get(1).x;
		float YMAX = poligono.get(0).y;
		float YMIN = poligono.get(1).y;

		if (punto.x <= XMAX && punto.x >= XMIN && punto.y <= YMAX
				&& punto.y >= YMIN) {
			return true;
		}
		return false;
	}

	public List<Bush> getFoldObstacles() {
		return this.obstacles;
	}

	public Bush getGate() {
		return openable;
	}

	public void open(Table stage) {
		if (!isOpen) {
			stage.removeActor(openable);
			isOpen = true;
		}
	}

	public void close(Table stage) {
		if (isOpen) {
			stage.addActor(openable);
			isOpen = false;
		}
	}

	public boolean isOpen() {
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

	public BackgroundFold getBF() {
		return BF;
	}
}
