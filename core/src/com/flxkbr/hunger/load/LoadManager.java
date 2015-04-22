package com.flxkbr.hunger.load;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.ObjectMap;
import com.flxkbr.hunger.testing.LogHandler;

public class LoadManager {
	
	private static LoadManager manager;
	
	private ObjectMap<String, Texture> textures;
	private ObjectMap<String, Json> jsons;
	private ObjectMap<String, String> strings;
	
	private LoadManager() {
		
	}
	
	public static LoadManager getLoadManager() {
		if (manager == null) {
			manager = new LoadManager();
		}
		return manager;
	}
	
	public Json getJson(String key) {
		if (jsons.containsKey(key)) {
			return jsons.get(key);
		}
		Gdx.app.error(LoadManager.class.toString(), "Specified Json File could not be found!");
		LogHandler.submitError(LoadManager.class.toString(), "Specified Json File could not be found!");
		return null;
	}
	
	public boolean loadTexture(String filename) {
		String fn = filename.endsWith(".png") ? filename : filename+".png";
		FileHandle f = Gdx.files.internal("data/textures/"+fn);
		if (f.exists()) {
			Texture tex = new Texture(f);
			fn = fn.replaceAll("\\.png", "");
			textures.put(fn, tex);
			return true;
		} else {
			Gdx.app.error(LoadManager.class.toString(), "Specified texture "+filename+" could not be found");
			return false;
		}
	}
	
	public Texture getTexture(String key) throws Exception {
		if (textures.containsKey(key)) {
			return textures.get(key);
		}
		if (loadTexture(key)) {
			return textures.get(key);
		}
		throw new GdxRuntimeException("Texture "+ key +" could not be loaded");
	}
}
