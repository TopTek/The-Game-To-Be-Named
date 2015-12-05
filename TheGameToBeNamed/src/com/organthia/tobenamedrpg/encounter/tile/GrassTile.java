package com.organthia.tobenamedrpg.encounter.tile;

import com.organthia.tobenamedrpg.graphics.Render;
import com.organthia.tobenamedrpg.graphics.Sprite;

public class GrassTile extends Tile {

	public GrassTile(Sprite sprite) {
		super(sprite);
	}

	public void render(int x, int y, Render render) {
		render.renderTile(x<<4, y<<4, this);
	}

}
