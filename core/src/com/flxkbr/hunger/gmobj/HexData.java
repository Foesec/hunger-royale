package com.flxkbr.hunger.gmobj;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.flxkbr.hunger.efw.components.mapdata.Terrain;
import com.flxkbr.hunger.efw.systems.Position;

public class HexData {

	private Terrain terrain;
	private Position position;
	
	// 0-1f --> lower is less dangerous but less resources
	// 		--> higher is more dangerous but better & more resources
	private float tenseness;
	
	private Array<Patient> occupiers;
	private Array<Item> treasures;
	private Array<Resource> resources;
	
	public HexData(Vector2 axial) {
		this.position = new Position();
		this.position.setByAxial(axial);
	}
}
