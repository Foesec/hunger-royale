package com.flxkbr.hunger.grfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flxkbr.hunger.GlobalConstants;
import com.flxkbr.hunger.geom.HexMap;
import com.flxkbr.hunger.geom.Hexagon;

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
		for (Hexagon hex : currentMap.getMap()) {
			hex.render(batch);
		}
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
			try {
				RenderMaster.get().registerRenderable(this);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			Gdx.app.error("MapRenderer", "No hexmap set - renderer not registered!");
		}
	}

	@Override
	public int getPriority() {
		return PRIO;
	}

}
