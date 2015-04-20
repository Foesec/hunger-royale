package com.flxkbr.hunger.geom;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Hexagon {
	
	// TODO: keep eye on float to int casts --> rounding errors?
	
	private final static float HEIGHT_FACTOR = (float) (Math.sqrt(3)/2.0);
	
	// based on even-q implementation --> (1, 0) goes up
	private Vector2 center;
	private float size;
	private float height, width;
	private int offsetX, offsetY;
	private Vector2 axial = new Vector2();
	private Vector3 cube = new Vector3();

	public Hexagon() {
		size = 1;
		offsetX = offsetY = 0;
		center = new Vector2(0, 0);
		initialize(true);
	}
	
	public Hexagon(boolean offset, int x, int y, int size, Vector2 center) {
		if (offset) {
			this.offsetX = x;
			this.offsetY = y;
			this.size = size;
			this.center = center;
		} else {
			axial.set(x, y);
			this.size = size;
			this.center = center;
		}
		width = 2 * size;
		height = HEIGHT_FACTOR * size;
		initialize(offset);
	}
	
	private void initialize(boolean offset) {
		if (offset) {
			cube.x = offsetX;
			cube.z = offsetY - (offsetX + (offsetX&1) / 2);
			cube.y = -cube.x-cube.z;
			axial.set(cube.x, cube.z);
		} else {
			cube.set(axial.x, axial.y, -axial.x-axial.y);
			offsetX = (int)cube.x;
			offsetY = (int)(cube.z + (cube.x + ((int)cube.x & 1)) / 2);
		}
	}
	
	public static Vector2 hexCorner(Vector2 center, int size, int i) {
		float angle_deg = 60 * i;
		float angle_rad = MathUtils.PI / 180 * angle_deg;
		return new Vector2(center.x + size * MathUtils.cos(angle_rad),
							center.y + size * MathUtils.sin(angle_rad));
	}
	
	public static Vector3 axialToCube(Vector2 axial) {
		float x = axial.x;
		float z = axial.y;
		float y = -x-z;
		return new Vector3(x, y, z);
	}
	
	public static Vector2 cubeToAxial(Vector3 cube) {
		return new Vector2(cube.x, cube.z);
	}
	
	public static Vector2 axialToOffset(Vector2 axial) {
		int x = (int)axial.x;
		int zCube = (int)axial.y;
		int y = zCube + (x + (x&1) / 2);
		return new Vector2(x, y);
	}
	
	public void moveAxial(Vector2 delta) {
		axial.set(delta.x, delta.y);
		initialize(false);
	}
	
	public void setOffsetCoordinates(int x, int y) {
		this.offsetX = x;
		this.offsetY = y;
		initialize(true);
	}
	
	public void setAxialCoordinates(int x, int y) {
		axial.x = x;
		axial.y = y;
		initialize(false);
	}
	
	public int getX() {
		return offsetX;
	}
	
	public int getY() {
		return offsetY;
	}
	
	public Vector2 getAxial() {
		return axial;
	}
	
	public Vector3 getCube() {
		return cube;
	}
	
	public float getWidth() {
		return width;
	}
	
	public float getHeight() {
		return height;
	}
}
