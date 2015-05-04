package com.flxkbr.hunger.gmobj;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.flxkbr.hunger.geom.HexMap;
import com.flxkbr.hunger.geom.Hexagon;
import com.flxkbr.hunger.grfx.IRenderable;
import com.flxkbr.hunger.grfx.MapRenderer;
import com.flxkbr.hunger.load.HRDisposable;
import com.flxkbr.hunger.logic.IUpdatable;

public class Map extends HRDisposable implements IRenderable, IUpdatable {
	
	private HexMap mapGeom;
	private MapRenderer mapRend;
	
	Array<HexData> mapData;
	
	
	public Map(HexMap map, MapRenderer renderer) {
		super();
		this.mapGeom = map;
		this.mapRend = renderer;
		processGeomToMap();
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
		this.mapRend.render(batch);
	}

	public void registerAsRenderable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isActive() {
		// TODO Auto-generated method stub
		return false;
	}
	
	private void processGeomToMap() {
		mapData = new Array<HexData>(mapGeom.getMap().size);
		for (Hexagon hex : mapGeom.getMap()) {
			mapData.add(new HexData(hex.getAxial()));
		}
	}
}
