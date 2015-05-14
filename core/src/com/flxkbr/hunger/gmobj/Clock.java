package com.flxkbr.hunger.gmobj;

import com.flxkbr.hunger.hud.HudMaster;
import com.flxkbr.hunger.hud.elements.HudClock;

public class Clock {

	private int hours = 8;
	private int elapsedDays = 0;
	// registriert sich selbst bei constr. beim HudMaster
	private HudClock hud;
	
	public Clock() {
		hud = new HudClock(this);
	}
	
	public HudClock getHudElement() {
		return hud;
	}
	
	public void increment() {
		increment(1);
	}
	
	public void increment(int delta) {
		hours = (hours + delta);
		if (hours > 23) {
			++elapsedDays;
		}
		hours = hours%24;
	}
	
	public int getTime() {
		return hours;
	}
	
	public int getDays() {
		return elapsedDays;
	}
}
