package com.me.shepherdMe.screens;

import utils.GraphicManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.me.shepherdMe.ShepherdMe;
import com.me.shepherdMe.functions.Cronometro;
import com.me.shepherdMe.table.LogicaLevel;

public class Level implements Screen {

	private ShepherdMe game;
	private Stage stage;
	private LogicaLevel logica;
	private Label cronometerLabel;
	private Cronometro cronometer;
	private Image resumeImage;
	private Image exitImage, pauseText;

	public Level(ShepherdMe game) {
		Gdx.app.log("LEVEL", "contruye level");
		this.game = game;
		this.stage = new Stage();

	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		stage.draw();

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
		
		logica = new LogicaLevel(game, this);
		stage.addActor(logica);
		
		int width = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();
		
		// Cronometer
		GraphicManager.initialize();
		LabelStyle headingStyle = new LabelStyle(GraphicManager.getBlackFont(),
				Color.BLACK);
		cronometerLabel = new Label("0:0", headingStyle);
		cronometerLabel.setZIndex(1000);
		cronometerLabel.setX(7 * Gdx.graphics.getWidth() / 8);
		cronometerLabel.setY(8 * Gdx.graphics.getHeight() / 9);
		cronometerLabel.setSize(50, 50);
//		cronometerLabel.setFontScale(3);
		cronometer = new Cronometro(cronometerLabel);
		cronometer.start();
		stage.addActor(cronometerLabel);
		
		// Pause text
		pauseText = new Image(new Texture(Gdx.files.internal("img/pauseText.png")));
		pauseText.setBounds(width/4, height/2, width/2, height/5);
		pauseText.setVisible(false);
		pauseText.setZIndex(0);
		stage.addActor(pauseText);
		
		// Resume button
		resumeImage = new Image(new Texture(Gdx.files.internal("img/resume.png")));
		resumeImage.setBounds(pauseText.getX(), height/4, width/8, width/8);
		resumeImage.setVisible(false);
		resumeImage.setZIndex(0);
		resumeImage.addListener(new InputListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				System.out.println("Resume pulsado");
				if(!cronometer.isRunning()){
					logica.setPause(false);
					System.out.println("Reanudando todo");
					cronometer.resume();
					resumeImage.setVisible(false);
					resumeImage.setZIndex(0);
					exitImage.setVisible(false);
					exitImage.setZIndex(0);
					pauseText.setVisible(false);
					pauseText.setZIndex(0);
				}
				return super.touchDown(event, x, y, pointer, button);
			}
		});
		stage.addActor(resumeImage);
		
		// Exit button
		exitImage = new Image(new Texture(Gdx.files.internal("img/exit2.png")));
		exitImage.setBounds(pauseText.getX()+pauseText.getWidth() - width/8, height/4, width/8, width/8);
		exitImage.setVisible(false);
		exitImage.setZIndex(0);
		exitImage.addListener(new InputListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				if(!cronometer.isRunning()){
					logica.setPause(false);
					((Game) Gdx.app.getApplicationListener()).setScreen(new LevelChooser(game));
				}
				return super.touchDown(event, x, y, pointer, button);
			}
		});
		stage.addActor(exitImage);
		
		
		// Pause Image
		Image imagePause = GraphicManager.createBackArrow();
		imagePause.setDrawable(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("img/pause.png")))));
		imagePause.addListener(new InputListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				logica.setPause(true);
				cronometer.pause();
				resumeImage.setVisible(true);
				resumeImage.setZIndex(1000);
				exitImage.setVisible(true);
				exitImage.setZIndex(1000);
				pauseText.setVisible(true);
				pauseText.setZIndex(1000);
				return super.touchDown(event, x, y, pointer, button);
			}
		});
		
		stage.addActor(imagePause);
		

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
