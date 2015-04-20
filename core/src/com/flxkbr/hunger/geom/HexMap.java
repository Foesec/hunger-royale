package com.flxkbr.hunger.geom;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public class HexMap {
	
	public final int WIDTH, HEIGHT, HEX_SIZE;
	public final float AXPIXFACTOR;
	
	private Array<Hexagon> map;
	
	private static final Vector2[] axialDirection = {	new Vector2(1, 0), new Vector2(1, -1), new Vector2(0, -1),
															new Vector2(-1, 0), new Vector2(-1, 1), new Vector2(0, 1)};
	private static final Vector3[] cubeDirections = {	new Vector3(1, -1, 0), new Vector3(1, 0, -1), new Vector3(0, 1, -1),
															new Vector3(-1, 1, 0), new Vector3(-1, 0, 1), new Vector3(0, -1, 1)};

	public HexMap(int width, int height, int hexSize) {
		WIDTH = width;
		HEIGHT = height;
		HEX_SIZE = hexSize;
		AXPIXFACTOR = (float) (hexSize * Math.sqrt(3));
		map = new Array<Hexagon>(width*height);
	}
	
	// get axial coordinates of neighbor of @hex in @direction 
	public static Vector2 getAxialNeighbour(Hexagon hex, int direction) {
		Vector2 dir = axialDirection[direction];
		return new Vector2(hex.getAxial().add(dir));
	}
	
	// give Hexagon located by standard offset coordinates
	private Hexagon getByOffset(int x, int y) {
		return map.get(x + y*WIDTH);
	}
	
	// give Hexagon located by axial coordinates
	private Hexagon getByAxial(Vector2 axial) {
		Vector2 offset = Hexagon.axialToOffset(axial);
		return map.get((int)(offset.x + offset.y*	WIDTH));
	}
	
	private Vector2 axialToPixel(Vector2 axial) {
		float x = AXPIXFACTOR * (axial.x + axial.y/2);
		float y = HEX_SIZE * 3/2 * axial.y;
		return new Vector2(x, y);
	}
	
	private Vector2 offsetToPixel(int x, int y) {
		float xp = HEX_SIZE * 3/2 * x;
		float yp = AXPIXFACTOR * (y - 0.5f * (x&1));
		return new Vector2(xp, yp);
	}
	
	@Deprecated
	private int cubeDistance(Vector3 a, Vector3 b) {
		return (int)(Math.abs(a.x - b.x) + Math.abs(a.y - b.y) + Math.abs(a.z - b.z)) / 2;
	}
	
	private int axialDistance(Vector2 a, Vector2 b) {
		Vector3 ac = Hexagon.axialToCube(a);
		Vector3 bc = Hexagon.axialToCube(b);
		return cubeDistance(ac, bc);
	}
	
	@Deprecated
	private int offsetDistance(int xa, int ya, int xb, int yb) {
		Vector3 ac = Hexagon.offsetToCube(xa, ya);
		Vector3 bc = Hexagon.offsetToCube(xb, yb);
		return cubeDistance(ac, bc);
	}
	
	private Vector3 cubeRound(Vector3 cube) {
		int rx = MathUtils.round(cube.x);
		int ry = MathUtils.round(cube.y);
		int rz = MathUtils.round(cube.z);
		
		float x_diff = Math.abs(rx - cube.x);
		float y_diff = Math.abs(ry - cube.y);
		float z_diff = Math.abs(rz - cube.z);

		if (x_diff > y_diff && x_diff > z_diff)
			rx = -ry-rz;
		else if (y_diff > z_diff)
			ry = -rx-rz;
		else
			rz = -rx-ry;
		
		return new Vector3(rx, ry, rz);
	}
	
	private Vector2 axialRound(Vector2 axial) {
		return Hexagon.cubeToAxial(cubeRound(Hexagon.axialToCube(axial)));
	}
	
}
