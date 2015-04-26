package com.flxkbr.hunger.load;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.ObjectMap;

public class LoadManager extends HRDisposable {
	
	private static LoadManager manager;
	
	private ObjectMap<String, Texture> textures;
	private ObjectMap<String, String> strings;
	private ObjectMap<String, String> rawtextfiles;
	
	private LoadManager() {
		super();
		textures = new ObjectMap<String, Texture>();
		strings = new ObjectMap<String, String>();
		rawtextfiles = new ObjectMap<String, String>();
	}
	
	public static LoadManager get() {
		if (manager == null) {
			manager = new LoadManager();
		}
		return manager;
	}
	
	public void dispose() {
		for (Texture tex : textures.values()) {
			tex.dispose();
		}
	}
	
	public boolean loadRawTextFile(String filename) {
		String fn = filename.endsWith(".txt") ? filename : filename+".txt";
		FileHandle f = Gdx.files.internal("text/"+fn);
		if (f.exists()) {
			String content = f.readString();
			fn = fn.replaceAll("\\.txt", "");
			rawtextfiles.put(fn, content);
			return true;
		} else {
			Gdx.app.error(LoadManager.class.toString(), "Specified textfile " + filename + " could not be found");
			return false;
		}
	}
	
	public String getRawTextFile(String key) {
		if (rawtextfiles.containsKey(key)) {
			return rawtextfiles.get(key);
		}
		else if (loadRawTextFile(key)) {
			return rawtextfiles.get(key);
		} else {
			throw new GdxRuntimeException("Raw Textfile "+ key +" could not be loaded");
		}
	}
	
	public boolean loadTexture(String filename) {
		String fn = filename.endsWith(".png") ? filename : filename+".png";
		FileHandle f = Gdx.files.internal("textures/"+fn);
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
		else if (loadTexture(key)) {
			return textures.get(key);
		} else {
			throw new GdxRuntimeException("Texture "+ key +" could not be loaded");
		}
	}
}
