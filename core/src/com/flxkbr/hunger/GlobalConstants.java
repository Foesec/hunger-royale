package com.flxkbr.hunger;

public final class GlobalConstants {
	
	public final class InputConstants {
		public static final float HORIZONTALSCROLLMARGIN = 10f;
		public static final float VERTICALSCROLLMARGIN = 8f;
	}
	
	public final class Settings {
		public static final float CAMERASENSITIVITY = 10f;
	}
	
	public static final float VIEWPORTWIDTH = 100f;

	public static final float SQRT3BY2 = (float) (Math.sqrt(3)/2.0);
	public static final float SQRT3 = (float) Math.sqrt(3);
	public static final float HEXSCALETOWORLD = 0.1f;
	public static final float HEXWIDTH = VIEWPORTWIDTH*HEXSCALETOWORLD;
	public static final float HEXSIZE = HEXWIDTH/2f;
	public static final float HEXSCALE = HEXWIDTH /  128f;
	public static final float AXPIXFACTOR = HEXWIDTH * SQRT3BY2;
	
	public static final String MAPDIR = "maps/";
	public static final String TEXDIR = "textures/";
	public static final String TEXTDIR = "text/";
	

}
