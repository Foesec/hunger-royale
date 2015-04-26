package com.flxkbr.hunger.efw.systems;

import com.flxkbr.hunger.efw.HRSystem;
import com.flxkbr.hunger.efw.components.PatientMulti;

public class PlayerSystem implements HRSystem {
	
	PatientMulti data;
	
	public PlayerSystem() {
		data = new PatientMulti();
	}

	@Override
	public void update() {
		
	}

}
