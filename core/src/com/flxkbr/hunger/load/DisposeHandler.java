package com.flxkbr.hunger.load;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

public class DisposeHandler {
	
	private static Array<Disposable> disposables;
	private static Array<DisposeRequestable> requestables;

	public static void registerDisposable(Disposable disp) {
		if (disposables == null) {
			disposables = new Array<Disposable>();
		}
		disposables.add(disp);
	}
	
	public static void registerRequestable(DisposeRequestable disreq) {
		if (requestables == null) {
			requestables = new Array<DisposeRequestable>();
		}
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
