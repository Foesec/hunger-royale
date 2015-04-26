package com.flxkbr.hunger.gmobj;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flxkbr.hunger.geom.HexMap;
import com.flxkbr.hunger.grfx.IRenderable;
import com.flxkbr.hunger.grfx.MapRenderer;
import com.flxkbr.hunger.load.HRDisposable;
import com.flxkbr.hunger.logic.IUpdatable;

public class Map extends HRDisposable implements IRenderable, IUpdatable {
	
	HexMap mapData;
	MapRenderer renderer;
	
	public Map() {
		super();
	}

	@Override
	public void dispose() {
		
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

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(SpriteBatch batch) {
		// TODO Auto-generated method stub
		
	}

	public void registerAsRenderable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isActive() {
		// TODO Auto-generated method stub
		return false;
	}
}
