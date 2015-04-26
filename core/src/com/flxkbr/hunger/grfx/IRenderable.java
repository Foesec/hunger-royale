package com.flxkbr.hunger.grfx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flxkbr.hunger.connectors.IRegisterable;

public interface IRenderable extends IRegisterable {

	public void render(SpriteBatch batch);
	public boolean isActive();
	
}
