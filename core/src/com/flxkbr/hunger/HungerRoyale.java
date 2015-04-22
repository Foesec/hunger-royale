package com.flxkbr.hunger;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flxkbr.hunger.testing.TestHandler;
import com.flxkbr.hunger.testing.tests.LogHandlerTest;
import com.flxkbr.hunger.testing.tests.MapTesting;

public class HungerRoyale extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		
//		TestHandler testy = new TestHandler();
//		
//		testy.submitTest(new MapTesting());
//		testy.submitTest(new LogHandlerTest());
//		testy.run();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
}
