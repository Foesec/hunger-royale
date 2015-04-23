package com.flxkbr.hunger;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.flxkbr.hunger.grfx.RenderMaster;
import com.flxkbr.hunger.load.DisposeHandler;
import com.flxkbr.hunger.load.LoadManager;
import com.flxkbr.hunger.logic.LogicMaster;
import com.flxkbr.hunger.testing.TestHandler;
import com.flxkbr.hunger.testing.tests.LoaderTest;

public class HungerRoyale extends ApplicationAdapter {
	
	RenderMaster renderer;
	LogicMaster updater;
	
	@Override
	public void create () {
		
		TestHandler testy = new TestHandler();
		
		testy.submitTest(new LoaderTest());
		testy.run();
		
		renderer = RenderMaster.getRenderMaster();
		updater = LogicMaster.getLogicMaster();
	}

	@Override
	public void render () {
		updater.update();
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		renderer.render();
	}
	
	@Override
	public void dispose() {
		LoadManager.globalDispose();
		if (DisposeHandler.initFlag())
			DisposeHandler.disposeAll();
	}
}
