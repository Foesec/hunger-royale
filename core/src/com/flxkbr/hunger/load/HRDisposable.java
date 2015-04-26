package com.flxkbr.hunger.load;

import com.badlogic.gdx.utils.Disposable;

public abstract class HRDisposable implements Disposable {
	
	protected HRDisposable() {
		registerAsDisposable();
	}
	
	private void registerAsDisposable() {
		DisposeHandler.get().registerDisposable(this);
	}

	protected void deregisterSelf() {
		
	}
	
}
