package com.flxkbr.hunger.grfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.flxkbr.hunger.GlobalConstants;
import com.flxkbr.hunger.load.HRDisposable;
import com.flxkbr.hunger.load.LoadManager;

public class RenderMaster extends HRDisposable {
	
	public static final int RENDERLEVELS = 5;
	
	private static RenderMaster master;
	
	private final float viewPortWidth = GlobalConstants.VIEWPORTWIDTH;
	private float aspectRatio;
	
	private SpriteBatch mainBatch;
	private OrthographicCamera mainCam;
	private Array<Array<IRenderable>> renderPipeline;
	
	private Texture testTex;
	private Sprite testSpr;
	
	private RenderMaster() {
		super();
		mainBatch = new SpriteBatch();
		renderPipeline = new Array<Array<IRenderable>>(5);
		aspectRatio = (float)Gdx.graphics.getHeight()/(float)Gdx.graphics.getWidth();
		mainCam = new OrthographicCamera(viewPortWidth, viewPortWidth*aspectRatio);
		mainCam.position.set(viewPortWidth / 2f, mainCam.viewportHeight / 2f, 0);
		for (int i = 0; i < RENDERLEVELS; ++i) {
			renderPipeline.add(new Array<IRenderable>());
		}
		
		try {
			testTex = LoadManager.get().getTexture("prototype_hex");
			testSpr = new Sprite(testTex, 0, 0, 128, 111);
			float scl = viewPortWidth / (10 * testSpr.getWidth());
			testSpr.setSize(testSpr.getWidth()*scl, testSpr.getHeight()*scl);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static RenderMaster get() {
		if (master==null) {
			master = new RenderMaster();
		}
		return master;
	}
	
	public void registerRenderable(IRenderable rend) {
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
		mainCam.update();
		mainBatch.setProjectionMatrix(mainCam.combined);
		mainBatch.begin();
		for (int i = 0; i < 10; ++i) {
			testSpr.setPosition(0, i*testSpr.getHeight()*0.999f);
			testSpr.draw(mainBatch);
		}
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
	
	public void resize() {
		
	}
}
