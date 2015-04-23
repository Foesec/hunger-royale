package com.flxkbr.hunger.grfx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class RenderMaster {
	
	private static RenderMaster master;
	
	private SpriteBatch mainBatch;
	private Array<IRenderable> renderPipeline;
	
	private RenderMaster() {
		mainBatch = new SpriteBatch();
		renderPipeline = new Array<IRenderable>();
	}
	
	public static RenderMaster getRenderMaster() {
		if (master==null) {
			master = new RenderMaster();
		}
		return master;
	}
	
	public void render() {
		mainBatch.begin();
		for (IRenderable renderUnit : renderPipeline) {
			renderUnit.render(mainBatch);
		}
		mainBatch.end();
	}

}
