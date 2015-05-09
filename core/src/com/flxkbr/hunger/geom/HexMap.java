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
	
	private Hexagon oobHex = new Hexagon(true, -1, -1, -1, HexMath.offsetToWorld(-1, -1));


	public HexMap(String filename) throws Exception {
		parseFromJson(filename);
	}

	// get axial coordinates of neighbor of #hex in #direction
	public static Vector2 getAxialNeighbour(Hexagon hex, int direction) {
		Vector2 dir = axialDirection[direction%axialDirection.length];
		return new Vector2(hex.getAxial().add(dir));
	}

	public void dispose() {
		// TODO: check for disposables!
	}

	public Array<Hexagon> getMap() {
		return map;
	}
	
	public int getWidth() {
		return WIDTH;
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
	
	public Hexagon getByWorld(float x, float y) {
		return getByAxial(HexMath.worldToAxial(x,y));
	}

	// give Hexagon located by axial coordinates
	public Hexagon getByAxial(Vector2 axial) {
		Vector2 offset = HexMath.axialToOffset(axial);
		if (offset.x < 0 || offset.y < 0 || offset.x >= WIDTH || offset.y >= HEIGHT) {
			return oobHex;
		}
		return map.get((int) (offset.x + offset.y * WIDTH));
	}

	private int axialDistance(Vector2 a, Vector2 b) {
		Vector3 ac = HexMath.axialToCube(a);
		Vector3 bc = HexMath.axialToCube(b);
		return cubeDistance(ac, bc);
	}

	// give Hexagon located by standard offset coordinates
	private Hexagon getByOffset(int x, int y) {
		return map.get(x + y * WIDTH);
	}

	@Deprecated
	private int offsetDistance(int xa, int ya, int xb, int yb) {
		Vector3 ac = HexMath.offsetToCube(xa, ya);
		Vector3 bc = HexMath.offsetToCube(xb, yb);
		return cubeDistance(ac, bc);
	}

	@Deprecated
	private int cubeDistance(Vector3 a, Vector3 b) {
		return (int) (Math.abs(a.x - b.x) + Math.abs(a.y - b.y) + Math.abs(a.z - b.z)) / 2;
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
				map.add(new Hexagon(true, x, y, jsonMap.getMap().get(i), HexMath.offsetToWorld(x, y)));
			}
			Gdx.app.debug(HexMap.class.toString(), "JsonMap successfully read into Hexagon Map");
		} else {
			Gdx.app.error(HexMap.class.toString(), "No jsonMap has been parsed yet!");
		}
	}

}
