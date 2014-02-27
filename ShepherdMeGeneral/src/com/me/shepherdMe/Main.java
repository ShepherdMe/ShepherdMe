package com.me.shepherdMe;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Main implements ApplicationListener {
	private SpriteBatch batch;
    OrthographicCamera camera;
    private Texture oveja;
    Rectangle ovejita;
    
    class TouchInfo {
        public float touchX = 0;
        public float touchY = 0;
        public boolean touched = false;
    }
    private Map<Integer,TouchInfo> touches = new HashMap<Integer,TouchInfo>();
	@Override
	public void create() {		
		batch = new SpriteBatch();    
        oveja = new Texture(Gdx.files.internal("data\\Rapidash.png"));
        
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        
        ovejita = new Rectangle();
        ovejita.x = 800 / 2 - 64 / 2;
        ovejita.y = 20;
        ovejita.width = 64;
        ovejita.height = 64;
        
	}

	@Override
	public void dispose() {
		batch.dispose();
		oveja.dispose();
	}

	@Override
	public void render() {		
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		camera.update();
		

		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(oveja, ovejita.x, ovejita.y);
		batch.end();
		
		 for(int i=0; i<10;i++)
		 {
	         ovejita.y += 2 * Gdx.graphics.getDeltaTime();
	         ovejita.x += 1 * Gdx.graphics.getDeltaTime();
	         /*if(ovejita.y + 64 < 0) iter.remove();
	         if(raindrop.overlaps(bucket)) {
	            dropSound.play();
	            iter.remove();
	         }*/
	      }
		
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
	
}
