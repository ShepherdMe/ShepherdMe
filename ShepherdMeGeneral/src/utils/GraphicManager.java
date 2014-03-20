package utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class GraphicManager {
	
	private static Skin skin;
	private static TextureAtlas atlas;
	private static BitmapFont white, black;
	private static TextButtonStyle textButtonStyle;
	
//	static{
//		initialize();
//	}
	
	public static void initialize(){
		atlas = new TextureAtlas("ui/button.pack");
		skin = new Skin(atlas);
		white = new BitmapFont(Gdx.files.internal("font/white.fnt"),false);
		black = new BitmapFont(Gdx.files.internal("font/black.fnt"),false);
		textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.getDrawable("negro");
		textButtonStyle.down = skin.getDrawable("blanco");
		textButtonStyle.pressedOffsetX = 1;
		textButtonStyle.pressedOffsetY = - 1;
		textButtonStyle.font = black;
		textButtonStyle.font.scale(1.5f);
	}
	
	private static boolean correct(){
		return !(skin==null || atlas==null || white==null || black==null);
	}
	
	public static TextButton createTextButton(String text){
		TextButton button = null;
		if(correct()){
			button = new TextButton(text, textButtonStyle);
			button.scale(1.5f);
		}
		return button;
	}
	
	public static Label createLabel(String text){
		Label label = null;
		if(correct()){
			label = new Label("hello", new LabelStyle(black, new Color()));
		}
		return label;
	}
	
	public static Skin getSkin(){
		return skin;
	}
	
	public static BitmapFont getWhiteFont(){
		return white;
	}
	
	public static BitmapFont getBlackFont(){
		return black;
	}
}