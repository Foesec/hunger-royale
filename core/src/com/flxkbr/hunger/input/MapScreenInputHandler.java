package com.flxkbr.hunger.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.flxkbr.hunger.connectors.MapScreenMaster;
import com.flxkbr.hunger.geom.HexMap;
import com.flxkbr.hunger.grfx.RenderMaster;

public class MapScreenInputHandler implements InputProcessor {
	
	OrthographicCamera currentCam;
	HexMap currentMap;
	
	public MapScreenInputHandler() {
		currentCam = RenderMaster.getCurrentWorldCam();
		currentMap = MapScreenMaster.getCurrentMap();
	}
	
	public MapScreenInputHandler(OrthographicCamera cam) {
		currentCam = cam;
	}
	
	public void setCamera(OrthographicCamera cam) {
		currentCam = cam;
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Vector3 world = currentCam.unproject(new Vector3(screenX, screenY, 0));
		Gdx.app.log("INPUT - world", world.toString());
		Gdx.app.log("INPUT - axial", MapScreenMaster.getCurrentMap().getByWorld(world.x, world.y).getTerrainType()+"");
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
