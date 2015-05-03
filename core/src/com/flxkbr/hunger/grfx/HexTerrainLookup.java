package com.flxkbr.hunger.grfx;

import com.badlogic.gdx.graphics.Texture;
import com.flxkbr.hunger.load.LoadManager;

public class HexTerrainLookup {

	public static String nameForTerrain(int terrain) {
		return "prototype_hex";
	}
	
	public static int[] regionForTerrain(int terrain) {
		if (terrain > 4) {
			return new int[] {0, 0, 128, 111};
		}
		else {
			return new int[] {128*terrain, 0, 128, 111};
		}
	}
	
	public static Texture textureForTerrain(int terrain) throws Exception {
		return LoadManager.get().getTexture("prototype_hex");
	}
}
