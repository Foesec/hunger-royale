package com.flxkbr.hunger.grfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.flxkbr.hunger.GlobalConstants;
import com.flxkbr.hunger.connectors.MapScreenMaster;
import com.flxkbr.hunger.hud.HudMaster;
import com.flxkbr.hunger.load.HRDisposable;

public class RenderMaster extends HRDisposable {
	
	public static final int RENDERLEVELS = 5;
	
	private static float WORLDSCALE;
	private static RenderMaster master;
	
	private final float viewportWidth = GlobalConstants.VIEWPORTWIDTH;
	private final float viewportHeight;
	private float aspectRatio;
	
	private SpriteBatch mainBatch;
	private OrthographicCamera mainCam;
	private OrthographicCamera hudCam;
	private Array<Array<IRenderable>> renderPipeline;
	
	private float camSpeed = GlobalConstants.Settings.CAMERASENSITIVITY;
	
	private MapScreenMaster msmaster;
	private HudMaster hudmaster;
	
	private BitmapFont font = new BitmapFont(true);

	
	private RenderMaster() throws Exception {
		super();
		mainBatch = new SpriteBatch();
		renderPipeline = new Array<Array<IRenderable>>(5);
		aspectRatio = (float)Gdx.graphics.getHeight()/(float)Gdx.graphics.getWidth();
		viewportHeight = viewportWidth * aspectRatio;
		mainCam = new OrthographicCamera(viewportWidth, viewportHeight);
		mainCam.setToOrtho(true, viewportWidth, viewportWidth*aspectRatio);
		mainCam.position.set(viewportWidth / 2f - 5, mainCam.viewportHeight / 2f - 10, 0);
		
		hudCam = new OrthographicCamera(viewportWidth, viewportHeight);
		hudCam.setToOrtho(true, viewportWidth, viewportWidth*aspectRatio);
		//hudCam.position.set(viewportWidth / 2f - 5, mainCam.viewportHeight / 2f - 10, 0);
		
		for (int i = 0; i < RENDERLEVELS; ++i) {
			renderPipeline.add(new Array<IRenderable>());
		}
		WORLDSCALE = viewportWidth / (float)Gdx.graphics.getWidth();
		Gdx.app.log("WORLDSCALE", WORLDSCALE+"");
		font.getData().setScale(WORLDSCALE);
	}
	
	public static float getWorldScale() {
		return WORLDSCALE;
	}
	
	public static RenderMaster get() throws Exception {
		if (master==null) {
			master = new RenderMaster();
			master.msmaster = MapScreenMaster.get();
			master.msmaster.init("test");
			master.hudmaster = HudMaster.get();
		}
		return master;
	}
	
	public static OrthographicCamera getCurrentWorldCam() {
		return master.mainCam;
	}
	
	public static BitmapFont getMasterFont() {
		return master.font;
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
		msmaster.masterRender(mainBatch);
		mainBatch.end();
		
		hudCam.update();
		mainBatch.setProjectionMatrix(hudCam.combined);
		mainBatch.begin();
		hudmaster.masterRender(mainBatch);
		mainBatch.end();
	}
	
	public void dispose() {
/*		for (int i = 0; i < RENDERLEVELS; ++i) {
			for (IRenderable renderUnit : renderPipeline.get(i)) {
				renderUnit.dispose();
			}
		}*/
		mainBatch.dispose();
		msmaster.dispose();
		font.dispose();
	}
	
	public void resize() {
		
	}
	
	public static float getScale(float pixelSize, float scaleToWorld) {
		return (GlobalConstants.VIEWPORTWIDTH*scaleToWorld) /  pixelSize;
	}
	
	private void handleScrolling() {
		Vector2 transl = new Vector2();
		if (Gdx.input.isKeyPressed(Keys.A)) {
			if (Gdx.input.isKeyPressed(Keys.D)) {
				
			} else {
				transl.x = -camSpeed;
			}
		} else if (Gdx.input.isKeyPressed(Keys.D)) {
			transl.x = camSpeed;
		}
		if (Gdx.input.isKeyPressed(Keys.W)) {
			if (Gdx.input.isKeyPressed(Keys.S)) {
				
			} else {
				transl.y = -camSpeed;
			}
		} else if (Gdx.input.isKeyPressed(Keys.S)) {
			transl.y = camSpeed;
		}
		transl.scl(Gdx.graphics.getDeltaTime());
		mainCam.translate(transl);
	}
}
