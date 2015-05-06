package com.flxkbr.hunger.grfx;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.flxkbr.hunger.load.LoadManager;

public class PlayerRenderer {
	
	private static PlayerRenderer instance;
	
	private Sprite spr;
	
	private PlayerRenderer() throws Exception {
		spr = new Sprite(LoadManager.get().getTexture("prototype_gons.png"), 0, 0, 64, 64);
	}
	
	public static PlayerRenderer get() throws Exception {
		if (instance == null) {
			instance = new PlayerRenderer();
		}
		return instance;
	}
}
