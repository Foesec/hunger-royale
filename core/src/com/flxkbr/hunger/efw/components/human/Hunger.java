package com.flxkbr.hunger.efw.components.human;

import com.flxkbr.hunger.efw.HRComp;

public class Hunger implements HRComp {
	
	private static String TYPE = "Hunger";

	public float value;
	
	public Hunger() {
		value = 100f;
	}

	@Override
	public String getType() {
		return TYPE;
	}

}
