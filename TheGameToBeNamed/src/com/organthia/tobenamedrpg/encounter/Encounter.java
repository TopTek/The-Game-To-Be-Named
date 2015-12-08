package com.organthia.tobenamedrpg.encounter;

import com.organthia.tobenamedrpg.encounter.tile.Tile;
import com.organthia.tobenamedrpg.graphics.Render;

public class Encounter {

	protected int width, height;
	protected int[] tiles;
	public int[] grassTileIndex;

	public int grassTileReturn = 1;
	public int waterTileReturn = 2;
	public int lavaTileReturn = 3;

	public Encounter(int width, int height) {
		this.width = width;
		this.height = height;
		tiles = new int[width * height];
		generateEncounter();
	}
	
	public int getWidth(){
		return this.width;
	}
	
	public int getHeight(){
		return this.height;
	}

	public Encounter(String path) {
		loadEncounter(path);
	}

	public Encounter getEncounter() {
		return this;
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
				if(getTile(x,y)==Tile.waterTile){
					getTile(x, y).render(x, y, render, this);
				}else{
					getTile(x, y).render(x, y, render);
				}
			}
		}
	}

	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
		if (tiles[x + y * width] == grassTileReturn) return Tile.grassTile;
		if (tiles[x + y * width] == waterTileReturn) return Tile.waterTile;
		if (tiles[x + y * width] == lavaTileReturn) return Tile.lavaTile;

		return Tile.voidTile;
	}

	public int[] generateTileIndex(int[] index, int tileReturn, int w, int h) {
		index = new int[w * h];
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {

				if (tiles[x + y * w] == tileReturn) continue;
				int total = 0;
				for (int yy = 0; yy < 3; yy++) {
					for (int xx = 0; xx < 3; xx++) {
						if ((x + xx - 1) < 0 || (x + xx - 1) >= w || (y + yy - 1) < 0 || (y + yy - 1) >= h) continue;
						if (tiles[(x + xx - 1) + (y + yy - 1) * w] == tileReturn) {
							if (xx == 0 && yy == 0) total += 1;
							if (xx == 1 && yy == 0) total += 100;
							if (xx == 2 && yy == 0) total += 2;
							if (xx == 0 && yy == 1) total += 800;
							if (xx == 1 && yy == 1) total += 0;
							if (xx == 2 && yy == 1) total += 200;
							if (xx == 0 && yy == 2) total += 8;
							if (xx == 1 && yy == 2) total += 400;
							if (xx == 2 && yy == 2) total += 4;
						}
					}
				}
				index[x + y * width] = total;
			}
		}
		return index;
	}
}
