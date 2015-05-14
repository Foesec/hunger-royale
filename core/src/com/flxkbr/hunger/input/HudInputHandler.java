package com.flxkbr.hunger.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.flxkbr.hunger.hud.HudMaster;
import com.flxkbr.hunger.hud.elements.TextBox;

public class HudInputHandler extends InputAdapter {
	
	private static HudInputHandler handler;

	private HudMaster hudMaster;
	
	private HudInputHandler() {
		hudMaster = HudMaster.get();
	}
	
	public static HudInputHandler get() {
		if (handler == null) {
			handler = new HudInputHandler();
		}
		return handler;
	}

//	public static void registerTextBox(TextBox newText) throws Exception {
//		if (handler.tb == null) {
//			handler.tb = newText;
//		} else {
//			throw new Exception("There is already a TextBox active!");
//		}
//	}
//	
//	public static boolean deregisterSelf(TextBox newText) {
//		if (handler.tb != null && newText.equals(handler.tb)) {
//			handler.tb = null;
//			return true;
//		}
//		return false;
//	}
//	
//	public static boolean isFree() {
//		return (handler.tb == null);
//	}
	
	@Override
	public boolean touchDown (int x, int y, int pointer, int button) {
//		Gdx.app.log("HUDINPUT", "click registered");
//		if (tb == null) {
//			Gdx.app.log("HUDINPUT", "tb is null");
//			return false;
//		}
//		if (pointer == Buttons.LEFT) {
//			Gdx.app.log("HUDINPUT", "tb has been clicked");
//			tb.clicked();
//		}
		return false;
	}
	
	@Override
	public boolean keyDown (int keycode) {
		if (keycode == Keys.E) {
			hudMaster.keyDown(keycode);
			return true;
		}
		return false;
	}
}
