package com.flxkbr.hunger.efw.components.human;

import com.flxkbr.hunger.efw.HRComp;

public class Qualities implements HRComp {
	
	private static final String TYPE = "Qualities";
	private static final int LIMIT = 20;
	
	/**
	 * main stats, can range between 1-20?
	 * 
	 */
	public int physique;
	public int dexterity;
	public int intelligence;
	
	public Qualities() {
		physique = dexterity = intelligence = 1;
	}
	
	public void setStats(int phys, int dex, int intl) {
		physique = Math.min(phys, LIMIT);
		dexterity = Math.min(dex, LIMIT);
		intelligence = Math.min(intl, LIMIT);
	}

	@Override
	public String getType() {
		return TYPE;
	}

}
