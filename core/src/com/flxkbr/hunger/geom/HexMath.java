package com.flxkbr.hunger.geom;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.flxkbr.hunger.GlobalConstants;

public abstract class HexMath {
	
	private static final float HEX_SIZE = GlobalConstants.HEXSIZE;

	public static Vector2 worldToAxial(float x, float y) {
		Vector2 fract = new Vector2();
		fract.x = x * (2f/3f) / HEX_SIZE;
		fract.y = (float) ((-x / 3f + Math.sqrt(3) / 3f * y) / HEX_SIZE);
		return axialRound(fract);
	}
	
	public static Vector2 worldToOffset(float x, float y) {
		return axialToOffset(worldToAxial(x,y));
	}
	
	public static Vector3 axialToCube(Vector2 axial) {
		float x = axial.x;
		float z = axial.y;
		float y = -x-z;
		return new Vector3(x, y, z);
	}
	
	public static Vector2 axialToOffset(Vector2 axial) {
		return cubeToOffset(axialToCube(axial));
	}
	
	public static Vector2 axialToWorld(Vector2 axial) {
		float x = HEX_SIZE * (3/2) * axial.x;
		float y = HEX_SIZE * GlobalConstants.SQRT3 * (axial.y + axial.x/2f);
		return new Vector2(x, y);
	}

	public static Vector2 cubeToOffset(Vector3 cube) {
		Vector2 offset = new Vector2();
		offset.x = cube.x;
		offset.y = cube.z + (cube.x + ((int)cube.x & 1)) / 2;
		return offset;
	}
	
	public static Vector2 cubeToAxial(Vector3 cube) {
		return new Vector2(cube.x, cube.z);
	}
	
	public static Vector3 offsetToCube(int x, int y) {
		int z = y - (x + (x&1)) / 2;
		int yn = -x-z;
		return new Vector3(x, yn, z);
	}
	
	public static Vector2 offsetToWorld(int x, int y) {
		float xp = HEX_SIZE * (3f / 2f) * (float)x;
		float yp = (float) (HEX_SIZE * Math.sqrt(3) * ((float)y - 0.5f * (x & 1)));
		return new Vector2(xp, yp);
	}

	public static Vector2 hexCorner(Vector2 center, int size, int i) {
		float angle_deg = 60 * i;
		float angle_rad = MathUtils.PI / 180 * angle_deg;
		return new Vector2(center.x + size * MathUtils.cos(angle_rad),
							center.y + size * MathUtils.sin(angle_rad));
	}

	private static Vector3 cubeRound(Vector3 cube) {
		int rx = MathUtils.round(cube.x);
		int ry = MathUtils.round(cube.y);
		int rz = MathUtils.round(cube.z);
	
		float x_diff = Math.abs(rx - cube.x);
		float y_diff = Math.abs(ry - cube.y);
		float z_diff = Math.abs(rz - cube.z);
	
		if (x_diff > y_diff && x_diff > z_diff)
			rx = -ry - rz;
		else if (y_diff > z_diff)
			ry = -rx - rz;
		else
			rz = -rx - ry;
	
		return new Vector3(rx, ry, rz);
	}

	private static Vector2 axialRound(Vector2 axial) {
		return cubeToAxial(cubeRound(axialToCube(axial)));
	}
}
