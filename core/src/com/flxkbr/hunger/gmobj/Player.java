package com.flxkbr.hunger.gmobj;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.flxkbr.hunger.efw.components.PatientMulti;
import com.flxkbr.hunger.grfx.PlayerRenderer;

public class Player {
	
	private static Player player;

	//PlayerSystem system;
	private PatientMulti data;
	private PlayerRenderer renderer;
	
	public static Player get() throws Exception {
		if (player == null) {
			player = new Player();
		}
		return player;
	}
	
	private Player() throws Exception {
		data = new PatientMulti();
		renderer = PlayerRenderer.get();
		renderer.setPlayer(this);
	}
	
	public void moveToHex(Vector2 axial) {
		data.position.setByAxial(axial);
	}
	
	public Vector2 getAxialPosition() {
		return data.position.getAxial();
	}
	
	public float getX() {
		return data.position.getWorld().x;
	}
	
	public float getY() {
		return data.position.getWorld().y;
	}
	
	public void render(SpriteBatch batch) {
		renderer.render(batch);
	}
}
