package com.flxkbr.hunger.geom;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.flxkbr.hunger.GlobalConstants;

public class Hexagon {
	
	// TODO: keep eye on float to int casts --> rounding errors?
	
	private final static float HEIGHT_FACTOR = (float) (Math.sqrt(3)/2.0);
	
	// based on even-q implementation --> (1, 0) goes up
	private Vector2 center;
	private float size = GlobalConstants.HEXSIZE;
	private float height, width;
	private int offsetX, offsetY;
	private int terrainType;
	private Vector2 axial = new Vector2();
	private Vector3 cube = new Vector3();
	//private Vector2 screenPosition = new Vector2();

	public Hexagon() {
		//size = 1;
		offsetX = offsetY = 0;
		center = new Vector2(0, 0);
		terrainType = 0;
		initialize(true);
	}
	
	public Hexagon(boolean offset, int x, int y, Vector2 center) {
		if (offset) {
			this.offsetX = x;
			this.offsetY = y;
			this.center = center;
		} else {
			axial.set(x, y);
			this.center = center;
		}
		width = 2 * size;
		height = HEIGHT_FACTOR * size;
		terrainType = 0;
		initialize(offset);
	}
	
	public Hexagon(boolean offset, int x, int y, int size, int terrainType, Vector2 center) {
		this(offset, x, y, center);
		this.terrainType = terrainType;
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
	
	public static Vector3 offsetToCube(int x, int y) {
		int z = y - (x + (x&1)) / 2;
		int yn = -x-z;
		return new Vector3(x, yn, z);
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
	
	public void setScreenPosition(Vector2 screenPos) {
		center = screenPos;
	}
	
	public void setCenter(Vector2 center) {
		this.center = center;
	}
	
	public int getOffsetX() {
		return offsetX;
	}
	
	public int getOffsetY() {
		return offsetY;
	}
	
	public Vector2 getOffset() {
		return new Vector2(offsetX, offsetY);
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
	
	public int getTerrainType() {
		return terrainType;
	}
	
	public Vector2 getCenter() {
		return center;
	}
	
}
