package com.flxkbr.hunger.grfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.flxkbr.hunger.load.DisposeHandler;
import com.flxkbr.hunger.load.HRDisposable;
import com.flxkbr.hunger.logic.IUpdatable;
import com.flxkbr.hunger.logic.LogicMaster;

public class RenderMaster extends HRDisposable {
	
	public static final int RENDERLEVELS = 5;
	
	private static RenderMaster master;
	
	private SpriteBatch mainBatch;
	private Array<Array<IRenderable>> renderPipeline;
	
	private RenderMaster() {
		super();
		mainBatch = new SpriteBatch();
		renderPipeline = new Array<Array<IRenderable>>(5);
		for (int i = 0; i < RENDERLEVELS; ++i) {
			renderPipeline.add(new Array<IRenderable>());
		}
	}
	
	public static RenderMaster get() {
		if (master==null) {
			master = new RenderMaster();
		}
		return master;
	}
	
	public void registerRenderable(int i, IRenderable rend) {
		Gdx.app.log("RENDERMASTER", "Trying to add renderable to renderPipeline - good luck!");
		renderPipeline.get(rend.getPriority()).add(rend);
	}
	
	public boolean deregister(IRenderable dereg) {
		for (int i = 0; i < RENDERLEVELS; ++i) {
			if(renderPipeline.get(i).removeValue(dereg, true))
				return true;
		}
		return false;
	}
	
	public void render() {
		mainBatch.begin();
		for (int i = 0; i < RENDERLEVELS; ++i) {
			for (int j = 0; j < renderPipeline.get(i).size; ++j) {
				renderPipeline.get(i).get(j).render(mainBatch);
			}
		}
		mainBatch.end();
	}
	
	public void dispose() {
/*		for (int i = 0; i < RENDERLEVELS; ++i) {
			for (IRenderable renderUnit : renderPipeline.get(i)) {
				renderUnit.dispose();
			}
		}*/
		mainBatch.dispose();
	}
}
