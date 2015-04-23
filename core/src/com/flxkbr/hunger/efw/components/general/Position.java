package com.flxkbr.hunger.efw.components.general;

import com.badlogic.gdx.math.Vector2;
import com.flxkbr.hunger.efw.HRComp;

public class Position implements HRComp {
	
	public Vector2 pos = new Vector2(0, 0);

	@Override
	public String getType() {
		return "Position";
	}

}
