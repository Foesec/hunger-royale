package com.flxkbr.hunger;

public final class GlobalConstants {
	
	public final class InputConstants {
		public static final float HORSCRMARGIN_PERC = 0.1f;
		public static final float HORIZONTALSCROLLMARGIN = HORSCRMARGIN_PERC * VIEWPORTWIDTH;
		public static final float VERTSCRMARGIN_PERC = 0.1f;
		public static final float VERTICALSCROLLMARGIN = VERTSCRMARGIN_PERC * VIEWPORTHEIGHT;
	}
	
	public final class Settings {
		private static final float SENS = 10f;
		public static final float CAMERASENSITIVITY = (SENS / 100f) *VIEWPORTWIDTH;
	}
	
	public static final float VIEWPORTWIDTH = 1920f;
	public static final float VIEWPORTHEIGHT = 1080f;

	public static final float SQRT3BY2 = (float) (Math.sqrt(3)/2.0);
	public static final float SQRT3 = (float) Math.sqrt(3);
	public static final float HEXTOWORLD = 0.1f;
	public static final float HEXWIDTH = VIEWPORTWIDTH*HEXTOWORLD;
	public static final float HEXHEIGHT = SQRT3BY2 * HEXWIDTH;
	public static final float HEXSIZE = HEXWIDTH/2f;
	public static final float SPRITESCALE = HEXWIDTH /  128f;
	public static final float AXPIXFACTOR = HEXWIDTH * SQRT3BY2;
	
	public static final String MAPDIR = "maps/";
	public static final String TEXDIR = "textures/";
	public static final String TEXTDIR = "text/";
	

}
