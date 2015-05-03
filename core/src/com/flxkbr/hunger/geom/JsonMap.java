package com.flxkbr.hunger.geom;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntArray;

public class JsonMap {
		private IntArray map;
		private int width;
		private String name;
		private Array<String> categories;
		
		public JsonMap() {
			
		}

		public JsonMap(int size) {
			map = new IntArray();
			width = size;
			name = "EMPTY";
			String[] smack = { "gaga", "fudi", "homo", "nohomo" };
			categories = new Array<String>(smack);
			testDataGeneration();
		}

		public JsonMap(IntArray map, int width, String name, Array<String> categories) {
			this.map = map;
			this.width = width;
			this.name = name;
			this.categories = categories;
		}

		public void dispose() {
			categories.clear();
			map.clear();
		}

		public Array<String> getCategories() {
			return categories;
		}

		public IntArray getMap() {
			return map;
		}

		public String getName() {
			return name;
		}

		public int getWidth() {
			return width;
		}

		public void setCategories(Array<String> categories) {
			this.categories = categories;
		}

		public void setMap(IntArray map) {
			this.map = map;
			width = (int) Math.sqrt(map.size);
		}

		public void setName(String name) {
			this.name = name;
		}

		private void testDataGeneration() {
			for (int i = 0; i < width * width; ++i) {
				map.add(MathUtils.random(3));
			}
			name = "fug me sily";
		}
	}