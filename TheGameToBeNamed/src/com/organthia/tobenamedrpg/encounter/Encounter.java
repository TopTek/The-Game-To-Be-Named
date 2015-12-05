package com.organthia.tobenamedrpg.encounter;

import com.organthia.tobenamedrpg.encounter.tile.Tile;
import com.organthia.tobenamedrpg.graphics.Render;

public class Encounter {

	protected int width, height;
	protected int[] tiles;

	public Encounter(int width, int height) {
		this.width = width;
		this.height = height;
		tiles = new int[width * height];
		generateEncounter();
	}

	public Encounter(String path) {
		loadEncounter(path);
	}

	protected void generateEncounter() {

	}

	private void loadEncounter(String path) {

	}

	public void update() {

	}

	private void time() {

	}

	public void render(int xScroll, int yScroll, Render render) {
		render.setOffset(xScroll, yScroll);
		int x0 = xScroll >> 4;
		int x1 = (xScroll + render.width + 16) >> 4;
		int y0 = yScroll >> 4;
		int y1 = (yScroll + render.height + 16) >> 4;

		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, render);
			}
		}
	}

	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
		if (tiles[x + y * width] == 1) return Tile.grassTile;
		if (tiles[x + y * width] == 2) return Tile.waterTile;
		if (tiles[x + y * width] == 3) return Tile.lavaTile;
		
		return Tile.voidTile;
	}
}
