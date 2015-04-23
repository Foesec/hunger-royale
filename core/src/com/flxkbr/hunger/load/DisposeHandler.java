package com.flxkbr.hunger.load;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class DisposeHandler {
	
	public enum DisposeTime {
		INIT, RUNTIME, EXIT;
	}
	
	private static boolean initialized = false;
	
	private static Array<Disposable> disposables;
	private static Array<DisposeRequestable> requestables;
	private static Array<Disposable> runtimeDisposables;
	private static Array<Disposable> afterInitDisposables;
	
	public static void init() {
		if (!initialized) {
			disposables = new Array<Disposable>();
			runtimeDisposables = new Array<Disposable>();
			afterInitDisposables = new Array<Disposable>();
			requestables = new Array<DisposeRequestable>();
		}
		initialized = true;
	}

	public static void registerDisposable(Disposable disp) {
		registerDisposable(disp, DisposeTime.EXIT);
	}
	
	public static void registerDisposable(Disposable disp, DisposeTime when) {
		if (!initialized)
			init();
		
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
	
	public static void registerRequestable(DisposeRequestable disreq) {
		if (!initialized)
			init();
		requestables.add(disreq);
	}
	
	public static void disposeAll() {
		if (disposables != null){
			for (Disposable d : disposables) {
				d.dispose();
			}
		}
		requestDisposeAll();
	}
	
	public static void requestDisposeAll() {
		if (requestables != null) {
			for (DisposeRequestable dr : requestables) {
				dr.requestDispose();
			}
		}
	}
}
