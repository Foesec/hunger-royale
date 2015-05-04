package com.flxkbr.hunger.connectors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.flxkbr.hunger.GlobalConstants;
import com.flxkbr.hunger.geom.HexMap;
import com.flxkbr.hunger.gmobj.Map;
import com.flxkbr.hunger.gmobj.Patient;
import com.flxkbr.hunger.gmobj.Player;
import com.flxkbr.hunger.grfx.MapRenderer;
import com.flxkbr.hunger.grfx.MasterRenderable;
import com.flxkbr.hunger.grfx.RenderMaster;
import com.flxkbr.hunger.input.MapScreenInputHandler;
import com.flxkbr.hunger.logic.IUpdatable;
import com.flxkbr.hunger.logic.LogicMaster;

public class MapScreenMaster implements IUpdatable, MasterRenderable {
	
	public static final int PRIO = 0;
	
	private static MapScreenMaster master;

	private HexMap currentMap;
	private MapRenderer mapRend;
	private Player player;
	private Array<Patient> patients;
	
	private BitmapFont _bmf = new BitmapFont(true);
	
	private Map map;
	
	public static MapScreenMaster get() {
		if (master == null)
			master = new MapScreenMaster();
		return master;
	}
	
	public static HexMap getCurrentMap() {
		return master.currentMap;
	}
	
	public static BitmapFont _getBMF() {
		return master._bmf;
	}
	
	private MapScreenMaster() {
		_bmf.getData().setScale(RenderMaster.getWorldScale());
	}

	@Override
	public void masterRender(SpriteBatch batch) {
		renderScreen(batch);
	}

	@Override
	public void registerToRenderMaster() {
		// TODO register to renderMaster
	}
	
	/**
	 * calculate what's gonna happen next
	 */
	private void updateLogic() {
		
	}
	
	/**
	 * tell renderMaster that logic has been applied
	 */
	private void renderScreen(SpriteBatch batch) {
		this.map.render(batch);
	}
	
	public void init(String mapName) throws Exception {
		this.currentMap = new HexMap(mapName);
		this.mapRend = new MapRenderer();
		this.mapRend.setMap(currentMap);
		this.map = new Map(currentMap, mapRend);
		Gdx.input.setInputProcessor(new MapScreenInputHandler());
	}
	
	@Override
	public int getPriority() {
		return PRIO;
	}
	
	public void setMap(Map map) {
		this.map = map;
	}
	
	public void setPlayer(Player p) {
		this.player = p;
	}
	
	public void setPatients(Array<Patient> pats) {
		this.patients = pats;
	}

	@Override
	public void update() {
		updateLogic();
		//renderScreen();
	}

	@Override
	public void registerSelf() {
		LogicMaster.get().registerUpdatable(this);
	}
	
	public void dispose() {
		_bmf.dispose();
	}
	
}
