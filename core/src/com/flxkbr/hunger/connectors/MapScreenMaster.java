package com.flxkbr.hunger.connectors;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.flxkbr.hunger.connectors.helpers.MapScreenSelection;
import com.flxkbr.hunger.geom.HexMath;
import com.flxkbr.hunger.geom.Hexagon;
import com.flxkbr.hunger.gmobj.Map;
import com.flxkbr.hunger.gmobj.Patient;
import com.flxkbr.hunger.gmobj.Player;
import com.flxkbr.hunger.grfx.MasterRenderable;
import com.flxkbr.hunger.grfx.RenderMaster;
import com.flxkbr.hunger.logic.IUpdatable;
import com.flxkbr.hunger.logic.LogicMaster;

public class MapScreenMaster implements IUpdatable, MasterRenderable {
	
	public static final int PRIO = 0;
	
	private static MapScreenMaster master;

	private Player player;
	private Array<Patient> patients;
	
	private BitmapFont _bmf = new BitmapFont(true);
	
	private Map map;
	private MapScreenSelection selector;
	
	public static MapScreenMaster get() throws Exception {
		if (master == null)
			master = new MapScreenMaster();
		return master;
	}
	
	public static Map getCurrentMap() {
		return master.map;
	}
	
	public static BitmapFont _getBMF() {
		return master._bmf;
	}
	
	private MapScreenMaster() throws Exception {
		_bmf.getData().setScale(RenderMaster.getWorldScale());
		player = Player.get();
		selector = new MapScreenSelection();
	}

	public void init(String mapName) throws Exception {
		this.map = new Map(mapName);
		//Gdx.input.setInputProcessor(new MapScreenInputHandler(this));
	}
	
	private void updateLogic() {
		
	}

	private void renderScreen(SpriteBatch batch) {
		this.map.render(batch);
		this.selector.render(batch);
		this.player.render(batch);
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
	
	public boolean leftClickAtWorld(float x, float y) {
		Vector2 axial = HexMath.worldToAxial(x, y);
		Hexagon selection = map.getHexagonByAxial(axial);
		this.selector.setSelection(selection);
		
		return true;
	}
	
	public boolean rightClickAtWorld(float x, float y) {
		this.selector.deselect();
		return true;
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

	@Override
	public void masterRender(SpriteBatch batch) {
		renderScreen(batch);
	}

	@Override
	public void registerToRenderMaster() {
		// TODO register to renderMaster
	}

	@Override
	public int getPriority() {
		return PRIO;
	}
	
}
