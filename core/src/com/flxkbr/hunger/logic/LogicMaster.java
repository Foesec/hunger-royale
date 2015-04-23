package com.flxkbr.hunger.logic;

import com.badlogic.gdx.utils.Array;


public class LogicMaster {

	private static LogicMaster master;
	
	private Array<IUpdatable> updatePipeline;
	
	private LogicMaster() {
		updatePipeline = new Array<IUpdatable>();
	}
	
	public static LogicMaster getLogicMaster() {
		if (master==null) {
			master = new LogicMaster();
		}
		return master;
	}
	
	public void update() {
		for (IUpdatable updateUnit : updatePipeline) {
			updateUnit.update();
		}
	}
}
