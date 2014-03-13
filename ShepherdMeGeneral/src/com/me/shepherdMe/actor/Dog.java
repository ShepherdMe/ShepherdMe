package com.me.shepherdMe.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.me.shepherdMe.ShepherdMe;

public class Dog extends Actor {

	private ShepherdMe game;
	private TextureRegion regionDog;
	private Texture dog;
	private RotateToAction rotate;
	private MoveByAction move;

	public Dog(ShepherdMe game) {

		this.game = game;
		setPosition(50, 50);
		setHeight(64);
		setWidth(64);
		setX(getWidth() / 2);
		setY(getHeight() / 2);

		/*
		 * move = new MoveByAction();
		 * 
		 * move.setAmount(50, 0); move.setDuration(10f);
		 * 
		 * this.addAction(move);
		 */

		dog = new Texture(Gdx.files.internal("img/dog.png"));

		regionDog = new TextureRegion(dog);

		/*addListener(new InputListener() {
			public void touchDrgged

		});*/

	}

	public void draw(SpriteBatch batch, float parentAlpha) {
		/*Vector2 v;
		Vector2 siguientePunto;

		if (Gdx.input.isTouched()) {

			this.translate(Gdx.input.getX()-this.getStage().getWidth(), Gdx.input.getY()-this.getStage().getHeight());
			//this.getStage().getWidth()
		 
			//v = this.screenToLocalCoordinates(new Vector2(Gdx.input.getX(),Gdx.input.getY()));
			//v = new Vector2(Gdx.input.getX(),Gdx.input.getY());

			// v.set(this.screenToLocalCoordinates(new Vector2
			// (Gdx.input.getX(), Gdx.input.getY());
			// while(getX()!=v.x && getY()!=v.y)

			/*Gdx.app.log("mover de ", "X " + getX() + " Y " + getY());
			Gdx.app.log("mover a ", v.toString());

			siguientePunto = lineaRecta(v.x, v.y);

			setX(siguientePunto.x);
			setY(siguientePunto.y);*/
			/*batch.draw(regionDog, getX(), getY(), getOriginX(), getOriginY(),
					getWidth(), getHeight(), getScaleX(), getScaleY(),
					getRotation());
			Gdx.app.log("movido a", "X " + getX() + " Y " + getY());*/
			// this.addAction(Actions.moveTo(v.x, v.y));

		//}
		batch.draw(regionDog, getX(), getY(), getOriginX(), getOriginY(),
				getWidth(), getHeight(), getScaleX(), getScaleY(),
				getRotation());

		/*
		 * addListener(new InputListener(){
		 * 
		 * @Override public boolean touchDown (InputEvent event, float x, float
		 * y, int pointer, int button) { //Gdx.app.log("Mover",
		 * "Reconoce pulsacion en x:"+x+" y:"+y); //Gdx.app.log("Mover",
		 * "Reconoce pulsacion en x:"+Gdx.input.getX()+" y:"+Gdx.input.getY());
		 * Vector2 v =
		 * event.getListenerActor().localToAscendantCoordinates(getParent(),(new
		 * Vector2(x, y))); Gdx.app.log("mover en back", v.toString()); return
		 * true; } });
		 */
	}

	//public void move(Vector2 v) {
		/*
		 * Vector2 siguientePunto; while(getX()!=v.x && getY()!=v.y) {
		 * siguientePunto=lineaRecta(getX(), getY());
		 * 
		 * setX(siguientePunto.x); setY(siguientePunto.y);
		 * 
		 * //this.addAction(Actions.moveTo(v.x, v.y)); }
		 */
	//}

	/*public Vector2 lineaRecta(float x, float y) {
		Vector2 siguientePunto = new Vector2();

		if (x == getX()) {
			if (y < getY()) {
				siguientePunto.y = getY() + 1;
			} else {
				siguientePunto.y = getY() - 1;
			}
		} else {
			float m = (y - getY()) / (x - getX());
			if (m < 0) {
				siguientePunto.y = getY() + 1;
			} else {
				siguientePunto.y = getY() - 1;
			}
			siguientePunto.x = getX() / m;
		}

		/*
		 * Gdx.app.log("y",y+""); Gdx.app.log("gety",getY()+"");
		 * Gdx.app.log("x",x+""); Gdx.app.log("getx",getX()+"");
		 */

		//Gdx.app.log("m", m + "");

		// Gdx.app.log("MOVER", siguientePunto.toString());
		//return siguientePunto;
	//}
}
