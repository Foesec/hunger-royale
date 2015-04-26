package com.flxkbr.hunger.efw.systems;

import com.flxkbr.hunger.efw.HRSystem;
import com.flxkbr.hunger.efw.components.PatientMulti;

public class PatientSystem implements HRSystem {
	
	PatientMulti data;
	
	public PatientSystem() {
		data = new PatientMulti();
	}

	@Override
	public void update() {
		
	}

}
