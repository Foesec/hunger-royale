package com.flxkbr.hunger.gmobj;

import com.badlogic.gdx.math.Vector2;
import com.flxkbr.hunger.efw.components.PatientMulti;

public class Player {
	
	private static Player player;

	//PlayerSystem system;
	PatientMulti data;
	
	public static Player getPlayer() {
		if (player == null) {
			player = new Player();
		}
		return player;
	}
	
	private Player() {
		data = new PatientMulti();
	}
	
	public void moveTo(Vector2 axial) {
		data.position.pos = axial;
	}
	
	public Vector2 getPosition() {
		return data.position.pos;
	}
}
