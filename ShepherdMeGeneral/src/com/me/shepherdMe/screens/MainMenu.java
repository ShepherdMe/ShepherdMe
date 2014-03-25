package com.me.shepherdMe.screens;

import utils.GraphicManager;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
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
	private TextButton buttonRecords, buttonPlay;
	private Label heading;
	private ShepherdMe game;
	private Image volumeImage, exitImage;
	private Sprite fondo;
	private boolean volumeOn;
	
	public MainMenu(ShepherdMe game){
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
		textureBackground = new Texture(Gdx.files.internal("img/menuBackground.gif"));
		backgroundSprite = new Sprite(textureBackground);
		backgroundSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		stage =  new Stage();
		
		Gdx.input.setInputProcessor(stage);
	
		
		int width = Gdx.graphics.getWidth(), height = Gdx.graphics.getHeight();
		buttonRecords = GraphicManager.createTextButton("Records");
		buttonRecords.setBounds(8*width/14, height/8, width/3, height/4);
		
		System.out.println(buttonRecords);
		buttonRecords.pad(20);
		buttonRecords.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				super.clicked(event, x, y);
				((Game) Gdx.app.getApplicationListener()).setScreen(new RecordScreen(game));
			}
		});
		
		buttonPlay = GraphicManager.createTextButton("Play");
		buttonPlay.pad(20);
		
		buttonPlay.setBounds(width/8, height/8, width/3, height/4);
		buttonPlay.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				super.clicked(event, x, y);
				((Game) Gdx.app.getApplicationListener()).setScreen(new LevelChooser(game));
			}
		});
		
		
		//Creating heading
		LabelStyle headingStyle = new LabelStyle(GraphicManager.getWhiteFont(), Color.WHITE);
		heading = new Label("Shepherd Me!", headingStyle);
		heading.setFontScale(3);
		heading.setBounds(5*width/16, 5*height/8, width/2, height/4);
		
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
		
		
		//exit icon
		exitImage = GraphicManager.createExitButton();
		exitImage.addListener(new InputListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				Gdx.app.exit();
				return true;
			}
		});
		
		
		stage.addActor(buttonPlay);
		stage.addActor(buttonRecords);
		stage.addActor(heading);
		stage.addActor(volumeImage);
		stage.addActor(exitImage);
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
