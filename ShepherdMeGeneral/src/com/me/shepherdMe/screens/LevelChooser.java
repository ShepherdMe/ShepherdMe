package com.me.shepherdMe.screens;

import java.util.ArrayList;
import java.util.List;

import utils.GraphicManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class LevelChooser implements Screen {

	private Stage stage;
	private SpriteBatch batchBackground;
	private Texture textureBackground;
	private Sprite backgroundSprite;
	private List<Texture> levelImages;

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub

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

		levelImages = new ArrayList<Texture>();
		for (int i = 0; i < 6; i++) {
			if (i % 2 == 0)
				levelImages
						.add(new Texture(Gdx.files.internal("img/green.png")));
			else
				levelImages
						.add(new Texture(Gdx.files.internal("img/blue.jpg")));
		}

		Table table = new Table(GraphicManager.getSkin());
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		LabelStyle headingStyle = new LabelStyle(GraphicManager.getWhiteFont(), Color.WHITE);
		Label heading = new Label("Choose level", headingStyle);
		heading.setFontScale(3);
		
		// putting stuff together
		table.add(heading).colspan(2);
		table.getCell(heading).spaceBottom(300);
		table.row();
		for(Texture image : levelImages){
//			table.add(image);
		}
		table.debug();

		stage.addActor(table);
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
