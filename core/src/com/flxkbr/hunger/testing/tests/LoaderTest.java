package com.flxkbr.hunger.testing.tests;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.flxkbr.hunger.load.LoadManager;

public class LoaderTest implements RoyalTest {
	
	LoadManager loader;

	@Override
	public void init() {
		loader = LoadManager.getLoadManager();
	}

	@Override
	public boolean run() {
		boolean loaded = loader.loadTexture("prototype_hex.png");
		if (loaded) {
			Gdx.app.log(LoaderTest.class.toString(), "Texture loaded!");
			try {
				Texture tex = loader.getTexture("prototype_hex");
				if (tex != null) {
					Gdx.app.log(LoaderTest.class.toString(), "Texture successfully fetched");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else
			Gdx.app.error(LoaderTest.class.toString(), "Texture could not be loaded!");
		
		
		
		return loaded;
	}

	@Override
	public void cleanup() {
		loader.dispose();
	}

}
