package com.flxkbr.hunger.grfx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flxkbr.hunger.connectors.IRegisterable;
import com.flxkbr.hunger.geom.HexMap;

public class MapRenderer implements IRenderable {
	
	private HexMap currentMap;

	@Override
	public void render(SpriteBatch batch) {
		
	}

	public void registerAsRenderable() {

	}

	@Override
	public boolean isActive() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void registerSelf() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getPriority() {
		// TODO Auto-generated method stub
		return 0;
	}

}
