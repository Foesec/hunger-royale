package com.flxkbr.hunger.load;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class DisposeHandler {
	
	public enum DisposeTime {
		INIT, RUNTIME, EXIT;
	}
	
	
	private static DisposeHandler disposer;
	
	private Array<Disposable> disposables;
	private Array<DisposeRequestable> requestables;
	private Array<Disposable> runtimeDisposables;
	private Array<Disposable> afterInitDisposables;
	
	private DisposeHandler() {
		disposables = new Array<Disposable>();
		runtimeDisposables = new Array<Disposable>();
		afterInitDisposables = new Array<Disposable>();
		requestables = new Array<DisposeRequestable>();
	}
	
	public static void init() {
		disposer = new DisposeHandler();
	}
	
	public static boolean initFlag() {
		return (disposer != null);
	}
	
	public static void registerDisposable(Disposable disp) {
		disposer.regDisposable(disp, DisposeTime.EXIT);
	}
	
	public static void registerDisposable(Disposable disp, DisposeTime when) {
		disposer.regDisposable(disp, when);
	}
	
	private void regDisposable(Disposable disp, DisposeTime when) {
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
		disposer.regRequestable(disreq);
	}
	
	private void regRequestable(DisposeRequestable disreq) {
		requestables.add(disreq);
	}
	
	public static void disposeAll() {
		disposer.dispAll();
		requestDisposeAll();
	}
	
	private void dispAll() {
		for (Disposable disp : disposables) {
			disp.dispose();
		}
		for (Disposable disp : runtimeDisposables) {
			disp.dispose();
		}
		for (Disposable disp : afterInitDisposables) {
			disp.dispose();
		}
	}
	
	public static void requestDisposeAll() {
		disposer.reqDisposeAll();
	}
	
	private void reqDisposeAll() {
		for (DisposeRequestable dr : requestables) {
			dr.requestDispose();
		}
	}
}
