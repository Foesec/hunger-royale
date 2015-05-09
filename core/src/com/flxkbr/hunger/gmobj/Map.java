package com.flxkbr.hunger.gmobj;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.flxkbr.hunger.geom.HexMap;
import com.flxkbr.hunger.geom.HexMath;
import com.flxkbr.hunger.geom.Hexagon;
import com.flxkbr.hunger.grfx.IRenderable;
import com.flxkbr.hunger.grfx.MapRenderer;
import com.flxkbr.hunger.load.HRDisposable;
import com.flxkbr.hunger.logic.IUpdatable;

public class Map extends HRDisposable implements IRenderable, IUpdatable {
	
	private int WIDTH;
	
	private HexMap mapGeom;
	private MapRenderer mapRend;
	private Array<HexData> mapData;
	
	@Deprecated
	public Map(HexMap map, MapRenderer renderer) {
		super();
		this.mapGeom = map;
		this.mapRend = renderer;
		processGeomToMap();
	}
	
	public Map(String mapName) throws Exception {
		super();
		initialize(mapName);
		processGeomToMap();
	}
	
	public void initialize(String mapName) throws Exception {
		this.mapGeom = new HexMap(mapName);
		this.mapRend = new MapRenderer();
		this.mapRend.setMap(mapGeom);
		WIDTH = mapGeom.getWidth();
	}
	
	public Hexagon getHexagonByAxial(Vector2 axial) {
		return mapGeom.getByAxial(axial);
	}
	
//	public Hexagon getHexagonByWorld(Vector2 world) {
//		return mapGeom.getByWorld(world.x, world.y);
//	}
	
	public HexData getDataByAxial(Vector2 axial) {
		Vector2 offset = HexMath.axialToOffset(axial);
		return mapData.get(Math.round(offset.x + offset.y * WIDTH));
	}

	@Override
	public void dispose() {
		mapGeom.dispose();
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

	public HexMap getGeometry() {
		return mapGeom;
	}

	public Array<HexData> getMapData() {
		return mapData;
	}
}
