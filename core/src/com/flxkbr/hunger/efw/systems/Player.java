package com.flxkbr.hunger.efw.systems;

import com.flxkbr.hunger.efw.HRSystem;
import com.flxkbr.hunger.efw.components.human.*;

public class Player implements HRSystem {
	
	private static Player player;
	
	Disposition disposition;
	Hunger hunger;
	Qualities qual;
	Traits traits;
	Vigour vig;
	
	public static Player getPlayer() {
		if (player==null)
			player = new Player();
		return player;
	}
	
	private Player() {
		disposition = new Disposition();
		hunger = new Hunger();
		qual = new Qualities();
		traits = new Traits();
		vig = new Vigour();
	}

	@Override
	public void update() {
		
	}

}
