package com.flxkbr.hunger.efw.components.human;

import com.flxkbr.hunger.efw.HRComp;

public class Traits implements HRComp {
	
	private static final String TYPE = "Traits";
	
	// lernt schnell
	public int insight = 1;
	// schwieriger zu beeindrucken
	public int serenity = 1;
	// gute dinge passieren
	public int bliss = 1;
	// kann mit anderen umgehen
	public int charisma = 1;
	// nimmt mehr dinge wahr
	public int perception = 1;
	// kann andere leichter durchschauen
	public int facereading = 1;

	@Override
	public String getType() {
		return TYPE;
	}

}
