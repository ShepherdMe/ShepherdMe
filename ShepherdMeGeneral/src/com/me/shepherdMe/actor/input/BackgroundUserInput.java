package com.me.shepherdMe.actor.input;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.Array;
import com.me.shepherdMe.actor.ActorInvisible;
import com.me.shepherdMe.actor.Dog;
import com.me.shepherdMe.actor.Obstacle;
import com.me.shepherdMe.actor.Sheep;
import com.me.shepherdMe.sound.SoundManager;

public class BackgroundUserInput extends InputListener {

	private static final int MIN_SPEED = 5;
	private static double STEP = 8;

	private ActorInvisible AI;

	private float deltaY;
	private float deltaX;
	private float destinoX;
	private float destinoY;

	private float toX;

	private float toY;
	private static Thread hilo = new Thread();
	private static Timer timer;
	private static TimerTask timerTask;
	private static boolean running = false;
	private static double speed = 1;
	private static double distance;
	private static boolean pause = false;

	public BackgroundUserInput(ActorInvisible Ai) {
		this.AI = Ai;
		this.STEP=Math.sqrt(Math.pow(Gdx.graphics.getWidth(), 2)+Math.pow(Gdx.graphics.getHeight(), 2))/100;
	}

	public void setPause(boolean pause) {
		this.pause = pause;
	}

	@Override
	public boolean touchDown(final InputEvent event, float x, float y,
			int pointer, int button) {
		SoundManager.playDog();
		if (!pause) {
			if (running) {
				timerTask.cancel();
				running = false;
			}
			toX = event.getStageX();
			toY = event.getStageY();
			if (!running) {
				running = true;
				timer = new Timer();
				timerTask = new TimerTask() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						if (!pause) {
							Dog dog = AI.getLogica().getDog();
							Array<Vector2> polygon = new Array();
							polygon.add(new Vector2(dog.getX()-dog.getWidth()/4, dog.getY()-dog.getHeight()/4));
							polygon.add(new Vector2(
									dog.getX() +dog.getWidth()/4,dog.getY()-dog.getHeight()/4));
							polygon.add(new Vector2(
									dog.getX() +dog.getWidth()/4,dog.getY()+dog.getHeight()/4
											+ dog.getHeight()));
							polygon.add(new Vector2(dog.getX()-dog.getWidth()/4, dog.getY()+dog.getHeight()/4));

							Vector2 point = new Vector2(toX, toY);
							if (!Intersector.isPointInPolygon(polygon, point)) {
								float x = dog.getX();
								float y = dog.getY();
								distance = Math.sqrt((toX - x) * (toX - x)
										+ (toY - y) * (toY - y));
								speed = distance / Gdx.graphics.getHeight()
										* STEP;

								if (speed < MIN_SPEED)
									speed = MIN_SPEED;
								moveDog();
							}
						}
					}
				};
				timer.scheduleAtFixedRate(timerTask, 0, 20);
			}
		}
		return true;
	}

	@Override
	public void touchDragged(final InputEvent event, float x, float y,
			int pointer) {
		if (!pause) {
			toX = event.getStageX();
			toY = event.getStageY();
		}

	}

	private void moveDog() {
		Dog dog = AI.getLogica().getDog();

		Vector2 origen = new Vector2(dog.getX(), dog.getY());
		Vector2 destino = new Vector2(toX, toY);

		Vector2 siguientePunto = siguientePunto(origen, destino);

		dog.setY(siguientePunto.y);
		dog.setX(siguientePunto.x);
		origen.set(siguientePunto);
	}

	private Vector2 siguientePunto(Vector2 origen, Vector2 destino) {

		Vector2 delta = new Vector2(destino.x - origen.x, destino.y - origen.y);
		delta.nor();

		delta.set((float) speed * delta.x, (float) speed * delta.y);

		Vector2 position = new Vector2(origen.x + delta.x, origen.y + delta.y);

		if (hitArea(position)) {
			return origen;
		} else {
			return position;
		}
	}

	private boolean hitArea(Vector2 v) {
		List<Obstacle> obstaculos = this.AI.getLogica().getObstacle();
		for (Obstacle obstacle : obstaculos) {
			if (obstacle.hitArea(v.x, v.y, this.AI.getLogica().getDog()
					.getWidth(), this.AI.getLogica().getDog().getHeight())) {
				return true;
			}
		}
		List<Sheep> ovejas = this.AI.getLogica().getSheeps();
		for (Sheep oveja : ovejas) {
			if (oveja.elementoTocaOveja(v.x, v.y, this.AI.getLogica().getDog()
					.getWidth(), this.AI.getLogica().getDog().getHeight())) {
				return true;
			}
		}
		return false;
	}

}
