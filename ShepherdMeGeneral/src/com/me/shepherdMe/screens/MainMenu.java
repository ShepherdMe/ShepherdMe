package com.me.shepherdMe.screens;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import utils.GraphicManager;
import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateByAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.me.shepherdMe.ShepherdMe;
import com.me.shepherdMe.tween.SpriteAccessor;

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
	private boolean volumeEffectsOn, volumeMusicOn, stop = false,
			showingPause = false;
	private TweenManager tweenManager;
	private Image cartelExit;
	private LevelChooser lc = null;
	private RecordScreen rs = null;
	private Timer timer = new Timer();

	public MainMenu(ShepherdMe game) {
		this.game = game;
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
		GraphicManager.initialize();
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

		GraphicManager.scaleFont(GraphicManager.getWhiteFont());
		GraphicManager.scaleFont(GraphicManager.getBlackFont());
		int width = Gdx.graphics.getWidth(), height = Gdx.graphics.getHeight();

		sun = new Image(
				new Texture(Gdx.files.internal("img/main/sol_main.png")));
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

		int i = new Random().nextInt(8);

		sheep = new Image(new Texture(Gdx.files.internal("img/main/sheep_" + i
				+ ".png")));
		/*
		 * if(i==7 || i ==1 || i==0 || i==3 || i==5 ||i==6) {
		 * sheep.setBounds(2*width/10, -15, height/1.5f, height/1.5f); }
		 */
		if (i == 2) {
			sheep.setBounds(2 * width / 10, -15, 1.5f * height / 1.5f,
					height / 1.5f);
		} else if (i == 4) {
			sheep.setBounds(width / 10, -30, 1.5f * height / 1.5f,
					height / 1.5f);
		} else {
			sheep.setBounds(2 * width / 10, -15, height / 1.5f, height / 1.5f);

		}
		// sheep.setBounds(2*width/10, -15, height/1.5f, height/1.5f);

		/*
		 * AlphaAction alphaAc = new AlphaAction();
		 * 
		 * 
		 * RepeatAction ra2 = new RepeatAction(); ra2.setActor(sheep);
		 * ra2.setAction(alphaAc); ra2.setCount(RepeatAction.FOREVER);
		 */

		/*
		 * SpriteBatch batch = new SpriteBatch(); tweenManager= new
		 * TweenManager(); Tween.registerAccessor(Sprite.class, new
		 * SpriteAccessor());
		 * 
		 * Texture splashTexture= new
		 * Texture(Gdx.files.internal("img/main/sheep.png")); sheep= new
		 * Sprite(splashTexture); sheep.setBounds(2*width/10, -15, height/1.5f,
		 * height/1.5f);
		 * 
		 * /*Sprite splash= new Sprite(new
		 * Texture(Gdx.files.internal("img/main/sheep.png")));
		 * splash.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		 */

		/*
		 * Tween.set(sheep, SpriteAccessor.ALPHA).target(0).start(tweenManager);
		 * Tween.to(sheep, SpriteAccessor.ALPHA, 2).target(1).repeatYoyo(1,
		 * 2).setCallback(new TweenCallback() {
		 * 
		 * @Override public void onEvent(int type, BaseTween<?> source) {
		 * 
		 * } }).start(tweenManager);
		 */

		buttonRecords = new Image(new Texture(
				Gdx.files.internal("ui/records.png")));
		buttonRecords.setBounds(width - width / 5, height / 21, width / 5,
				height / 4);
		buttonRecords.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				super.clicked(event, x, y);
				if (rs == null)
					rs = new RecordScreen(game, mm);
				((Game) Gdx.app.getApplicationListener()).setScreen(rs);
			}
		});
		buttonPlay = new Image(new Texture(Gdx.files.internal("ui/play.png")));
		buttonPlay.setBounds(width - width / 5, height / 3.5f, width / 5,
				height / 4);
		buttonPlay.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				super.clicked(event, x, y);
				if (lc == null)
					lc = new LevelChooser(game, mm);
				((Game) Gdx.app.getApplicationListener()).setScreen(lc);
			}
		});

		// Creating heading
		heading = new Image(new Texture(Gdx.files.internal("ui/tittle.png")));
		heading.setBounds(width / 25, 4 * height / 8, width / 1.5f,
				height / 1.5f);

		// Volume effects icon
		volumeEffects = new Image(new Texture(
				Gdx.files.internal("img/main/sonido.png")));
		volumeEffectsOn = true;
		volumeEffects.setBounds(7.2f * width / 8, 7 * height / 8, height / 11,
				height / 11);
		volumeEffects.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				if (volumeEffectsOn) {
					volumeEffects.setDrawable(new TextureRegionDrawable(
							new TextureRegion(new Texture(Gdx.files
									.internal("img/main/sonido_2.png")))));
					volumeEffectsOn = false;
				} else {
					volumeEffects.setDrawable(new TextureRegionDrawable(
							new TextureRegion(new Texture(Gdx.files
									.internal("img/main/sonido.png")))));
					volumeEffectsOn = true;
				}
				return true;
			}
		});

		// Volume music icon
		volumeMusic = new Image(new Texture(
				Gdx.files.internal("img/main/music.png")));
		volumeMusicOn = true;
		volumeMusic.setBounds(7.2f * width / 8,
				6.8f * height / 8 - height / 11, height / 11, height / 11);
		volumeMusic.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				if (volumeMusicOn) {
					volumeMusic.setDrawable(new TextureRegionDrawable(
							new TextureRegion(new Texture(Gdx.files
									.internal("img/main/music_2.png")))));
					volumeMusicOn = false;
				} else {
					volumeMusic.setDrawable(new TextureRegionDrawable(
							new TextureRegion(new Texture(Gdx.files
									.internal("img/main/music.png")))));
					volumeMusicOn = true;
				}
				return true;
			}
		});

		stage.addActor(sheep);
		stage.addActor(buttonPlay);
		stage.addActor(buttonRecords);
		stage.addActor(heading);
		stage.addActor(volumeEffects);
		stage.addActor(volumeMusic);

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
		buttonRecords.setTouchable(Touchable.disabled);
	}

	private void ponerEventos() {
		buttonPlay.setTouchable(Touchable.enabled);
		buttonRecords.setTouchable(Touchable.enabled);
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
		atlas.dispose();
		// white.dispose();
		// black.dispose();
		// skin.dispose();
	}

}
