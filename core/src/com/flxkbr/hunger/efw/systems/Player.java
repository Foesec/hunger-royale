package com.flxkbr.hunger.efw.systems;

import com.flxkbr.hunger.efw.HRSystem;
import com.flxkbr.hunger.efw.components.general.Position;
import com.flxkbr.hunger.efw.components.human.Disposition;
import com.flxkbr.hunger.efw.components.human.Hunger;
import com.flxkbr.hunger.efw.components.human.Qualities;
import com.flxkbr.hunger.efw.components.human.Traits;
import com.flxkbr.hunger.efw.components.human.Vigour;

public class Player implements HRSystem {
	
	private static Player player;
	
	Disposition disposition;
	Hunger hunger;
	Qualities qual;
	Traits traits;
	Vigour vig;
	
	Position position;
	
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
		
		position = new Position();
	}

	@Override
	public void update() {
		
	}

}
