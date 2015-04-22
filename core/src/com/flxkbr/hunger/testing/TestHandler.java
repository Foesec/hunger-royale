package com.flxkbr.hunger.testing;

import com.badlogic.gdx.utils.Array;
import com.flxkbr.hunger.testing.tests.RoyalTest;

public class TestHandler {
	
	private Array<RoyalTest> tests;
	
	public TestHandler() {
		tests = new Array<RoyalTest>();
	}
	
	public void submitTest(RoyalTest test) {
		tests.add(test);
	}
	
	public void run() {
		for (RoyalTest test : tests) {
			test.init();
			test.run();
			test.cleanup();
		}
	}
	

}
