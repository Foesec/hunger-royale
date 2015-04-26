package com.flxkbr.hunger.connectors;

import com.badlogic.gdx.utils.Array;
import com.flxkbr.hunger.geom.HexMap;
import com.flxkbr.hunger.gmobj.Map;
import com.flxkbr.hunger.gmobj.Patient;
import com.flxkbr.hunger.gmobj.Player;
import com.flxkbr.hunger.logic.IUpdatable;
import com.flxkbr.hunger.logic.LogicMaster;

public class MapScreenMaster implements IUpdatable {
	
	public static final int PRIO = 0;
	
	private static MapScreenMaster master;

	private HexMap currentMap;
	private Player player;
	private Array<Patient> patients;
	
	private Map map;
	
	public static MapScreenMaster get() {
		if (master == null)
			master = new MapScreenMaster();
		return master;
	}
	
	private MapScreenMaster() {
		
	}
	
	/**
	 * calculate what's gonna happen next
	 */
	private void updateLogic() {
		
	}
	
	/**
	 * tell renderMaster that logic has been applied
	 */
	private void renderScreen() {
		
	}
	
	@Override
	public int getPriority() {
		return PRIO;
	}
	
	public void setMap(Map map) {
		this.map = map;
	}
	
	public void setPlayer(Player p) {
		this.player = p;
	}
	
	public void setPatients(Array<Patient> pats) {
		this.patients = pats;
	}

	@Override
	public void update() {
		updateLogic();
		renderScreen();
	}

	@Override
	public void registerSelf() {
		LogicMaster.get().registerUpdatable(this);
	}
	
}
