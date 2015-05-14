package com.flxkbr.hunger.hud.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flxkbr.hunger.connectors.MapScreenMaster;
import com.flxkbr.hunger.input.HudInputHandler;
import com.flxkbr.hunger.load.LoadManager;

public class TextBox extends InputAdapter {
	
	private static final String texturefile = "textbox.9.png";
	
	private Sprite box;
	private String text = "Test test test test";
	
	private boolean filled = false;
	
	private TextBox() throws Exception {
		box = new Sprite(LoadManager.get().getTexture(texturefile));
		//HudInputHandler.registerTextBox(this);
		Gdx.app.log("TextBox", "Self registered");
	}
	
//	public static TextBox createBox() throws Exception {
//		if (HudInputHandler.isFree())
//			return new TextBox();
//		else
//			throw new Exception("HudInputHandler is not free!");
//	}
	
	public void setPosition(float x, float y) {
		box.setPosition(x, y);
	}
	
	public void render(SpriteBatch batch) {
		box.draw(batch);
		MapScreenMaster._getBMF().draw(batch, text, box.getX(), box.getY());
	}
	
//	public void clicked() {
//		HudInputHandler.deregisterSelf(this);
//	}
	
	public void write(String text) {
		this.text = text;
	}
	
	public void clear() {
		this.text = "";
	}
	
}
