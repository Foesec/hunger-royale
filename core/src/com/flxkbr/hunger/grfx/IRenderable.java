package com.flxkbr.hunger.grfx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

public interface IRenderable extends Disposable {

	public void render(SpriteBatch batch);
}
