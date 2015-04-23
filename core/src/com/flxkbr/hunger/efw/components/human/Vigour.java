package com.flxkbr.hunger.efw.components.human;

import com.flxkbr.hunger.efw.HRComp;

public class Vigour implements HRComp {
	
	private final static String TYPE = "Vigour";
	
	public int total;
	public int current;
	
	public Vigour() {
		current = total = 100;
	}
	
	public Vigour(int total) {
		current = this.total = total;
	}

	@Override
	public String getType() {
		return TYPE;
	}

}
