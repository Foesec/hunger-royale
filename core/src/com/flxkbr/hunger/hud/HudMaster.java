package com.flxkbr.hunger.hud;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.flxkbr.hunger.gmobj.Clock;
import com.flxkbr.hunger.grfx.MasterRenderable;
import com.flxkbr.hunger.hud.elements.ActionMenu;
import com.flxkbr.hunger.hud.elements.HudElement;
import com.flxkbr.hunger.load.HRDisposable;

public class HudMaster extends HRDisposable implements MasterRenderable{
	
	private static HudMaster master;
	
	private HudRenderer renderer;
	private Array<HudElement> elements;
	private ActionMenu _am;
	private Clock clock;
	
	private HudMaster() {
		super();
		renderer = HudRenderer.get();
		elements = new Array<HudElement>();
		//elements.add(new ActionMenu());
		_am = new ActionMenu();
		elements.add(_am);
		clock = new Clock();
		elements.add(clock.getHudElement());
	}
	
	public static HudMaster get() {
		if (master == null) {
			master = new HudMaster();
		}
		return master;
	}
	
	public void keyDown(int keyCode) {
		_am.toggle();
	}
	
	public static Array<HudElement> getElements() {
		return master.elements;
	}
	
	public static void incrementClock(int i) {
		master.clock.increment(i);
	}
	
	public static void incrementClock() {
		incrementClock(1);
	}

	@Override
	public void masterRender(SpriteBatch batch) {

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
