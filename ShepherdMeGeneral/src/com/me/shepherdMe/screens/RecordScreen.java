package com.me.shepherdMe.screens;

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
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.me.shepherdMe.ShepherdMe;


public class RecordScreen implements Screen{
	
	private Stage stage;
	private SpriteBatch batchBackground;
	private Texture textureBackground;
	private Sprite backgroundSprite;
	private ShepherdMe game;
	private Image backArrow;

	
	public RecordScreen(ShepherdMe game){
		System.out.println("entra en el record");
		this.game = game;
		this.game.recordScreen=this;
	}
	
	@Override
	public void render(float delta) {
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

		
		batchBackground = new SpriteBatch();
		textureBackground = new Texture(
				Gdx.files.internal("img/chooseLevel/fondo.png"));
		backgroundSprite = new Sprite(textureBackground);
		backgroundSprite.setSize(Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());

		stage = new Stage();

		Gdx.input.setInputProcessor(stage);
		
		backArrow = new Image(new Texture(Gdx.files.internal("img/chooseLevel/back.png")));
		int width = Gdx.graphics.getWidth(), height = Gdx.graphics.getHeight();
		backArrow.setBounds(width / 20, 7 * height / 8, width / 11, height / 11);

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
