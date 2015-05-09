package com.flxkbr.hunger.efw.components;

import com.flxkbr.hunger.efw.components.human.Disposition;
import com.flxkbr.hunger.efw.components.human.Hunger;
import com.flxkbr.hunger.efw.components.human.Qualities;
import com.flxkbr.hunger.efw.components.human.Traits;
import com.flxkbr.hunger.efw.components.human.Vigour;
import com.flxkbr.hunger.efw.systems.Position;

public class PatientMulti {

	public Disposition disposition;
	public Hunger hunger;
	public Qualities qual;
	public Traits traits;
	public Vigour vig;
	
	public Position position;
	
	public PatientMulti() {
		this.disposition = new Disposition();
		this.hunger = new Hunger();
		this.qual = new Qualities();
		this.traits = new Traits();
		this.vig = new Vigour();
		this.position = new Position();
	}
}
