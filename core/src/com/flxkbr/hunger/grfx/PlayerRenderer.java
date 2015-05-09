package com.flxkbr.hunger.grfx;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flxkbr.hunger.GlobalConstants;
import com.flxkbr.hunger.gmobj.Player;
import com.flxkbr.hunger.load.HRDisposable;
import com.flxkbr.hunger.load.LoadManager;

public class PlayerRenderer extends HRDisposable implements IRenderable {
	
	private static PlayerRenderer instance;
	
	private Sprite spr;
	private Player player;
	private boolean active = true;
	
	private PlayerRenderer() throws Exception {
		super();
		spr = new Sprite(LoadManager.get().getTexture("prototype_gons.png"), 0, 0, 64, 64);
		spr.setSize(spr.getWidth()*GlobalConstants.SPRITESCALE, spr.getHeight()*GlobalConstants.SPRITESCALE);
	}
	
	public static PlayerRenderer get() throws Exception {
		if (instance == null) {
			instance = new PlayerRenderer();
		}
		return instance;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}

	@Override
	public void registerSelf() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getPriority() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void render(SpriteBatch batch) {
		spr.setCenter(player.getX(), player.getY());
		spr.draw(batch);
	}

	@Override
	public boolean isActive() {
		return active;
	}

	@Override
	public void dispose() {
		// TODO: dispose?
	}
	
	
}
