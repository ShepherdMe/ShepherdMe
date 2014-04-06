package com.me.shepherdMe.screens;

import java.awt.event.KeyEvent;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;

import utils.GraphicManager;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateByAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.esotericsoftware.tablelayout.Cell;
import com.me.shepherdMe.ShepherdMe;

public class MainMenu implements Screen {

	private Stage stage;
	private TextureAtlas atlas;
	private SpriteBatch batchBackground;
	private Texture textureBackground;
	private Sprite backgroundSprite;
	private Image buttonPlay, buttonRecords, buttonExit, buttonContinue,heading;
	private Image sun, sheep;
	private ShepherdMe game;
	private Image volumeImage;
	private boolean volumeOn;
	
	private Image cartelExit;
	
	
	public MainMenu(ShepherdMe game){
		this.game = game;	
	}
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		
		if(Gdx.input.isKeyPressed(Keys.BACK)||Gdx.input.isKeyPressed(Keys.MENU))
		{
			this.quitarEventos();
			sacarCartel();
			//Gdx.app.exit();
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

	@Override
	public void show() {
		// TODO Auto-generated method stub
		GraphicManager.initialize();
		batchBackground = new SpriteBatch();
		textureBackground = new Texture(Gdx.files.internal("img/main/Fondo_main.png"));
		backgroundSprite = new Sprite(textureBackground);
		backgroundSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		stage =  new Stage();
		
		Gdx.input.setInputProcessor(stage);
	
		GraphicManager.scaleFont(GraphicManager.getWhiteFont());
		GraphicManager.scaleFont(GraphicManager.getBlackFont());
		int width = Gdx.graphics.getWidth(), height = Gdx.graphics.getHeight();
		
		sun = new Image(new Texture(Gdx.files.internal("img/main/sol_main.png")));
		sun.setBounds(width-height/1.75f, height-height/1.75f, height/1.1f, height/1.1f);
		
		RotateByAction acc = new RotateByAction();
		
		acc.setAmount(360);
		acc.setDuration(10f);
		
		RepeatAction ra = new RepeatAction();
		ra.setActor(sun);
		ra.setAction(acc);
		ra.setCount(RepeatAction.FOREVER);
		
		sun.addAction(ra);
		sun.setOriginX(sun.getWidth()/2);
        sun.setOriginY(sun.getHeight()/2);
		stage.addActor(sun);
		
		
		sheep = new Image( new Texture(Gdx.files.internal("img/main/sheep.png")));
		sheep.setBounds(2*width/10, -15, height/1.5f, height/1.5f);
		
		
		buttonRecords = new Image(new Texture(Gdx.files.internal("ui/records.png")));
		buttonRecords.setBounds(width-width/5, height/21, width/5, height/4);
		buttonRecords.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				super.clicked(event, x, y);
				((Game) Gdx.app.getApplicationListener()).setScreen(new RecordScreen(game));
			}
		});
		
		buttonPlay = new Image(new Texture(Gdx.files.internal("ui/play.png")));
		buttonPlay.setBounds(width-width/5, height/3.5f, width/5, height/4);
		buttonPlay.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				super.clicked(event, x, y);
				((Game) Gdx.app.getApplicationListener()).setScreen(new LevelChooser(game));
			}
		});
		
		
		//Creating heading
		heading = new Image(new Texture(Gdx.files.internal("ui/tittle.png")));
		heading.setBounds(width/25, 4*height/8, width/1.5f, height/1.5f);
		
		//Volume icon
		volumeImage = new Image(new Texture(Gdx.files.internal("img/volumeOn.png")));
		volumeOn = true;
		volumeImage.setBounds(7*width/8, 7*height/8, width/11, height/11);
		volumeImage.addListener(new InputListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				if(volumeOn){
					volumeImage.setDrawable(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("img/volumeOff.png")))));
					volumeOn = false;
				}
				else{
					volumeImage.setDrawable(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("img/volumeOn.png")))));
					volumeOn = true;
				}
				return true;
			}
		});
			
		stage.addActor(sheep);
		stage.addActor(buttonPlay);
		stage.addActor(buttonRecords);
		stage.addActor(heading);
		stage.addActor(volumeImage);
		
		cartelExit = new Image(new Texture(Gdx.files.internal("ui/cartel.png")));
		cartelExit.setBounds(Gdx.graphics.getWidth()/2-Gdx.graphics.getWidth()/3,Gdx.graphics.getHeight()/2-Gdx.graphics.getWidth()/4,Gdx.graphics.getWidth()/1.5f,Gdx.graphics.getWidth()/2);
		
		buttonContinue = new Image(new Texture(Gdx.files.internal("ui/botonContinuar.png")));
		buttonContinue.setBounds(cartelExit.getX()+cartelExit.getWidth()/1.75f, cartelExit.getY()+cartelExit.getHeight()/2-Gdx.graphics.getWidth()/10, Gdx.graphics.getWidth()/5, Gdx.graphics.getWidth()/5);
		buttonContinue.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				//super.clicked(event, x, y);
				ponerEventos();
				buttonExit.setVisible(false);
				buttonExit.setZIndex(0);
				buttonContinue.setVisible(false);
				buttonContinue.setZIndex(0);
				cartelExit.setVisible(false);
				cartelExit.setZIndex(0);
				//stage.draw();
			}
		});
		
		buttonExit = new Image(new Texture(Gdx.files.internal("ui/botonExit.png")));
		buttonExit.setBounds(cartelExit.getX()+cartelExit.getWidth()/5, cartelExit.getY()+cartelExit.getHeight()/2-Gdx.graphics.getWidth()/10, Gdx.graphics.getWidth()/5.5f, Gdx.graphics.getWidth()/6);
		buttonExit.addListener(new ClickListener(){
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
		
		
		//stage.addActor(exitImage);
	}
	
	private void sacarCartel()
	{
	
		cartelExit.setVisible(true);
		cartelExit.setZIndex(stage.getActors().size);
		buttonExit.setVisible(true);
		buttonExit.setZIndex(stage.getActors().size);
		buttonContinue.setVisible(true);
		buttonContinue.setZIndex(stage.getActors().size);
		
	}

	private void quitarEventos()
	{
		buttonPlay.setTouchable(Touchable.disabled);
		buttonRecords.setTouchable(Touchable.disabled);
	}
	
	private void ponerEventos()
	{
		buttonPlay.setTouchable(Touchable.enabled);
		buttonRecords.setTouchable(Touchable.enabled);
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
//		white.dispose();
//		black.dispose();
//		skin.dispose();
	}

}
