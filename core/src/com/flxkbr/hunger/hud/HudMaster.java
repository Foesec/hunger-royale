package com.flxkbr.hunger.hud;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.flxkbr.hunger.grfx.MasterRenderable;
import com.flxkbr.hunger.hud.elements.HudElement;
import com.flxkbr.hunger.load.HRDisposable;

public class HudMaster extends HRDisposable implements MasterRenderable{
	
	private static HudMaster master;
	
	private HudRenderer renderer;
	private Array<HudElement> elements;
	
	private HudMaster() {
		super();
		renderer = HudRenderer.get();
		elements = new Array<HudElement>();
	}
	
	public static HudMaster get() {
		if (master == null) {
			master = new HudMaster();
		}
		return master;
	}
	
	public static Array<HudElement> getElements() {
		return master.elements;
	}

	@Override
	public void masterRender(SpriteBatch batch) {
		// TODO Auto-generated method stub
		renderer.render(batch);
	}

	@Override
	public void registerToRenderMaster() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	}
	
}
