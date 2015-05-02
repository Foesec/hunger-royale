package com.flxkbr.hunger.grfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flxkbr.hunger.GlobalConstants;
import com.flxkbr.hunger.geom.HexMap;

public class MapRenderer implements IRenderable {
	
	public static final int PRIO = 1;
	
	private float viewportWidth = GlobalConstants.VIEWPORTWIDTH;
	
	private HexMap currentMap;
	private boolean active;
	
	public MapRenderer() {
		active = true;
	}
	
	public void setMap(HexMap map) {
		currentMap = map;
	}

	@Override
	public void render(SpriteBatch batch) {
		
	}

	@Override
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public void registerSelf() {
		if (currentMap != null) {
			RenderMaster.get().registerRenderable(this);
		}
		else {
			Gdx.app.error("MapRenderer", "No hexmap set - renderer not registered!");
		}
	}

	@Override
	public int getPriority() {
		return PRIO;
	}
	
	private float getScale(float pixelSize, float scaleToWorld) {
		return (viewportWidth*scaleToWorld) /  pixelSize;
	}

}
