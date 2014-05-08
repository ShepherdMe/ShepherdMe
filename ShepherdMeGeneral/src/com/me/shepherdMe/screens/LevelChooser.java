package com.me.shepherdMe.screens;

import java.util.ArrayList;
import java.util.List;

import utils.LevelManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.me.shepherdMe.ShepherdMe;

public class LevelChooser implements Screen {

	private Stage stage;
	private SpriteBatch batchBackground;
	private Texture textureBackground;
	private Sprite backgroundSprite;
	private ShepherdMe game;
	private Image backArrow;
	private List<Image> imagenesNiveles1, imagenesNiveles2,imagenesMedallas1, imagenesMedallas2,imagenesCandados1,imagenesCandados2;
	private Image arrowRight, arrowLeft, dog1, dog2;

	public LevelChooser(ShepherdMe game) {
		this.game = game;
		this.imagenesNiveles1 = new ArrayList<Image>();
		this.imagenesNiveles2 = new ArrayList<Image>();
		this.imagenesMedallas1= new ArrayList<Image>();
		this.imagenesMedallas2 = new ArrayList<Image>();
		this.imagenesCandados1 = new ArrayList<Image>();
		this.imagenesCandados2 = new ArrayList<Image>();

		this.game.chooseLevel=this;
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (Gdx.input.isKeyPressed(Keys.BACK)) 
		{
			if (game.menu == null)
			{
				game.menu = new MainMenu(game);
			}
			((Game) Gdx.app.getApplicationListener()).setScreen(game.menu);
		}

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

	@Override
	public void show() {
		// TODO Auto-generated method stub
		batchBackground = new SpriteBatch();
		textureBackground = new Texture(
				Gdx.files.internal("img/chooseLevel/fondo.png"));
		backgroundSprite = new Sprite(textureBackground);
		backgroundSprite.setSize(Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());

		stage = new Stage();

		Gdx.input.setInputProcessor(stage);

		
		float imageHeight = Gdx.graphics.getHeight() / 3;
		float imageWidth = Gdx.graphics.getWidth() / 4;
		

		// Choose level text
		Image chooseText = new Image(new Texture(
				Gdx.files.internal("img/chooseLevel/chooseLevel.png")));
		chooseText.setSize(Gdx.graphics.getWidth() / 1.5f,
				Gdx.graphics.getHeight() / 4.2f);
		chooseText.setPosition(
				Gdx.graphics.getWidth() / 2 - chooseText.getWidth() / 2,
				Gdx.graphics.getHeight() - chooseText.getHeight() / 0.9f);

		dog1 = new Image(new Texture(
				Gdx.files.internal("img/chooseLevel/dog1.png")));
		dog1.setSize(Gdx.graphics.getWidth() / 4.5f,
				Gdx.graphics.getWidth() / 4.5f);

		dog2 = new Image(new Texture(
				Gdx.files.internal("img/chooseLevel/dog2.png")));
		dog2.setSize(Gdx.graphics.getWidth() / 4.5f,
				Gdx.graphics.getWidth() / 4.5f);
		dog2.setPosition(Gdx.graphics.getWidth(), -dog2.getHeight() * 2);// Desplazada
																			// dado
																			// que
																			// este
																			// al
																			// principio
																			// esta
																			// mas
																			// abajo
																			// para
																			// la
																			// animacion

		arrowRight = new Image(new Texture(
				Gdx.files.internal("img/chooseLevel/arrowRight.png")));
		arrowRight.setX(Gdx.graphics.getWidth() * 7 / 8);
		arrowRight.setY(Gdx.graphics.getHeight() * 1 / 7);
		arrowRight.setHeight(imageHeight * 2 + 5);
		arrowRight.setWidth(imageWidth / 3);

		arrowRight.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {

				for (Image imagen : imagenesNiveles1) {

					MoveByAction mba = new MoveByAction();
					mba.setAmountX(-Gdx.graphics.getWidth());
					mba.setDuration(0.75f);

					AlphaAction apA = new AlphaAction();
					apA.setAlpha(0);
					apA.setDuration(0.75f);

					ParallelAction pa = new ParallelAction(mba, apA);
					imagen.addAction(pa);

				}
				
				for (Image imagen : imagenesMedallas1) {

					MoveByAction mba = new MoveByAction();
					mba.setAmountX(-Gdx.graphics.getWidth());
					mba.setDuration(0.75f);

					AlphaAction apA = new AlphaAction();
					apA.setAlpha(0);
					apA.setDuration(0.75f);

					ParallelAction pa = new ParallelAction(mba, apA);
					imagen.addAction(pa);

				}
				
				for (Image imagen : imagenesCandados1) {

					MoveByAction mba = new MoveByAction();
					mba.setAmountX(-Gdx.graphics.getWidth());
					mba.setDuration(0.75f);

					AlphaAction apA = new AlphaAction();
					apA.setAlpha(0);
					apA.setDuration(0.75f);

					ParallelAction pa = new ParallelAction(mba, apA);
					imagen.addAction(pa);

				}

				for (Image imagen : imagenesNiveles2) {

					MoveByAction mba = new MoveByAction();
					mba.setAmountX(-Gdx.graphics.getWidth());
					mba.setDuration(0.75f);

					AlphaAction apA = new AlphaAction();
					apA.setAlpha(1);
					apA.setDuration(0.75f);

					ParallelAction pa = new ParallelAction(mba, apA);
					imagen.addAction(pa);

				}
				
				
				for (Image imagen : imagenesMedallas2) {

					MoveByAction mba = new MoveByAction();
					mba.setAmountX(-Gdx.graphics.getWidth());
					mba.setDuration(0.75f);

					AlphaAction apA = new AlphaAction();
					apA.setAlpha(1);
					apA.setDuration(0.75f);

					ParallelAction pa = new ParallelAction(mba, apA);
					imagen.addAction(pa);

				}
				
				for (Image imagen : imagenesCandados2) {

					MoveByAction mba = new MoveByAction();
					mba.setAmountX(-Gdx.graphics.getWidth());
					mba.setDuration(0.75f);

					AlphaAction apA = new AlphaAction();
					apA.setAlpha(1);
					apA.setDuration(0.75f);

					ParallelAction pa = new ParallelAction(mba, apA);
					imagen.addAction(pa);

				}
				
				arrowLeft.setVisible(true);
				arrowRight.setVisible(false);

				MoveByAction mba = new MoveByAction();
				mba.setAmount(-dog1.getWidth(), -dog1.getHeight() * 2);
				mba.setDuration(1f);
				dog1.addAction(mba);

				MoveByAction mba2 = new MoveByAction();
				mba2.setAmount(-dog2.getWidth(), dog2.getHeight() * 2);
				mba2.setDuration(1f);
				dog2.addAction(mba2);

				return true;
			}
		});

		arrowLeft = new Image(new Texture(
				Gdx.files.internal("img/chooseLevel/arrowLeft.png")));
		arrowLeft.setX(Gdx.graphics.getWidth() / 25);
		arrowLeft.setY(Gdx.graphics.getHeight() * 1 / 7);
		arrowLeft.setHeight(imageHeight * 2 + 5);
		arrowLeft.setWidth(imageWidth / 3);
		arrowLeft.setVisible(false);
		arrowLeft.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {

				for (Image imagen : imagenesNiveles2) {

					MoveByAction mba = new MoveByAction();
					mba.setAmountX(Gdx.graphics.getWidth());
					mba.setDuration(0.75f);

					AlphaAction apA = new AlphaAction();
					apA.setAlpha(0);
					apA.setDuration(0.75f);

					ParallelAction pa = new ParallelAction(mba, apA);
					imagen.addAction(pa);

				}
				
				for (Image imagen : imagenesMedallas2) {

					MoveByAction mba = new MoveByAction();
					mba.setAmountX(Gdx.graphics.getWidth());
					mba.setDuration(0.75f);

					AlphaAction apA = new AlphaAction();
					apA.setAlpha(0);
					apA.setDuration(0.75f);

					ParallelAction pa = new ParallelAction(mba, apA);
					imagen.addAction(pa);

				}
				
				for (Image imagen : imagenesCandados2) {

					MoveByAction mba = new MoveByAction();
					mba.setAmountX(Gdx.graphics.getWidth());
					mba.setDuration(0.75f);

					AlphaAction apA = new AlphaAction();
					apA.setAlpha(0);
					apA.setDuration(0.75f);

					ParallelAction pa = new ParallelAction(mba, apA);
					imagen.addAction(pa);

				}
				

				for (Image imagen : imagenesNiveles1) {

					MoveByAction mba = new MoveByAction();
					mba.setAmountX(Gdx.graphics.getWidth());
					mba.setDuration(0.75f);

					AlphaAction apA = new AlphaAction();
					apA.setAlpha(1);
					apA.setDuration(0.75f);

					ParallelAction pa = new ParallelAction(mba, apA);
					imagen.addAction(pa);

				}
				
				for (Image imagen : imagenesMedallas1) {

					MoveByAction mba = new MoveByAction();
					mba.setAmountX(Gdx.graphics.getWidth());
					mba.setDuration(0.75f);

					AlphaAction apA = new AlphaAction();
					apA.setAlpha(1);
					apA.setDuration(0.75f);

					ParallelAction pa = new ParallelAction(mba, apA);
					imagen.addAction(pa);

				}
				
				for (Image imagen : imagenesCandados1) {

					MoveByAction mba = new MoveByAction();
					mba.setAmountX(Gdx.graphics.getWidth());
					mba.setDuration(0.75f);

					AlphaAction apA = new AlphaAction();
					apA.setAlpha(1);
					apA.setDuration(0.75f);

					ParallelAction pa = new ParallelAction(mba, apA);
					imagen.addAction(pa);

				}
				

				arrowLeft.setVisible(false);
				arrowRight.setVisible(true);

				MoveByAction mba = new MoveByAction();
				mba.setAmount(dog1.getWidth(), dog1.getHeight() * 2);
				mba.setDuration(1f);
				dog1.addAction(mba);

				MoveByAction mba2 = new MoveByAction();
				mba2.setAmount(dog2.getWidth(), -dog2.getHeight() * 2);
				mba2.setDuration(1f);
				dog2.addAction(mba2);

				return true;
			}
		});

		this.crearNiveles();

		backArrow = new Image(new Texture(
				Gdx.files.internal("img/chooseLevel/back.png")));
		int width = Gdx.graphics.getWidth(), height = Gdx.graphics.getHeight();
		backArrow
				.setBounds(width / 20, 7 * height / 8, width / 11, height / 11);

		backArrow.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				if (game.menu == null)
				{
					game.menu = new MainMenu(game);
				}
				((Game) Gdx.app.getApplicationListener()).setScreen(game.menu);
				return true;
			}
		});

		for (Image img : imagenesNiveles1)
			stage.addActor(img);
		for (Image img : imagenesNiveles2)
			stage.addActor(img);
		for (Image img : imagenesMedallas1)
			stage.addActor(img);
		for (Image img : imagenesMedallas2)
			stage.addActor(img);
		for (Image img : imagenesCandados1)
			stage.addActor(img);
		for (Image img : imagenesCandados2)
			stage.addActor(img);
		stage.addActor(chooseText);
		stage.addActor(arrowRight);
		stage.addActor(arrowLeft);
		stage.addActor(backArrow);
		stage.addActor(dog1);
		stage.addActor(dog2);
	}

	public void crearNiveles() {
		Image image;
		float imageHeight = Gdx.graphics.getHeight() / 3;
		float imageWidth = Gdx.graphics.getWidth() / 5;
		float initialX = Gdx.graphics.getWidth() * 3 / 16, initialY = Gdx.graphics
				.getHeight() * 2 / 5, deltaX = 0;
		final LevelChooser levelChooser = this;
		
		for (int i = 0; i < 6; i++) 
		{
			image = new Image(new Texture(Gdx.files.internal("img/chooseLevel/cartel.png")));
			this.imagenesNiveles1.add(image);
			image.setBounds(initialX + imageWidth * deltaX + 2, initialY,imageWidth, imageHeight);
			deltaX++;
			if (i == 0) 
			{
				image.addListener(new InputListener() 
				{
					@Override
					public boolean touchDown(InputEvent event, float x,
							float y, int pointer, int button) {
						((Game) Gdx.app.getApplicationListener())
								.setScreen(new Level(game, levelChooser, 1));
						return true;
					}
				});
			}
			if (i == 2) 
			{
				initialY -= imageHeight + 5;
				deltaX = 0;
			}
			
			LevelManager.comprobarNivel(i,image,imagenesMedallas1,imagenesCandados1);
			
		}

		initialX = Gdx.graphics.getWidth() * 3 / 16;
		initialY = Gdx.graphics.getHeight() * 2 / 5;
		deltaX = 0;
		
		for (int i = 0; i < 6; i++) 
		{
			image = new Image(new Texture(Gdx.files.internal("img/chooseLevel/cartel.png")));
			this.imagenesNiveles2.add(image);
			image.setBounds(Gdx.graphics.getWidth() + initialX + imageWidth* deltaX + 2, initialY, imageWidth, imageHeight);
			deltaX++;
			if (i == 0) 
			{
				image.addListener(new InputListener() {
					@Override
					public boolean touchDown(InputEvent event, float x,
							float y, int pointer, int button) {
						((Game) Gdx.app.getApplicationListener())
								.setScreen(new Level(game, levelChooser, 1));
						return true;
					}
				});
			}
			if (i == 2) 
			{
				initialY -= imageHeight + 5;
				deltaX = 0;
			}
		}

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

	}

}
