package com.flxkbr.hunger.hud.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flxkbr.hunger.GlobalConstants;
import com.flxkbr.hunger.load.LoadManager;

public class ActionMenu implements HudElement {
	
	private Sprite bg;
	private float latchOffset = 27f;
	//private float xmargin = 8f;
	//private float ymargin = 1f;
	private float scrollspeed = 2500f;
	
	private float positionX;
	private float closedPositionY;

	private float openPositionY = 0f;
	
	private ActionMenuState state;
	
	public enum ActionMenuState {
		MIN,
		OPENING,
		CLOSING,
		OPEN;
	}
	
	public ActionMenu() {
		try {
			bg = new Sprite(LoadManager.get().getTexture("amenu.png"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		positionX = GlobalConstants.VIEWPORTWIDTH - bg.getWidth();
		closedPositionY = -bg.getHeight()+latchOffset;
		bg.flip(false, true);
		bg.setPosition(positionX, closedPositionY);
		state = ActionMenuState.MIN;
		
		Gdx.app.log("ActionMenu", "bg sprite initialized at " + bg.getOriginX() + ", " + bg.getOriginY());
	}
	
	public void toggle() {
		if (state == ActionMenuState.OPEN || state == ActionMenuState.OPENING) {
			state = ActionMenuState.CLOSING;
		} else {
			state = ActionMenuState.OPENING;
		}
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
		bg.draw(batch);
	}
	
	private void incrementOpening() {
		bg.translateY(scrollspeed * Gdx.graphics.getDeltaTime());
		if (bg.getY() >= openPositionY) {
			bg.setY(openPositionY);
			state = ActionMenuState.OPEN;
		}
	}
	
	private void incrementClosing() {
		bg.translateY(-scrollspeed * Gdx.graphics.getDeltaTime());
		if (bg.getY() <= closedPositionY) {
			bg.setY(closedPositionY);
			state = ActionMenuState.MIN;
		}
	}

}
