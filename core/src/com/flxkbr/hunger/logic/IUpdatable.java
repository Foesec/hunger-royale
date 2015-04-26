package com.flxkbr.hunger.logic;

import com.flxkbr.hunger.connectors.IRegisterable;

public interface IUpdatable extends IRegisterable {
	
	public void update();
	public int getPriority();

}
