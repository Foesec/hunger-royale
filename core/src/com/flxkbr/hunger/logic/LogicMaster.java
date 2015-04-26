package com.flxkbr.hunger.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.flxkbr.hunger.connectors.IRegisterable;


public class LogicMaster {
	
	public static final int LOGICLEVELS = 5;

	private static LogicMaster master;
	
	private Array<Array<IUpdatable>> updatePipeline;
	
	private LogicMaster() {
		updatePipeline = new Array<Array<IUpdatable>>(LOGICLEVELS);
		for (int i = 0; i < LOGICLEVELS; ++i) {
			updatePipeline.add(new Array<IUpdatable>());
		}
		Gdx.app.log("LogicMaster", "initialized, updatePipeline is " + updatePipeline.size);
	}
	
	public static LogicMaster get() {
		if (master==null) {
			master = new LogicMaster();
		}
		return master;
	}
	
	public void update() {
		for (int i = 0; i < LOGICLEVELS; ++i) {
			for (int j = 0; j < updatePipeline.get(i).size; ++j) {
				updatePipeline.get(i).get(j).update();
			}
		}
	}
	
	public void registerUpdatable(IUpdatable upd) {
		updatePipeline.get(upd.getPriority()).add(upd);
	}

	public void deregister(IRegisterable registered) {
		
	}
}
