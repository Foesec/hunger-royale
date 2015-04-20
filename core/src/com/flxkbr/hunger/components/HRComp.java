package com.flxkbr.hunger.components;

/***
 * 
 * @author Felix
 *	abstract parent interface for all components
 */
public abstract interface HRComp {
	
	// defines type of component
	// prop only has fields
	// behavior also defines methods
	public enum CompType{
		PROPERTIES,
		BEHAVIOR
	}
	
	// returns type of component
	public CompType getType();
}