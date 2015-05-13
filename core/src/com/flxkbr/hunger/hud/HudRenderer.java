package com.flxkbr.hunger.hud;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.flxkbr.hunger.hud.elements.HudElement;
import com.flxkbr.hunger.load.HRDisposable;

public class HudRenderer extends HRDisposable {
	
	private static HudRenderer instance;
	
	private BitmapFont font;
	
	
	public static HudRenderer get() {
		if (instance == null) {
			instance = new HudRenderer();
		}
		return instance;
	}
	
	private HudRenderer() {
		super();
		font = new BitmapFont();
		
	}
	
	public static BitmapFont getFont() {
		return instance.font;
	}
	
	public void render(SpriteBatch batch) {
		for (HudElement e : HudMaster.getElements()) {
			e.render(batch);
		}
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		font.dispose();
	}

}
