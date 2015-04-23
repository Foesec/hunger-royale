package com.flxkbr.hunger.grfx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.flxkbr.hunger.load.DisposeHandler;

public class RenderMaster implements Disposable {
	
	private static RenderMaster master;
	
	private SpriteBatch mainBatch;
	private Array<IRenderable> renderPipeline;
	
	private RenderMaster() {
		mainBatch = new SpriteBatch();
		renderPipeline = new Array<IRenderable>();
		DisposeHandler.registerDisposable(this);
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
	
	public void dispose() {
		for (IRenderable renderUnit : renderPipeline) {
			renderUnit.dispose();
		}
		mainBatch.dispose();
	}

}
