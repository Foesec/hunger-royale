package com.flxkbr.hunger.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;

public class InputMaster {
	
	private static InputMaster master;
	
	private InputMultiplexer multiplexer;
	
	private InputMaster() throws Exception {
		multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(HudInputHandler.get());
		multiplexer.addProcessor(new MapScreenInputHandler());
		Gdx.input.setInputProcessor(multiplexer);
	}
	
	public static InputMaster get() throws Exception {
		if (master == null) {
			master = new InputMaster();
		}
		return master;
	}

}
