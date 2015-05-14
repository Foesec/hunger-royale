package com.flxkbr.hunger.connectors.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.flxkbr.hunger.GlobalConstants;
import com.flxkbr.hunger.geom.Hexagon;
import com.flxkbr.hunger.load.LoadManager;

public class MapScreenSelection {
	
	private Sprite selector;
	private Hexagon selection;
	private Array<Hexagon> selectionLine;
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
	
	public void setSelectionLine(Hexagon selection, Array<Hexagon> line) {
		this.selection = selection;
		this.selectionLine = line;
		this.selected = true;
		
	}
	
	public void deselect() {
		this.selection = null;
		this.selected = false;
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	public Hexagon getSelection() {
		return selection;
	}
	
	public void render(SpriteBatch batch) {
		if (selected) {
			if (selectionLine != null) {
				for (Hexagon hex : selectionLine) {
					selector.setCenter(hex.getCenter().x, hex.getCenter().y);
					selector.draw(batch);
				}
				selector.setCenter(selection.getCenter().x, selection.getCenter().y);
			}
			selector.draw(batch);
		}
	}
	
}
