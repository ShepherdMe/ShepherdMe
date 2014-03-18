package com.me.shepherdMe.screens;

import utils.GraphicManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.me.shepherdMe.ShepherdMe;


public class RecordScreen implements Screen{
	
	private Stage stage;
	private TextureAtlas atlas;
	private SpriteBatch batchBackground;
	private Texture textureBackground;
	private Sprite backgroundSprite;
	private Table table;
	private Label heading;
	private BitmapFont white, black;
	private ShepherdMe game;
	
	public RecordScreen(ShepherdMe game){
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
		batchBackground = new SpriteBatch();
		textureBackground = new Texture(Gdx.files.internal("img/menuBackground.gif"));
		backgroundSprite = new Sprite(textureBackground, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		stage =  new Stage();
		
		Gdx.input.setInputProcessor(stage);
				
		atlas = new TextureAtlas("ui/button.pack");
		
		Skin skin = new Skin(atlas);
		
		white = new BitmapFont(Gdx.files.internal("font/white.fnt"),false);
		black = new BitmapFont(Gdx.files.internal("font/black.fnt"),false);
	
		table = new Table(skin);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		
//		Label label1 = new Label("hello", skin);
		
//		Label label1 = new Label("hello", new LabelStyle(black, new Color()));
		Label label1 = GraphicManager.createLabel("Statistics");
		
//		//Creating heading
		LabelStyle headingStyle = new LabelStyle(white, Color.WHITE);
		heading = new Label("Level 1", headingStyle);
		heading.setFontScale(3);
//		
//		//putting stuff together
		table.add(heading).colspan(3);
		table.getCell(heading);
		table.row();
		table.add(label1);
//		table.add(tiempo).spaceLeft(200);
//		table.add(fecha).spaceLeft(200);
//		table.debug();
//		
		stage.addActor(table);
//		
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
		white.dispose();
		black.dispose();
//		skin.dispose();
	}

	
	
}
