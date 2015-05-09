package com.flxkbr.hunger.geom;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Align;
import com.flxkbr.hunger.GlobalConstants;
import com.flxkbr.hunger.connectors.MapScreenMaster;
import com.flxkbr.hunger.grfx.HexTerrainLookup;

public class Hexagon {
	
	// TODO: keep eye on float to int casts --> rounding errors?
	
	private final static float HEIGHT_FACTOR = GlobalConstants.SQRT3BY2;//(float) (Math.sqrt(3)/2.0);
	private final static float HEXWIDTH = GlobalConstants.HEXWIDTH;
	private final static float HEXHEIGHT = GlobalConstants.HEXHEIGHT;
	private final static float HEXSIZE = GlobalConstants.HEXSIZE;
	//private final static float SIZESCALE = GlobalConstants.SPRITESCALE;
	
	// based on even-q implementation --> (1, 0) goes up
	private Vector2 center;
	private int offsetX, offsetY;
	private int terrainType;
	private Vector2 axial = new Vector2();
	private Vector3 cube = new Vector3();
	
	private Sprite spr;
	
	@Deprecated
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

		this.terrainType = terrainType;
		initialize(offset);
	}
	
	private void initialize(boolean offset) throws Exception {
		if (offset) {
			cube.set(HexMath.offsetToCube(offsetX, offsetY));
			axial.set(HexMath.cubeToAxial(cube));
		} else {
			cube.set(HexMath.axialToCube(axial));
			Vector2 ofst = HexMath.axialToOffset(axial);
			offsetX = (int)ofst.x;
			offsetY = (int)ofst.y;
		}
		
		// terrainType < 0 --> out of bounds Hexagon
		if (terrainType >= 0) {
			int[] tc = HexTerrainLookup.regionForTerrain(terrainType);
			spr = new Sprite(HexTerrainLookup.textureForTerrain(terrainType), tc[0], tc[1], tc[2], tc[3]);
			spr.setSize(HEXWIDTH, HEXHEIGHT);
			spr.setCenter(center.x, center.y);
		}
		//Gdx.app.log(Hexagon.class.getName(), "offset: " + offsetX + "," + offsetY + ", center: " + center);
	}
	
	public void render(SpriteBatch batch) {
		spr.draw(batch);
		MapScreenMaster._getBMF().draw(batch, (int)axial.x + "," + (int)axial.y, center.x, center.y, 0, Align.center, false);
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
		return HEXWIDTH;
	}
	
	public float getHeight() {
		return HEXHEIGHT;
	}
	
	public int getTerrainType() {
		return terrainType;
	}
	
	public Vector2 getCenter() {
		return center;
	}
	
}
