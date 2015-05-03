package com.flxkbr.hunger.grfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.flxkbr.hunger.GlobalConstants;
import com.flxkbr.hunger.geom.HexMap;
import com.flxkbr.hunger.load.HRDisposable;
import com.flxkbr.hunger.load.LoadManager;

public class RenderMaster extends HRDisposable {
	
	public static final int RENDERLEVELS = 5;
	
	private static RenderMaster master;
	
	private final float viewportWidth = GlobalConstants.VIEWPORTWIDTH;
	private final float viewportHeight;
	private float aspectRatio;
	
	private SpriteBatch mainBatch;
	private OrthographicCamera mainCam;
	private Array<Array<IRenderable>> renderPipeline;
	
	private float camSpeed = GlobalConstants.Settings.CAMERASENSITIVITY;
	
	MapRenderer mr;
	HexMap hm;
	
	private RenderMaster() throws Exception {
		super();
		mainBatch = new SpriteBatch();
		renderPipeline = new Array<Array<IRenderable>>(5);
		aspectRatio = (float)Gdx.graphics.getHeight()/(float)Gdx.graphics.getWidth();
		viewportHeight = viewportWidth * aspectRatio;
		mainCam = new OrthographicCamera(viewportWidth, viewportHeight);
		mainCam.setToOrtho(true, viewportWidth, viewportWidth*aspectRatio);
		mainCam.position.set(viewportWidth / 2f - 5, mainCam.viewportHeight / 2f - 10, 0);
		for (int i = 0; i < RENDERLEVELS; ++i) {
			renderPipeline.add(new Array<IRenderable>());
		}
		
		mr = new MapRenderer();
		hm = new HexMap("test");
		mr.setMap(hm);
	}
	
	public static RenderMaster get() throws Exception {
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
		handleScrolling();
		mainCam.update();
		mainBatch.setProjectionMatrix(mainCam.combined);
		mainBatch.begin();
		mr.render(mainBatch);
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
	
	public static float getScale(float pixelSize, float scaleToWorld) {
		return (GlobalConstants.VIEWPORTWIDTH*scaleToWorld) /  pixelSize;
	}
	
	private void handleScrolling() {
		float mx = (Gdx.input.getX() / (float) Gdx.graphics.getWidth()) * viewportWidth;
		if (mx < GlobalConstants.InputConstants.HORIZONTALSCROLLMARGIN) {
			mainCam.translate(-camSpeed*Gdx.graphics.getDeltaTime(), 0);
		} else if (mx > viewportWidth - GlobalConstants.InputConstants.HORIZONTALSCROLLMARGIN) {
			mainCam.translate(camSpeed*Gdx.graphics.getDeltaTime(), 0);
		}
		float my = (Gdx.input.getY() / (float) Gdx.graphics.getHeight()) * viewportHeight;
		if (my < GlobalConstants.InputConstants.VERTICALSCROLLMARGIN) {
			mainCam.translate(0, -camSpeed*Gdx.graphics.getDeltaTime());
		} else if (my > viewportHeight - GlobalConstants.InputConstants.VERTICALSCROLLMARGIN) {
			mainCam.translate(0, camSpeed*Gdx.graphics.getDeltaTime());
		}
	}
}
