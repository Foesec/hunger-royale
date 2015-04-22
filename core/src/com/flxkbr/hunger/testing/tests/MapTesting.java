package com.flxkbr.hunger.testing.tests;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.flxkbr.hunger.geom.HexMap;
import com.flxkbr.hunger.geom.Hexagon;
import com.flxkbr.hunger.testing.RoyalTest;

public class MapTesting implements RoyalTest{
	
	private HexMap map;
	private final int SIZE = 5;

	public void init() {
		map = new HexMap();
	}
	
	public boolean run() {
		Array<Hexagon> hm = map.getMap();
		int count = 0;
		System.out.print("[");
		for (Hexagon hex : hm) {
			if (count == 4) {
				count = 0;
				System.out.print("\n ");
			}
			System.out.print(hex.getTerrainType() + ",");
			++count;
		}
		System.out.print("]\n");
		
		count = 0;
		for (Hexagon hex : hm) {
			if (count == 4) {
				count = 0;
				System.out.print("\n");
			}
			System.out.print(hex.getCenter() + ",");
			++count;
		}
		return true;
	}
	
	public void cleanup() {
		map.dispose();
	}
	
}
