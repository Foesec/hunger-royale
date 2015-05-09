package com.flxkbr.hunger.efw.systems;

import com.badlogic.gdx.math.Vector2;
import com.flxkbr.hunger.efw.HRSystem;
import com.flxkbr.hunger.geom.HexMath;

public class Position implements HRSystem {
	
	private Vector2 world = new Vector2(0, 0);
	private Vector2 axial = new Vector2(0, 0);
	
	public Vector2 getWorld() {
		return world;
	}
	
	public Vector2 getAxial() {
		return axial;
	}
	
	public void setByAxial(Vector2 axial) {
		this.axial = axial;
		this.world = HexMath.axialToWorld(axial);
	}
	
	public void setByWorld(float x, float y) {
		this.world.x = x;
		this.world.y = y;
		this.axial = HexMath.worldToAxial(x, y);
	}
	
	public void setByWorld(Vector2 world) {
		this.world = world;
		this.axial = HexMath.worldToAxial(world.x, world.y);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
