package com.flxkbr.hunger.testing;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;

public class LogHandler {
	
	private static boolean debFlag = false;
	private static boolean errFlag = false;
	private static int errors = 0;
	private static int debugs = 0;
	
	public enum Priority {
		CRITICAL(0),
		DEFAULT(1),
		INFO(2);
		private final int priority;
		Priority(int i) {
			priority = i;
		}
		
		int priority() {
			return priority;
		}
	}

	static Array<ObjectMap<String, Array<String>>> debugLog;
	static Array<ObjectMap<String, Array<String>>> errorLog;
	
	public static void submitLog(String classTag, String logMsg) {
		submitLog(classTag, logMsg, Priority.DEFAULT);
	}
	
	public static void submitLog(String classTag, String logMsg, Priority prio) {
		if (debugLog == null) {
			initDbg();
			debFlag = true;
		}
		if (!debugLog.get(prio.priority()).containsKey(classTag)) {
			debugLog.get(prio.priority()).put(classTag, new Array<String>());
		}
		debugLog.get(prio.priority()).get(classTag).add(logMsg);
		++debugs;
	}
	
	public static void submitError(String classTag, String errMsg) {
		submitError(classTag, errMsg, Priority.DEFAULT);
	}
	
	public static void submitError(String classTag, String errMsg, Priority prio) {
		if (errorLog == null) {
			initErr();
			errFlag = true;
		}
		if (!errorLog.get(prio.priority()).containsKey(classTag)) {
			errorLog.get(prio.priority()).put(classTag, new Array<String>());
		}
		errorLog.get(prio.priority()).get(classTag).add(errMsg);
		++errors;
	}
	
	public static boolean errFlag() {
		return errFlag;
	}
	
	public static boolean debFlag() {
		return debFlag;
	}
	
	public static int errorCount() {
		return errors;
	}
	
	public static int debugCount() {
		return debugs;
	}
	
	public static void printErrors() {
		if (errFlag) {
			System.out.println("\n__ERROR LOGS__");
			for (int i = 0; i < 3; ++i) {
				System.out.println(">Priority " + i + "<");
				for (String key : errorLog.get(i).keys()){
					System.out.println("\t--"+ key +"--");
					System.out.println("\t\t"+errorLog.get(i).get(key));
				}
			}
		} else { System.out.println("No error messages"); }
	}
	
	public static void printDebugs() {
		if (debFlag) {
			System.out.println("\n__DEBUG LOGS__");
			for (int i = 0; i < 3; ++i) {
				System.out.println(">Priority " + i + "<");
				for (String key : debugLog.get(i).keys()){
					System.out.println("\t--"+key+"--");
					System.out.println("\t\t"+ debugLog.get(i).get(key));
				}
			}
		} else { System.out.println("No debug messages"); }
	}
	
	public static void printAll() {
		printDebugs();
		printErrors();
	}
	
	private static void initDbg() {
		debugLog = new Array<ObjectMap<String,Array<String>>>(3);
		for (int i = 0; i < 3; ++i) {
			debugLog.add(new ObjectMap<String, Array<String>>());
		}
	}
	
	private static void initErr() {
		errorLog = new Array<ObjectMap<String,Array<String>>>(3);
		for (int i = 0; i < 3; ++i) {
			errorLog.add(new ObjectMap<String, Array<String>>());
		}
	}
}
