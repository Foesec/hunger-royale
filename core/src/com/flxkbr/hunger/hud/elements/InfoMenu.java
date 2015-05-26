package com.flxkbr.hunger.hud.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flxkbr.hunger.GlobalConstants;
import com.flxkbr.hunger.hud.elements.ActionMenu.MenuState;
import com.flxkbr.hunger.load.LoadManager;

public class InfoMenu implements HudElement {
	
	private static String texturefile = "textbox.9.png";
	private static float openSpeed = GlobalConstants.HudSettings.OPENSPEED;
	private static float openPositionY = GlobalConstants.VIEWPORTHEIGHT * 0.6f;
	private static float closedPositionY = GlobalConstants.VIEWPORTHEIGHT - 40;
	
	private Sprite box;
	private MenuState state = MenuState.MIN;
	
	public InfoMenu() {
		box = new Sprite(LoadManager.get().getTexture(texturefile));
		box.setSize(GlobalConstants.VIEWPORTWIDTH, GlobalConstants.VIEWPORTHEIGHT * 0.4f);
		box.setPosition(0, closedPositionY);
	}

	@Override
	public void render(SpriteBatch batch) {
		switch(state) {
		case OPENING:
			incrementOpening();
			break;
		case CLOSING:
			incrementClosing();
			break;
		}
		box.draw(batch);
	}
	
	public void toggle() {
		if (state == MenuState.OPEN || state == MenuState.OPENING) {
			state = MenuState.CLOSING;
		} else {
			state = MenuState.OPENING;
		}
	}
	
	private void incrementOpening() {
		box.translateY(-openSpeed * Gdx.graphics.getDeltaTime());
		if (box.getY() <= openPositionY) {
			box.setY(openPositionY);
			state = MenuState.OPEN;
		}
	}
	
	private void incrementClosing() {
		box.translateY(openSpeed * Gdx.graphics.getDeltaTime());
		if (box.getY() >= closedPositionY) {
			box.setY(closedPositionY);
			state = MenuState.MIN;
		}
	}

}
