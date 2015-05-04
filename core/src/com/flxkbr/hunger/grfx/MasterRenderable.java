package com.flxkbr.hunger.grfx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/***
 * 
 * @author Felix
 *	the top level renderable instances, only these should be registered to the RenderMaster.
 *	they pass rendering commands on to lower lying renderables.
 */
public interface MasterRenderable {

	public void masterRender(SpriteBatch batch);
	public void registerToRenderMaster();

}
