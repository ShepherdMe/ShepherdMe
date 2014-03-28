package utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class GraphicManager {

	private static Skin skin;
	private static TextureAtlas atlas = null;
	private static BitmapFont white, black;
	private static TextButtonStyle textButtonStyle;

	public static void initialize() {
		atlas = new TextureAtlas("ui/button.pack");
		skin = new Skin(atlas);
		white = new BitmapFont(Gdx.files.internal("font/white.fnt"), false);
		black = new BitmapFont(Gdx.files.internal("font/black.fnt"), false);
		textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.getDrawable("negro");
		textButtonStyle.down = skin.getDrawable("blanco");
		textButtonStyle.pressedOffsetX = 1;
		textButtonStyle.pressedOffsetY = -1;
		textButtonStyle.font = black;
		textButtonStyle.font.scale(1.5f);

	}

	public static Image createBackArrow() {
		Image backArrow = new Image(new Texture(
				Gdx.files.internal("img/backArrow.png")));
		int width = Gdx.graphics.getWidth(), height = Gdx.graphics.getHeight();
		backArrow
				.setBounds(width / 20, 7 * height / 8, width / 11, height / 11);
		return backArrow;
	}

	public static Image createExitButton() {
		Image backArrow = new Image(new Texture(
				Gdx.files.internal("img/exit.png")));
		int width = Gdx.graphics.getWidth(), height = Gdx.graphics.getHeight();
		backArrow
				.setBounds(width / 20, 7 * height / 8, width / 11, height / 11);
		return backArrow;
	}

	private static boolean correct() {
		return !(skin == null || atlas == null || white == null || black == null);
	}

	public static TextButton createTextButton(String text) {
		TextButton button = null;
		if (correct()) {
			button = new TextButton(text, textButtonStyle);
			// button.scale(1.5f);
		}
		return button;
	}

	public static Label createLabel(String text) {
		Label label = null;
		if (correct()) {
			System.out.println("HEllo!!!!!");
			label = new Label(text, new LabelStyle(black, new Color()));
		}
		return label;
	}

	public static Skin getSkin() {
		return skin;
	}

	public static BitmapFont getWhiteFont() {
		return white;
	}

	public static BitmapFont getBlackFont() {
		return black;
	}
}
