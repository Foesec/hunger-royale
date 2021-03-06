package com.flxkbr.hunger;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.flxkbr.hunger.connectors.Initializer;
import com.flxkbr.hunger.grfx.RenderMaster;
import com.flxkbr.hunger.load.DisposeHandler;
import com.flxkbr.hunger.load.LoadManager;
import com.flxkbr.hunger.logic.LogicMaster;

public class HungerRoyale extends ApplicationAdapter {
	
	RenderMaster renderer;
	LogicMaster updater;
	DisposeHandler cleaner;
	LoadManager loader;
	
	Texture tex;
	
	@Override
	public void create () {
		Initializer i;
		try {
			i = Initializer.get().initialize();
			renderer = i.getRenderer();
			updater = i.getUpdater();
			cleaner = i.getCleaner();
			loader = i.getLoader();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
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
		cleaner.disposeAll();
	}
}
