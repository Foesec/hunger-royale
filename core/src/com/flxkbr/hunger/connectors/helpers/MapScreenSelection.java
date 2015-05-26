package com.flxkbr.hunger.connectors.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.flxkbr.hunger.GlobalConstants;
import com.flxkbr.hunger.geom.HexMath;
import com.flxkbr.hunger.geom.Hexagon;
import com.flxkbr.hunger.load.LoadManager;

public class MapScreenSelection {
	
	private Sprite selector;
	private Hexagon selection;

	private boolean selected;
	

	public MapScreenSelection() throws Exception {
		selector = new Sprite(LoadManager.get().getTexture("prototype_hex.png"), 3*128, 0, 128, 111);
		selector.setSize(selector.getWidth()*GlobalConstants.SPRITESCALE, selector.getHeight()*GlobalConstants.SPRITESCALE);
		selector.setColor(0.1f, 0.8f, 0.9f, 0.5f);
		
		selected = false;
	}
	
	public void setSelection(Hexagon selection) {
		this.selection = selection;
		this.selected = true;
		this.selector.setCenter(selection.getCenter().x, selection.getCenter().y);
	}
	
//	public void setSelectionLine(Vector2 selection, Array<Vector2> axialline) {
//		Vector2 world = HexMath.axialToWorld(selection);
//		selector.setCenter(world.x, world.y);
//		Gdx.app.log("MSSelection", "Selection set at " + world);
//		
//		int newsize = axialline.size;
//		int present = path.size;
//		int diff = newsize - present;
//		/*
//		 * there are diff hexagons too many
//		 */
//		if (diff <= 0) {
//			Gdx.app.log("MSS", "Less or same hexagons than before " + diff);
//			for (int i = 0; i < newsize; ++i) {
//				Vector2 w = HexMath.axialToWorld(axialline.get(i));
//				path.get(i).setCenter(w.x, w.y);
//			}
//			if (diff < 0) {
//				path.removeRange(newsize, present-1);
//			}
//		}
//		/*
//		 * there are diff hexagons too few
//		 */
//		else {
//			Gdx.app.log("MSS", "More hexagons than before " + diff);
//			for (int i = 0; i < present; ++i) {
//				Vector2 w = HexMath.axialToWorld(axialline.get(i));
//				path.get(i).setCenter(w.x, w.y);
//			}
//			for (int i = present; i < newsize; ++i) {
//				Sprite p = new Sprite(LoadManager.get().getTexture("prototype_hex.png"), 2*128, 0, 128, 111);
//				p.setSize(selector.getWidth(), selector.getHeight());
//				p.setColor(0.1f, 0.4f, 0.3f, 0.5f);
//				
//				Vector2 w = HexMath.axialToWorld(axialline.get(i));
//				p.setCenter(w.x, w.y);
//				path.add(p);
//			}
//		}
//		
//		this.selected = true;
//		
//	}
	
	public void deselect() {
		this.selection = null;
		this.selected = false;
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	public void render(SpriteBatch batch) {
		if (selected) {
			selector.draw(batch);
		}
	}
	
}
