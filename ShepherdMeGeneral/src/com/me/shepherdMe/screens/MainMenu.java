package com.me.shepherdMe.screens;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateByAction;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleByAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.me.shepherdMe.ShepherdMe;
import com.me.shepherdMe.images.Imagenes;
import com.me.shepherdMe.sound.SoundManager;

public class MainMenu implements Screen {

	private Stage stage;
	private TextureAtlas atlas;
	private SpriteBatch batchBackground;
	private Texture textureBackground;
	private Sprite backgroundSprite;
	private Image buttonPlay, buttonRecords, buttonExit, buttonContinue,
			heading;
	private Image sun, sheep;
	private ShepherdMe game;
	private Image volumeEffects, volumeMusic;
	private boolean stop = false,
			showingPause = false;
	private Image cartelExit;
	private Timer timer = new Timer();

	public MainMenu(ShepherdMe game) {
		this.game = game;
		this.game.menu=this;
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub

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
				this.quitarEventos();
				showingPause = true;
			} else {
				this.ponerEventos();
				quitarCartel();
				showingPause = false;
			}

		}

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batchBackground.begin();
		batchBackground.disableBlending();
		backgroundSprite.draw(batchBackground);
		batchBackground.enableBlending();
		batchBackground.end();

		stage.act(delta);
		stage.draw();

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	private void quitarCartel() {
		cartelExit.setVisible(false);
		cartelExit.setZIndex(0);
		buttonExit.setVisible(false);
		buttonExit.setZIndex(0);
		buttonContinue.setVisible(false);
		buttonContinue.setZIndex(0);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		final MainMenu mm = this;
		Gdx.input.setCatchBackKey(true);
		batchBackground = new SpriteBatch();
		textureBackground = new Texture(
				Gdx.files.internal("img/main/Fondo_main.png"));
		backgroundSprite = new Sprite(textureBackground);
		backgroundSprite.setSize(Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		stage = new Stage();

		Gdx.input.setInputProcessor(stage);

		int width = Gdx.graphics.getWidth(), height = Gdx.graphics.getHeight();

		sun = new Image(Imagenes.sol);
		sun.setBounds(width - height / 1.75f, height - height / 1.75f,
				height / 1.1f, height / 1.1f);

		RotateByAction acc = new RotateByAction();

		acc.setAmount(360);
		acc.setDuration(10f);

		RepeatAction ra = new RepeatAction();
		ra.setActor(sun);
		ra.setAction(acc);
		ra.setCount(RepeatAction.FOREVER);

		sun.addAction(ra);
		sun.setOriginX(sun.getWidth() / 2);
		sun.setOriginY(sun.getHeight() / 2);
		stage.addActor(sun);

		int i = new Random().nextInt(7);

		sheep = new Image(Imagenes.getOveja(i));
		
		if (i == 2) {
			sheep.setBounds(2 * width / 10, -15, 1.5f * height / 1.5f,
					height / 1.5f);
		} else if (i == 4) {
			sheep.setBounds(width / 10, -30, 1.5f * height / 1.5f,
					height / 1.5f);
		} else {
			sheep.setBounds(2 * width / 10, -15, height / 1.5f, height / 1.5f);

		}
		
		sheep.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				super.clicked(event, x, y);
				if (game.chooseLevel == null)
				{
					game.chooseLevel = new LevelChooser(game);
				}
				((Game) Gdx.app.getApplicationListener()).setScreen(game.chooseLevel);
			}
		});
		
//		buttonRecords = new Image(new Texture(
//				Gdx.files.internal("img/main/records.png")));
//		buttonRecords.setBounds(width - width / 5, height / 21, width / 5,
//				height / 4);
//		buttonRecords.addListener(new ClickListener() {
//			@Override
//			public void clicked(InputEvent event, float x, float y) {
//				// TODO Auto-generated method stub
//				super.clicked(event, x, y);
//				if (game.recordScreen == null)
//				{
//					game.recordScreen = new RecordScreen(game);
//				}
//				((Game) Gdx.app.getApplicationListener()).setScreen(game.recordScreen);
//			}
//		});
		
		
		buttonPlay = new Image(Imagenes.play);
		buttonPlay.setBounds(width - width / 3.5f, height / 18f, width / 4,	height / 3f);
		buttonPlay.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				super.clicked(event, x, y);
				if (game.chooseLevel == null)
				{
					game.chooseLevel = new LevelChooser(game);
				}
				((Game) Gdx.app.getApplicationListener()).setScreen(game.chooseLevel);
			}
		});
		
		buttonPlay.setOriginX(buttonPlay.getWidth() / 2);
		buttonPlay.setOriginY(buttonPlay.getHeight() / 2);
		buttonPlay.setRotation(-7.5f);
		RotateByAction rba2 = new RotateByAction();

		rba2.setAmount(15);
		rba2.setDuration(0.7f);
		
		RotateByAction rba3 = new RotateByAction();

		rba3.setAmount(-15);
		rba3.setDuration(0.7f);
		
		RepeatAction ra2 = new RepeatAction();
		ra2.setActor(buttonPlay);
		ra2.setAction(new SequenceAction(rba2,rba3));
		ra2.setCount(RepeatAction.FOREVER);

		buttonPlay.addAction(ra2);
		
		// Creating heading
		heading = new Image(Imagenes.tittle);
		heading.setBounds(width / 25, height - height/2.8f, width / 1.5f, height / 3);

		// Volume effects icon
	
		if(SoundManager.getEffectsOn())
		{
			volumeEffects = new Image(Imagenes.sonidoON);
		}
		else
		{
			volumeEffects = new Image(Imagenes.sonidoOFF);
		}
		volumeEffects.setBounds(7.2f * width / 8, 7 * height / 8, height / 11,height / 11);
		volumeEffects.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				if (SoundManager.getEffectsOn()) {
					SoundManager.setEffectsOn(false);
					volumeEffects.setDrawable(new TextureRegionDrawable(Imagenes.sonidoOFF));
				} else {
					SoundManager.setEffectsOn(true);
					volumeEffects.setDrawable(new TextureRegionDrawable(Imagenes.sonidoON));
				}
				return true;
			}
		});

		// Volume music icon
		if(SoundManager.getMusicOn())
		{
			volumeMusic = new Image(Imagenes.musicON);
		}
		else
		{
			volumeMusic = new Image(Imagenes.musicOFF);
		}
		volumeMusic.setBounds(7.2f * width / 8, 6.8f * height / 8 - height / 11, height / 11, height / 11);
		volumeMusic.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				if (SoundManager.getMusicOn()) {
					SoundManager.setMusicOn(false);
					volumeMusic.setDrawable(new TextureRegionDrawable(Imagenes.musicOFF));
				} else {
					SoundManager.setMusicOn(true);
					volumeMusic.setDrawable(new TextureRegionDrawable(Imagenes.musicON));
				}
				return true;
			}
		});

		stage.addActor(sheep);
		stage.addActor(buttonPlay);
//		stage.addActor(buttonRecords);
		stage.addActor(heading);
		stage.addActor(volumeEffects);
		stage.addActor(volumeMusic);

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
				ponerEventos();
				buttonExit.setVisible(false);
				buttonExit.setZIndex(0);
				buttonContinue.setVisible(false);
				buttonContinue.setZIndex(0);
				cartelExit.setVisible(false);
				cartelExit.setZIndex(0);
				// stage.draw();
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
				Gdx.app.exit();
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

	private void sacarCartel() {

		cartelExit.setVisible(true);
		cartelExit.setZIndex(stage.getActors().size);
		buttonExit.setVisible(true);
		buttonExit.setZIndex(stage.getActors().size);
		buttonContinue.setVisible(true);
		buttonContinue.setZIndex(stage.getActors().size);

	}

	private void quitarEventos() {
		buttonPlay.setTouchable(Touchable.disabled);
//		buttonRecords.setTouchable(Touchable.disabled);
	}

	private void ponerEventos() {
		buttonPlay.setTouchable(Touchable.enabled);
//		buttonRecords.setTouchable(Touchable.enabled);
		showingPause = false;
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

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
		stage.dispose();
	}

}
