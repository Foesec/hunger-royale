package com.flxkbr.hunger.geom;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public class HexMap {
	
	public final int WIDTH, HEIGHT;
	
	private Array<Hexagon> map;
	private static final Vector2[] axialDirection = {	new Vector2(1, 0), new Vector2(1, -1), new Vector2(0, -1),
															new Vector2(-1, 0), new Vector2(-1, 1), new Vector2(0, 1)};
	private static final Vector3[] cubeDirections = {	new Vector3(1, -1, 0), new Vector3(1, 0, -1), new Vector3(0, 1, -1),
															new Vector3(-1, 1, 0), new Vector3(-1, 0, 1), new Vector3(0, -1, 1)};

	public HexMap(int width, int height) {
		WIDTH = width;
		HEIGHT = height;
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
	
	private int cubeDistance(Vector3 a, Vector3 b) {
		return (int)(Math.abs(a.x - b.x) + Math.abs(a.y - b.y) + Math.abs(a.z - b.z)) / 2;
	}
	
	private int axialDistance(Vector2 a, Vector2 b) {
		Vector3 ac = Hexagon.axialToCube(a);
		Vector3 bc = Hexagon.axialToCube(b);
		return cubeDistance(ac, bc);
	}
	
}
