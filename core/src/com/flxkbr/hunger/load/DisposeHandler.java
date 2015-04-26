package com.flxkbr.hunger.load;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class DisposeHandler {
	
	public static final String TAG = "DisposeHandler";
	
	public enum DisposeTime {
		INIT, RUNTIME, EXIT;
	}
	
	private static DisposeHandler disposer;
	
	private Array<HRDisposable> disposables;
	private Array<DisposeRequestable> requestables;
	private Array<HRDisposable> runtimeDisposables;
	private Array<HRDisposable> afterInitDisposables;
	
	private DisposeHandler() {
		disposables = new Array<HRDisposable>();
		runtimeDisposables = new Array<HRDisposable>();
		afterInitDisposables = new Array<HRDisposable>();
		requestables = new Array<DisposeRequestable>();
	}
	
	public static DisposeHandler get() {
		if (disposer == null) {
			disposer = new DisposeHandler();
		}
		return disposer;
	}
	
	public void registerDisposable(HRDisposable disp) {
		registerDisposable(disp, DisposeTime.EXIT);
	}
	
	public void registerDisposable(HRDisposable disp, DisposeTime when) {
		switch (when) {
		case EXIT:
			disposables.add(disp);
			break;
		case RUNTIME:
			runtimeDisposables.add(disp);
			break;
		case INIT:
			afterInitDisposables.add(disp);
			break;
		}
	}

	
	public void registerRequestable(DisposeRequestable disreq) {
		requestables.add(disreq);
	}
	
	public void disposeAll() {
		requestDisposeAll();
		for (Disposable disp : disposables) {
			Gdx.app.log(TAG, disp.toString());
			disp.dispose();
		}
		for (Disposable disp : runtimeDisposables) {
			Gdx.app.log(TAG, disp.toString());
			disp.dispose();
		}
		for (Disposable disp : afterInitDisposables) {
			Gdx.app.log(TAG, disp.toString());
			disp.dispose();
		}
	}
	
	public void requestDisposeAll() {
		for (DisposeRequestable dr : requestables) {
			dr.requestDispose();
		}
	}
}
