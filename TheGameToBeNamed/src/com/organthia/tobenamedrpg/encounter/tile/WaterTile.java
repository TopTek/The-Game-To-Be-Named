package com.organthia.tobenamedrpg.encounter.tile;

import com.organthia.tobenamedrpg.encounter.Encounter;
import com.organthia.tobenamedrpg.graphics.Render;
import com.organthia.tobenamedrpg.graphics.Sprite;

public class WaterTile extends Tile {
	
	private int[] grassTileIndex;

	public WaterTile(Sprite sprite) {
		super(sprite);
	}

	public void render(int x, int y, Render render, Encounter encounter) {
		int w = encounter.getWidth();
		int spriteX = (int) Math.floor(grassTileIndex[x+y*w]/100);
		int spriteY = (int) (grassTileIndex[x+y*w]-(Math.floor(grassTileIndex[x+y*w]/100)*100));
		render.renderTiles(x << 4, y << 4, this, spriteX, spriteY);
	}

	public void initialize(Encounter encounter) {
		grassTileIndex = getTileIndex(encounter, encounter.grassTileIndex);
	}
}
