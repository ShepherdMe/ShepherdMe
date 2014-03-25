package com.me.shepherdMe.screens;

import java.util.ArrayList;
import java.util.List;

import utils.GraphicManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.esotericsoftware.tablelayout.Cell;
import com.me.shepherdMe.ShepherdMe;

public class LevelChooser implements Screen {

	private Stage stage;
	private SpriteBatch batchBackground;
	private Texture textureBackground;
	private Sprite backgroundSprite;
	private ShepherdMe game;
	private Image backArrow;

	public LevelChooser(ShepherdMe game) {
		this.game = game;
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batchBackground.begin();
		batchBackground.disableBlending();
		backgroundSprite.draw(batchBackground);
		batchBackground.enableBlending();
		batchBackground.end();

		stage.act(delta);

		stage.draw();
		// Table.drawDebug(stage);
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
				Gdx.files.internal("img/menuBackground.gif"));
		backgroundSprite = new Sprite(textureBackground, 0, 0,
				Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		stage = new Stage();

		Image imageGreen = new Image(new Texture(
				Gdx.files.internal("img/green.png")));
		Image imageBlue = new Image(new Texture(
				Gdx.files.internal("img/green.png")));
		float imageHeight = Gdx.graphics.getHeight() / 3;
		float imageWidth = Gdx.graphics.getWidth() / 4;
		imageGreen.setSize(imageWidth, imageHeight);
		imageBlue.setSize(imageWidth, imageHeight);


		LabelStyle headingStyle = new LabelStyle(GraphicManager.getBlackFont(),
				Color.BLACK);
		Label heading = new Label("Choose level", headingStyle);
		heading.setPosition(Gdx.graphics.getWidth() / 2 - heading.getWidth()
				- 10, Gdx.graphics.getHeight() - heading.getHeight() * 3);
		heading.setFontScale(3);

		// Creating arrows
		Image arrowRight = new Image(new Texture(
				Gdx.files.internal("img/arrowRight.png")));
		arrowRight.setX(Gdx.graphics.getWidth() * 7 / 8);
		arrowRight.setY(Gdx.graphics.getHeight() * 1 / 7);
		arrowRight.setHeight(imageHeight * 2 + 5);
		arrowRight.setWidth(imageWidth/3);
		Image arrowLeft = new Image(new Texture(
				Gdx.files.internal("img/arrowLeft.png")));
		arrowLeft.setX(Gdx.graphics.getWidth() / 25);
		arrowLeft.setY(Gdx.graphics.getHeight() * 1 / 7);
		arrowLeft.setHeight(imageHeight * 2 + 5);
		arrowLeft.setWidth(imageWidth/3);

		Gdx.input.setInputProcessor(stage);
		List<Image> levelImages = new ArrayList<Image>();
		Image image;
		float initialX = Gdx.graphics.getWidth() * 3 / 16, initialY = Gdx.graphics
				.getHeight() * 2 / 5, deltaX = 0;
		for (int i = 0; i < 6; i++) {
			image = new Image(new Texture(Gdx.files.internal("img/green.png")));
			image.setBounds(initialX + imageWidth * deltaX + 2, initialY,
					imageWidth, imageHeight);
			deltaX++;
			if (i == 0) {
				image.addListener(new InputListener() {
					@Override
					public boolean touchDown(InputEvent event, float x,
							float y, int pointer, int button) {
						System.out.println("Hello!");
						((Game) Gdx.app.getApplicationListener()).setScreen(new Level(game));
						return true;
					}
				});
			}
			if (i == 2) {
				initialY -= imageHeight + 5;
				deltaX = 0;
			}
			levelImages.add(image);
		}

		//backArrow
		//backArrow
				backArrow = GraphicManager.createBackArrow();
				backArrow.addListener(new InputListener(){
					@Override
					public boolean touchDown(InputEvent event, float x, float y,
							int pointer, int button) {
						// TODO Auto-generated method stub
						((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenu(game));
						return true;
					}
				});
		
		for (Image img : levelImages)
			stage.addActor(img);
		stage.addActor(heading);
		stage.addActor(arrowRight);
		stage.addActor(arrowLeft);
		stage.addActor(backArrow);
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
