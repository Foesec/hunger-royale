package com.flxkbr.hunger.testing.tests;

import com.flxkbr.hunger.testing.LogHandler;

public class LogHandlerTest implements RoyalTest {

	@Override
	public void init() {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean run() {
		LogHandler.submitLog("DUMMY", "is a dumb person. guyse");
		LogHandler.submitError("SHITEST", "srlysly tho guiz, no good");
		LogHandler.printAll();
		return true;
	}

	@Override
	public void cleanup() {
		// TODO Auto-generated method stub
		
	}

}
