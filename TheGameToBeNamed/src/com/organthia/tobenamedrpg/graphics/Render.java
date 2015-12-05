package com.organthia.tobenamedrpg.graphics;

import java.util.Random;

import com.organthia.tobenamedrpg.encounter.tile.Tile;

public class Render {

	public int width, height;
	public int[] pixels;

	public int mapSize = 64;
	public int tileSize = 16;
	public int bitwise = (int) (Math.log10(tileSize) / Math.log10(2));

	public int xOffset, yOffset;

	private Random random = new Random();

	public Render(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	public void renderTile(int xp, int yp, Tile tile) {
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < tile.sprite.SIZE; y++) {
			int ya = y + yp;
			for (int x = 0; x < tile.sprite.SIZE; x++) {
				int xa = x + xp;
				if (xa < -tile.sprite.SIZE || xa >= width || ya < 0 || ya >= height) break;
				if (xa < 0) xa=0;
				pixels[xa + ya * width] = tile.sprite.pixels[x + y * tile.sprite.SIZE];
			}

		}
	}

	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
}
