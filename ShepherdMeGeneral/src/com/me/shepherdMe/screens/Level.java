package com.me.shepherdMe.screens;

import java.util.Timer;
import java.util.TimerTask;

import utils.LevelManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.me.shepherdMe.ShepherdMe;
import com.me.shepherdMe.actor.Sheep;
import com.me.shepherdMe.actor.SheepFold;
import com.me.shepherdMe.functions.Cronometro;
import com.me.shepherdMe.images.Imagenes;
import com.me.shepherdMe.medals.BronzeMedal;
import com.me.shepherdMe.medals.GoldMedal;
import com.me.shepherdMe.medals.Medal;
import com.me.shepherdMe.medals.SilverMedal;
import com.me.shepherdMe.sound.SoundManager;
import com.me.shepherdMe.table.LogicaLevel;

/**
 * Clase que se encarga de sacar cositas en el nivel (como los carteles de
 * pausa, el cronómetro, etc)
 * 
 * @author jesus
 * 
 */
public class Level implements Screen {

	private ShepherdMe game;
	private Stage stage;
	private LogicaLevel logica;
	private Cronometro cronometer;
	private Image cartelExit, buttonContinue, buttonExit;
	// private TextureAtlas atlas;
	private boolean showingPause = false, stop = false;
	private Timer timer = new Timer();
	private SpriteBatch batch;
	private float width, height;
	private Medal medal = null;
	private boolean End;
	private int gold, silver, bronze;
	private int level;

	public Level(ShepherdMe game, LevelChooser lc, int level) {
		Gdx.app.log("LEVEL", "contruye level");
		this.game = game;
		this.stage = new Stage();
		// SoundManager.loadAudios();
		batch = new SpriteBatch();
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		End = false;
		this.level = level;
		this.cronometer = new Cronometro();

	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public int getSilver() {
		return silver;
	}

	public void setSilver(int silver) {
		this.silver = silver;
	}

	public int getBronze() {
		return bronze;
	}

	public void setBronze(int bronze) {
		this.bronze = bronze;
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

		batch.begin();

		float x = 4 * width / 5, y = 7 * height / 8;
		float swidth = width / 25;

		if (cronometer != null && cronometer.ready) {
			batch.draw(cronometer.min1, x, y, swidth, height / 16);
			x += swidth;
			batch.draw(cronometer.min2, x, y, swidth, height / 16);
			x += swidth;
			batch.draw(cronometer.separator, x, y, swidth / 4, height / 16);
			x += swidth / 4;
			batch.draw(cronometer.sec1, x, y, swidth, height / 16);
			x += swidth;
			batch.draw(cronometer.sec2, x, y, swidth, height / 16);
		}
		if (End && medal != null) {
			batch.draw(medal.getTexture(),
					cartelExit.getX() + cartelExit.getWidth() / 1.75f,
					cartelExit.getY() + cartelExit.getHeight() / 2
							- Gdx.graphics.getWidth() / 10,
					Gdx.graphics.getWidth() / 5, Gdx.graphics.getWidth() / 5);
		}
		batch.end();
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

	public void ganar() {
		stop = true;
		logica.setPause(true);
		cronometer.pause();
		cartelExit.setVisible(true);
		cartelExit.setZIndex(stage.getActors().size);
		buttonExit.setVisible(true);
		buttonExit.setZIndex(stage.getActors().size);
		End = true;

		int time = this.cronometer.getMinutos() * 60
				+ this.cronometer.getSegundos();
		if (time < gold) {
			System.out.println("Gold Medal!!!");
			this.medal = new GoldMedal();
			LevelManager.actualizarNivel(this.level, 3);
		} else {
			if (time < silver) {
				this.medal = new SilverMedal();
				System.out.println("Silver Medal!!!");
				LevelManager.actualizarNivel(this.level, 2);
			} else {
				this.medal = new BronzeMedal();
				System.out.println("Bronze Medal!!!");
				LevelManager.actualizarNivel(this.level, 1);

			}
		}
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

		logica = new LogicaLevel(game, this, level);
		stage.addActor(logica);

		cronometer = new Cronometro();
		// for(Image image : cronometer.getImages()){
		// if(image!=null)
		// stage.addActor(image);
		// }

		cronometer.start();

		// Open and close fold
		final Image imageLocker = new Image(Imagenes.nivelClose);
		int width = Gdx.graphics.getWidth(), height = Gdx.graphics.getHeight();
		imageLocker.setBounds(width / 20, 7 * height / 8, width / 11,
				height / 9);

		/*
		 * imageLocker.setDrawable(new TextureRegionDrawable(new TextureRegion(
		 * new Texture(Gdx.files.internal("img/level/close.png")))));
		 */
		imageLocker.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				SheepFold fold = logica.getFold();
				if (fold.isOpen()) {
					boolean choca = false;
					for (Sheep s : logica.getSheeps()) {
						if (fold.chocaConPuerta(s)) {
							choca = true;
							break;
						}
					}
					if (fold.chocaConPuerta(logica.getDog()))
						choca = true;
					if (!choca) {
						SoundManager.playDoor();
						imageLocker.setDrawable(new TextureRegionDrawable(
								Imagenes.nivelClose));
						logica.closeFold();
					}
				} else {
					SoundManager.playDoor();
					imageLocker.setDrawable(new TextureRegionDrawable(
							Imagenes.nivelOpen));
					logica.openFold();
				}

				return super.touchDown(event, x, y, pointer, button);
			}
		});

		stage.addActor(imageLocker);

		// Pause Menu
		cartelExit = new Image(Imagenes.cartel);
		cartelExit.setBounds(
				Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 3,
				Gdx.graphics.getHeight() / 2 - Gdx.graphics.getWidth() / 4,
				Gdx.graphics.getWidth() / 1.5f, Gdx.graphics.getWidth() / 2);

		buttonContinue = new Image(Imagenes.botonContinuar);
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

		buttonExit = new Image(Imagenes.botonExit);
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
					if (game.chooseLevel == null) {
						game.chooseLevel = new LevelChooser(game);
					}
					logica.pauseOvejas();
					((Game) Gdx.app.getApplicationListener())
							.setScreen(game.chooseLevel);
				}
			}
		});
		buttonExit.setVisible(false);
		buttonExit.setZIndex(0);
		buttonContinue.setVisible(false);
		buttonContinue.setZIndex(0);
		cartelExit.setVisible(false);
		cartelExit.setZIndex(0);

		if (level == 1 && LevelManager.esPrimeraPartida1()) {
			LevelManager.setPrimeraPartida1(false);
			Image tutorialPerro = new Image(new Texture(
					Gdx.files.internal("img/tutorial/moverPerro.png")));
			tutorialPerro.setSize(width, height);
			tutorialPerro.setPosition(0, 0);
			//final Image tp = tutorialPerro;
			tutorialPerro.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					// TODO Auto-generated method stub
					super.clicked(event, x, y);
					event.getListenerActor().remove();
					//tp.setVisible(false);
					//tp.setZIndex(9000);
					Image tutorialCandado = new Image(new Texture(Gdx.files
							.internal("img/tutorial/abrirCandado.png")));
					tutorialCandado.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
					tutorialCandado.setPosition(0, 0);
					//final Image tc = tutorialCandado;
					tutorialCandado.addListener(new ClickListener() {
						@Override
						public void clicked(InputEvent event, float x, float y) {
							// TODO Auto-generated method stub
							super.clicked(event, x, y);
							event.getListenerActor().remove();
							//tc.setVisible(false);
							//tc.setZIndex(9000);
							Image tutorialCerrar = new Image(new Texture(Gdx.files
									.internal("img/tutorial/cerrarRedil.png")));
							tutorialCerrar.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
							tutorialCerrar.setPosition(0, 0);
							//final Image tc = tutorialCerrar;
							tutorialCerrar.addListener(new ClickListener() {
								@Override
								public void clicked(InputEvent event, float x, float y) {
									// TODO Auto-generated method stub
									super.clicked(event, x, y);
									event.getListenerActor().remove();
									//tc.setVisible(false);
									//tc.setZIndex(9000);
								}
							});
							stage.addActor(tutorialCerrar);

						}
					});
					stage.addActor(tutorialCandado);
					

				}
				
			});
			stage.addActor(tutorialPerro);
		}
		
		if (level == 8 && LevelManager.esPrimeraPartida2()) {
			LevelManager.setPrimeraPartida2(false);
			Image tutorialNegra = new Image(new Texture(
					Gdx.files.internal("img/tutorial/ovejasNegras.png")));
			tutorialNegra.setSize(width, height);
			tutorialNegra.setPosition(0, 0);
			//final Image tp = tutorialNegra;
			tutorialNegra.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					// TODO Auto-generated method stub
					super.clicked(event, x, y);
					event.getListenerActor().remove();

					//tp.setVisible(false);
					//tp.setZIndex(9000);
				}
			});
			stage.addActor(tutorialNegra);
		}

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
		batch.dispose();
	}

	public Medal getMedal() {
		// Aquí deberíamos elegir la medalla dependiendo del tiempo que se ha
		// tardado

		return this.medal;
	}
}
