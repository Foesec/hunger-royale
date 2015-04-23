package com.flxkbr.hunger.efw.components.human;

import com.flxkbr.hunger.efw.HRComp;

public class Disposition implements HRComp {
	
	public int calculating = 1;
	public int smart = 1;
	public int mad = 1;
	public int predictable = 1;
	public int cunning = 1;
	public int cooperative = 1;
	public int cautious = 1;

	@Override
	public String getType() {
		return "Disposition";
	}

}
