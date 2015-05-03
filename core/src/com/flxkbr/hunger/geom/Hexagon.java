package com.flxkbr.hunger.geom;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Align;
import com.flxkbr.hunger.GlobalConstants;
import com.flxkbr.hunger.connectors.MapScreenMaster;
import com.flxkbr.hunger.grfx.HexTerrainLookup;
import com.flxkbr.hunger.grfx.RenderMaster;

public class Hexagon {
	
	// TODO: keep eye on float to int casts --> rounding errors?
	
	private final static float HEIGHT_FACTOR = GlobalConstants.SQRT3BY2;//(float) (Math.sqrt(3)/2.0);
	private final static float SIZESCALE = GlobalConstants.HEXSCALE;
	
	// based on even-q implementation --> (1, 0) goes up
	private Vector2 center;
	private float size = GlobalConstants.HEXSIZE;
	private float height, width;
	private int offsetX, offsetY;
	private int terrainType;
	private Vector2 axial = new Vector2();
	private Vector3 cube = new Vector3();
	
	private Sprite spr;

//	@Deprecated
//	public Hexagon() throws Exception {
//		//size = 1;
//		offsetX = offsetY = 0;
//		center = new Vector2(0, 0);
//		terrainType = 0;
//		initialize(true);
//	}
	
	public Hexagon(boolean offset, int x, int y, Vector2 center) throws Exception {
		this(offset, x, y, 0, center);
	}
	
	public Hexagon(boolean offset, int x, int y, int terrainType, Vector2 center) throws Exception {
		if (offset) {
			this.offsetX = x;
			this.offsetY = y;
			this.center = center;
		} else {
			axial.set(x, y);
			this.center = center;
		}
		width = 2 * size;
		height = HEIGHT_FACTOR * width;
		this.terrainType = terrainType;
		initialize(offset);
	}
	
	private void initialize(boolean offset) throws Exception {
		if (offset) {
			cube.set(offsetToCube(offsetX, offsetY));
			axial.set(cubeToAxial(cube));
//			cube.x = offsetX;
//			cube.z = offsetY - (offsetX + (offsetX&1) / 2);
//			cube.y = -cube.x-cube.z;
//			axial.set(cube.x, cube.z);
		} else {
			cube.set(axialToCube(axial));
			Vector2 ofst = axialToOffset(axial);
			offsetX = (int)ofst.x;
			offsetY = (int)ofst.y;
//			cube.set(axial.x, axial.y, -axial.x-axial.y);
//			offsetX = (int)cube.x;
//			offsetY = (int)(cube.z + (cube.x + ((int)cube.x & 1)) / 2);
		}
		
		// terrainType < 0 --> out of bounds Hexagon
		if (terrainType >= 0) {
			int[] tc = HexTerrainLookup.regionForTerrain(terrainType);
			spr = new Sprite(HexTerrainLookup.textureForTerrain(terrainType), tc[0], tc[1], tc[2], tc[3]);
			spr.setSize(width, height);
			spr.setCenter(center.x, center.y);
		}
		//Gdx.app.log(Hexagon.class.getName(), "offset: " + offsetX + "," + offsetY + ", center: " + center);
	}
	
/*	private void recalculate(boolean offset) {
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
		spr.setCenter(center.x, center.y);
	}*/
	
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
	
	public static Vector2 cubeToOffset(Vector3 cube) {
		Vector2 offset = new Vector2();
		offset.x = cube.x;
		offset.y = cube.z + (cube.x + ((int)cube.x & 1)) / 2;
		return offset;
	}
	
	public static Vector2 cubeToAxial(Vector3 cube) {
		return new Vector2(cube.x, cube.z);
	}
	
	public static Vector2 axialToOffset(Vector2 axial) {
		return cubeToOffset(axialToCube(axial));
	}
	
	public static Vector3 offsetToCube(int x, int y) {
		int z = y - (x + (x&1)) / 2;
		int yn = -x-z;
		return new Vector3(x, yn, z);
	}
	
	public void render(SpriteBatch batch) {
		spr.draw(batch);
		MapScreenMaster._getBMF().draw(batch, (int)axial.x + "," + (int)axial.y, center.x, center.y, 0, Align.center, false);
	}
	
/*	public void moveAxial(Vector2 delta) {
		axial.set(delta.x, delta.y);
		recalculate(false);
	}
	
	public void setOffsetCoordinates(int x, int y) {
		this.offsetX = x;
		this.offsetY = y;
		recalculate(true);
	}
	
	public void setAxialCoordinates(int x, int y) {
		axial.x = x;
		axial.y = y;
		recalculate(false);
	}
	
	public void setScreenPosition(Vector2 screenPos) {
		center = screenPos;
	}
	
	public void setCenter(Vector2 center) {
		this.center = center;
	}*/
	
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
