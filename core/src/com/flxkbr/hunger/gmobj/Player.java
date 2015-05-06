package com.flxkbr.hunger.gmobj;

import com.badlogic.gdx.math.Vector2;
import com.flxkbr.hunger.efw.components.PatientMulti;
import com.flxkbr.hunger.grfx.PlayerRenderer;

public class Player {
	
	private static Player player;

	//PlayerSystem system;
	private PatientMulti data;
	private PlayerRenderer renderer;
	
	public static Player getPlayer() throws Exception {
		if (player == null) {
			player = new Player();
		}
		return player;
	}
	
	private Player() throws Exception {
		data = new PatientMulti();
		renderer = PlayerRenderer.get();
	}
	
	public void moveTo(Vector2 axial) {
		data.position.pos = axial;
	}
	
	public Vector2 getPosition() {
		return data.position.pos;
	}
}
