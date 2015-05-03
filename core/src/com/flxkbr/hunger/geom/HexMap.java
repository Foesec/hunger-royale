package com.flxkbr.hunger.geom;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.Json;
import com.flxkbr.hunger.GlobalConstants;

public class HexMap {

	public final static String MAPDIR = GlobalConstants.MAPDIR;
	public final static String TAG = "HEXMAP";
	
	private static final Vector2[] axialDirection = { new Vector2(1, 0), new Vector2(1, -1), new Vector2(0, -1),
			new Vector2(-1, 0), new Vector2(-1, 1), new Vector2(0, 1) };

	private static final Vector3[] cubeDirections = { new Vector3(1, -1, 0), new Vector3(1, 0, -1),
			new Vector3(0, 1, -1), new Vector3(-1, 1, 0), new Vector3(-1, 0, 1), new Vector3(0, -1, 1) };
	
	private final static float HEX_SIZE = GlobalConstants.HEXSIZE;
	private final static float AXPIXFACTOR = GlobalConstants.AXPIXFACTOR;

	private int WIDTH, HEIGHT;
	private Array<Hexagon> map;
	private JsonMap jsonMap;
	private Json json = new Json();
	private IntArray dummyMap = new IntArray(new int[] { 1, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1 });

	/**
	 * @throws Exception 
	 * @deprecated only for testing purposes
	 */
	@Deprecated
	public HexMap() throws Exception {
		WIDTH = HEIGHT = 4;
		map = new Array<Hexagon>(WIDTH * HEIGHT);
		for (int y = 0; y < HEIGHT; ++y) {
			for (int x = 0; x < WIDTH; ++x) {
				map.add(new Hexagon(true, x, y, dummyMap.get(x + y * WIDTH), offsetToWorld(x, y)));
			}
		}
		jsonMap = new JsonMap(dummyMap, WIDTH, "negers", new Array<String>(new String[] { "point", "less" }));
	}

	public HexMap(String filename) throws Exception {
		parseFromJson(filename);
	}

	// get axial coordinates of neighbor of #hex in #direction
	public static Vector2 getAxialNeighbour(Hexagon hex, int direction) {
		Vector2 dir = axialDirection[direction];
		return new Vector2(hex.getAxial().add(dir));
	}

//	public static Vector2 axialToPixel(Vector2 axial) {
//		float x = AXPIXFACTOR * (axial.x + axial.y / 2);
//		float y = HEX_SIZE * 3 / 2 * axial.y;
//		return new Vector2(x, y);
//	}

	public void dispose() {
		// TODO: check for disposables!
	}

	public Array<Hexagon> getMap() {
		return map;
	}

	public void parseFromJson(String filename) throws Exception {
		String fn = filename.endsWith(".json") ? filename : filename + ".json";
		FileHandle handle = Gdx.files.internal(MAPDIR + fn);
		if (handle.exists()) {
			jsonMap = json.fromJson(JsonMap.class, handle);
			Gdx.app.log(HexMap.class.getName(), "Json Mapfile successfully read from file: " + fn);
		} else {
			Gdx.app.error(HexMap.class.getName(), "Specified Json Mapfile >>" + fn + "<< does not exist!");
		}
		readMapFromJsonMap();
	}

	public void writeToJson(String filename) {
		if (jsonMap != null) {
			String jsonString = json.toJson(jsonMap, JsonMap.class);
			jsonString = json.prettyPrint(jsonString);
			System.out.println(filename + ": " + jsonString);
		}
	}

	private int axialDistance(Vector2 a, Vector2 b) {
		Vector3 ac = Hexagon.axialToCube(a);
		Vector3 bc = Hexagon.axialToCube(b);
		return cubeDistance(ac, bc);
	}

	// give Hexagon located by axial coordinates
	private Hexagon getByAxial(Vector2 axial) {
		Vector2 offset = Hexagon.axialToOffset(axial);
		return map.get((int) (offset.x + offset.y * WIDTH));
	}

	// give Hexagon located by standard offset coordinates
	private Hexagon getByOffset(int x, int y) {
		return map.get(x + y * WIDTH);
	}

	@Deprecated
	private int offsetDistance(int xa, int ya, int xb, int yb) {
		Vector3 ac = Hexagon.offsetToCube(xa, ya);
		Vector3 bc = Hexagon.offsetToCube(xb, yb);
		return cubeDistance(ac, bc);
	}

	@Deprecated
	private int cubeDistance(Vector3 a, Vector3 b) {
		return (int) (Math.abs(a.x - b.x) + Math.abs(a.y - b.y) + Math.abs(a.z - b.z)) / 2;
	}

	private Vector2 offsetToWorld(int x, int y) {
		float xp = HEX_SIZE * (3f / 2f) * (float)x;
		float yp = (float) (HEX_SIZE * Math.sqrt(3) * ((float)y - 0.5f * (x & 1)));
		return new Vector2(xp, yp);
	}

	private void readMapFromJsonMap() throws Exception {
		if (jsonMap != null) {
			WIDTH = HEIGHT = jsonMap.getWidth();
			if (map == null)
				map = new Array<Hexagon>(WIDTH * HEIGHT);
			else
				map.clear();
			for (int i = 0; i < jsonMap.getMap().size; ++i) {
				int x = i % WIDTH;
				int y = i / WIDTH;
				map.add(new Hexagon(true, x, y, jsonMap.getMap().get(i), offsetToWorld(x, y)));
			}
			Gdx.app.debug(HexMap.class.toString(), "JsonMap successfully read into Hexagon Map");
		} else {
			Gdx.app.error(HexMap.class.toString(), "No jsonMap has been parsed yet!");
		}
	}

	private Vector3 cubeRound(Vector3 cube) {
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

	private Vector2 axialRound(Vector2 axial) {
		return Hexagon.cubeToAxial(cubeRound(Hexagon.axialToCube(axial)));
	}

}
