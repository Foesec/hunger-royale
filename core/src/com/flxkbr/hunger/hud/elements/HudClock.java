package com.flxkbr.hunger.hud.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.flxkbr.hunger.GlobalConstants;
import com.flxkbr.hunger.gmobj.Clock;
import com.flxkbr.hunger.grfx.RenderMaster;

public class HudClock implements HudElement {
	
	private Clock clock;
	private BitmapFont font = RenderMaster.getMasterFont();
	private float xpos = GlobalConstants.VIEWPORTWIDTH - 500;
	private float ypos = 10;
	
	public HudClock(Clock c) {
		clock = c;
	}

	@Override
	public void render(SpriteBatch batch) {
		font.draw(batch, "hour "+clock.getTime()+" on day " + clock.getDays(), xpos, ypos, 0, Align.right, false);
	}

}
