package com.flxkbr.hunger.connectors;

import com.flxkbr.hunger.grfx.RenderMaster;
import com.flxkbr.hunger.input.InputMaster;
import com.flxkbr.hunger.load.DisposeHandler;
import com.flxkbr.hunger.load.LoadManager;
import com.flxkbr.hunger.logic.LogicMaster;

public class Initializer {
	
	private static Initializer instance;
	
	private RenderMaster renderer;
	private LogicMaster updater;
	private DisposeHandler cleaner;
	private LoadManager loader;
	
	private Initializer() {
		
	}
	
	public static Initializer get() {
		if (instance == null) {
			instance = new Initializer();
		}
		return instance;
	}
	
	public Initializer initialize() throws Exception {
		try {
			renderer = RenderMaster.get();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		updater = LogicMaster.get();
		cleaner = DisposeHandler.get();
		loader = LoadManager.get();
		InputMaster.get();
		return this;
	}

	public RenderMaster getRenderer() {
		return renderer;
	}

	public LogicMaster getUpdater() {
		return updater;
	}

	public DisposeHandler getCleaner() {
		return cleaner;
	}

	public LoadManager getLoader() {
		return loader;
	}
	
	

}
