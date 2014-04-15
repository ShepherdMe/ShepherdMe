package com.me.shepherdMe.screens;

import java.util.Timer;
import java.util.TimerTask;

import utils.GraphicManager;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.me.shepherdMe.ShepherdMe;
import com.me.shepherdMe.actor.SheepFold;
import com.me.shepherdMe.functions.Cronometro;
import com.me.shepherdMe.table.LogicaLevel;

public class Level implements Screen {

	private ShepherdMe game;
	private Stage stage;
	private LogicaLevel logica;
	private Label cronometerLabel;
	private Cronometro cronometer;
	private Image cartelExit, buttonContinue, buttonExit;
	private boolean showingPause = false, stop = false;
	private Timer timer = new Timer();
	private LevelChooser lc;

	public Level(ShepherdMe game, LevelChooser lc) {
		Gdx.app.log("LEVEL", "contruye level");
		this.game = game;
		this.stage = new Stage();
		this.lc = lc;
	}

	@Override
	public void render(float delta) {

		if ((Gdx.input.isKeyPressed(Keys.BACK) || Gdx.input
				.isKeyPressed(Keys.MENU)) && !stop) {
			stop = true;
			timer.schedule(new TimerTask() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					stop = false;
				}
			}, 100);
			if (!showingPause) {
				sacarCartel();
				showingPause = true;
			} else {
				continueGame();
				showingPause = false;
			}

		}
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		stage.draw();

	}

	private void continueGame() {
		if (!cronometer.isRunning()) {
			showingPause = false;
			logica.setPause(false);
			cronometer.resume();
			buttonExit.setVisible(false);
			buttonExit.setZIndex(0);
			buttonContinue.setVisible(false);
			buttonContinue.setZIndex(0);
			cartelExit.setVisible(false);
			cartelExit.setZIndex(0);
		}
	}

	private void sacarCartel() {
		logica.setPause(true);
		cronometer.pause();
		cartelExit.setVisible(true);
		cartelExit.setZIndex(stage.getActors().size);
		buttonExit.setVisible(true);
		buttonExit.setZIndex(stage.getActors().size);
		buttonContinue.setVisible(true);
		buttonContinue.setZIndex(stage.getActors().size);

	}
	public void ganar()
	{
		sacarCartel();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		// stage.getCamera().translate(-stage.getGutterWidth(),-stage.getGutterHeight(),0);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		Gdx.input.setInputProcessor(stage);
		Gdx.input.setCatchBackKey(true);

		logica = new LogicaLevel(game, this);
		stage.addActor(logica);

		int width = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();

		// Cronometer
		GraphicManager.initialize();
		GraphicManager.scaleFont(GraphicManager.getBlackFont());
		LabelStyle headingStyle = new LabelStyle(GraphicManager.getBlackFont(),
				Color.BLACK);
		cronometerLabel = new Label("0:0", headingStyle);
		cronometerLabel.setZIndex(1000);
		cronometerLabel.setX(7 * Gdx.graphics.getWidth() / 8);
		cronometerLabel.setY(8 * Gdx.graphics.getHeight() / 9);
		// cronometerLabel.setFon
		// cronometerLabel.setSize(width/5, height/5);
		// cronometerLabel.setFontScale(3);
		cronometer = new Cronometro(cronometerLabel);
		cronometer.start();
		stage.addActor(cronometerLabel);

		// Open and close fold
		final Image imageLocker = GraphicManager.createBackArrow();
		imageLocker.setDrawable(new TextureRegionDrawable(new TextureRegion(
				new Texture(Gdx.files.internal("img/close.png")))));
		imageLocker.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				SheepFold fold = logica.getFold();
				if (fold.isOpen()) {
					imageLocker.setDrawable(new TextureRegionDrawable(
							new TextureRegion(new Texture(Gdx.files
									.internal("img/close.png")))));
					logica.closeFold();
				} else {
					imageLocker.setDrawable(new TextureRegionDrawable(
							new TextureRegion(new Texture(Gdx.files
									.internal("img/open.png")))));
					logica.openFold();
				}

				return super.touchDown(event, x, y, pointer, button);
			}
		});

		stage.addActor(imageLocker);

		// Pause Menu
		cartelExit = new Image(new Texture(Gdx.files.internal("ui/cartel.png")));
		cartelExit.setBounds(
				Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 3,
				Gdx.graphics.getHeight() / 2 - Gdx.graphics.getWidth() / 4,
				Gdx.graphics.getWidth() / 1.5f, Gdx.graphics.getWidth() / 2);

		buttonContinue = new Image(new Texture(
				Gdx.files.internal("ui/botonContinuar.png")));
		buttonContinue.setBounds(cartelExit.getX() + cartelExit.getWidth()
				/ 1.75f, cartelExit.getY() + cartelExit.getHeight() / 2
				- Gdx.graphics.getWidth() / 10, Gdx.graphics.getWidth() / 5,
				Gdx.graphics.getWidth() / 5);
		buttonContinue.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				// super.clicked(event, x, y);
				continueGame();
			}
		});

		buttonExit = new Image(new Texture(
				Gdx.files.internal("ui/botonExit.png")));
		buttonExit.setBounds(
				cartelExit.getX() + cartelExit.getWidth() / 5,
				cartelExit.getY() + cartelExit.getHeight() / 2
						- Gdx.graphics.getWidth() / 10,
				Gdx.graphics.getWidth() / 5.5f, Gdx.graphics.getWidth() / 6);
		buttonExit.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				super.clicked(event, x, y);
				if (!cronometer.isRunning()) {
					logica.setPause(false);
					((Game) Gdx.app.getApplicationListener())
							.setScreen(lc);
				}
			}
		});
		buttonExit.setVisible(false);
		buttonExit.setZIndex(0);
		buttonContinue.setVisible(false);
		buttonContinue.setZIndex(0);
		cartelExit.setVisible(false);
		cartelExit.setZIndex(0);

		stage.addActor(cartelExit);
		stage.addActor(buttonContinue);
		stage.addActor(buttonExit);

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		Gdx.input.setInputProcessor(null);
	}
}
