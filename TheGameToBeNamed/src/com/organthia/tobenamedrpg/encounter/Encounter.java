package com.organthia.tobenamedrpg.encounter;

import java.awt.Color;

import com.organthia.tobenamedrpg.encounter.tile.Tile;
import com.organthia.tobenamedrpg.graphics.Render;
import com.organthia.tobenamedrpg.graphics.RotateImage;
import com.organthia.tobenamedrpg.graphics.Sprite;
import com.organthia.tobenamedrpg.graphics.SpriteSheet;

public class Encounter {

	protected int width, height = 0;
	protected int[] tiles;
	protected int[] grassTileOverlayPixels;

	public int[] grassTileIndex;

	public int grassTileReturn = 1;
	public int waterTileReturn = 2;
	public int lavaTileReturn = 3;

	public SpriteSheet sheet;

	public Encounter(int width, int height) {
		this.width = width;
		this.height = height;
		tiles = new int[width * height];
		generateEncounter();
	}

	public Encounter(int width, int height, SpriteSheet sheet) {
		this.sheet = sheet;
		this.width = width;
		this.height = height;
		tiles = new int[width * height];
		generateEncounter();
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
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
				getTile(x, y).render(x, y, render);
			}
		}
		render.renderTileOverlay(grassTileOverlayPixels);
	}

	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
		if (tiles[x + y * width] == grassTileReturn) return Tile.grassTile;
		if (tiles[x + y * width] == waterTileReturn) return Tile.waterTile;
		if (tiles[x + y * width] == lavaTileReturn) return Tile.lavaTile;

		return Tile.voidTile;
	}

	public int[] generateTileIndex(int[] index, int[] tileContinue, int tileReturn, int w, int h) {
		index = new int[w * h];
		for (int i = 0; i < tileContinue.length; i++) {
			for (int y = 0; y < h; y++) {
				for (int x = 0; x < w; x++) {

					if (tiles[x + y * w] == tileReturn || tiles[x + y * w] != tileContinue[i] || index[x + y * width] == -1) {
						index[x + y * width] = -1;
						continue;
					}
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
		}
		for (int i = 0; i < index.length; i++) {
			if (index[i] == -1) index[i] = 0;
		}
		return index;
	}

	public int[] generateTileOverlay(int[] index, Sprite sprite, int w, int h) {
		int[] tileOverlay = new int[(w << 4) * (h << 4)];
		Color hexMaskC = new Color((0xD15FEE & 0xFF0000) >> 16, (0xD15FEE & 0xFF00) >> 8, (0xD15FEE & 0xFF));
		for (int i = 0; i < tileOverlay.length; i++) {
			tileOverlay[i] = hexMaskC.getRGB();
		}

		boolean cont;
		int[] pix = sprite.pixels;
		int[] pixRight = RotateImage.rotateRight(sprite.pixels, sprite.sheetHeight, sprite.sheetWidth);
		int[] pixHalf = RotateImage.rotateHalf(sprite.pixels, sprite.sheetHeight, sprite.sheetWidth);
		int[] pixLeft = RotateImage.rotateLeft(sprite.pixels, sprite.sheetHeight, sprite.sheetWidth);
		Color color;
		String hex;

		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				cont = false;
				if (index[x + y * w] != 0) {
					for (int t1 = 0; t1 < 2; t1++) {
						if (cont) break;
						for (int t2 = 0; t2 < 2; t2++) {
							if (cont) break;
							for (int t3 = 0; t3 < 2; t3++) {
								if (cont) break;
								for (int t4 = 0; t4 < 2; t4++) {
									if (cont) break;
									for (int t5 = 0; t5 < 2; t5++) {
										if (cont) break;
										for (int t6 = 0; t6 < 2; t6++) {
											if (cont) break;
											for (int t7 = 0; t7 < 2; t7++) {
												if (cont) break;
												for (int t8 = 0; t8 < 2; t8++) {
													if (cont) break;
													if (t1 + 100 * t2 + 2 * t3 + 200 * t4 + 4 * t5 + 400 * t6 + 8 * t7 + 800 * t8 == index[x + y * w]) {
														cont = true;
														for (int ys = 0; ys < sprite.tileHeight; ys++) {
															for (int xs = 0; xs < sprite.tileWidth; xs++) {
																color = new Color(tileOverlay[((x << 4) + xs) + ((y << 4) + ys) * (w << 4)]);
																hex = String.format("%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue()).toUpperCase();
																if (t1 == 1 && "D15FEE".equals(hex)) {
																	tileOverlay[((x << 4) + xs) + ((y << 4) + ys) * (w << 4)] = pix[xs + ys * sprite.sheetWidth];
																	color = new Color(tileOverlay[((x << 4) + xs) + ((y << 4) + ys) * (w << 4)]);
																	hex = String.format("%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue()).toUpperCase();
																}
																if (t2 == 1 && "D15FEE".equals(hex)) {
																	tileOverlay[((x << 4) + xs) + ((y << 4) + ys) * (w << 4)] = pix[(xs + 16) + ys * sprite.sheetWidth];
																	color = new Color(tileOverlay[((x << 4) + xs) + ((y << 4) + ys) * (w << 4)]);
																	hex = String.format("%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue()).toUpperCase();
																}
																if (t3 == 1 && "D15FEE".equals(hex)) {
																	tileOverlay[((x << 4) + xs) + ((y << 4) + ys) * (w << 4)] = pixRight[(xs + 16) + ys * sprite.sheetWidth];
																	color = new Color(tileOverlay[((x << 4) + xs) + ((y << 4) + ys) * (w << 4)]);
																	hex = String.format("%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue()).toUpperCase();
																}
																if (t4 == 1 && "D15FEE".equals(hex)) {
																	tileOverlay[((x << 4) + xs) + ((y << 4) + ys) * (w << 4)] = pixRight[(xs + 16) + (ys + 16) * sprite.sheetWidth];
																	color = new Color(tileOverlay[((x << 4) + xs) + ((y << 4) + ys) * (w << 4)]);
																	hex = String.format("%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue()).toUpperCase();
																}
																if (t5 == 1 && "D15FEE".equals(hex)) {
																	tileOverlay[((x << 4) + xs) + ((y << 4) + ys) * (w << 4)] = pixHalf[(xs + 16) + (ys + 16) * sprite.sheetWidth];
																	color = new Color(tileOverlay[((x << 4) + xs) + ((y << 4) + ys) * (w << 4)]);
																	hex = String.format("%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue()).toUpperCase();
																}
																if (t6 == 1 && "D15FEE".equals(hex)) {
																	tileOverlay[((x << 4) + xs) + ((y << 4) + ys) * (w << 4)] = pixHalf[xs + (ys + 16) * sprite.sheetWidth];
																	color = new Color(tileOverlay[((x << 4) + xs) + ((y << 4) + ys) * (w << 4)]);
																	hex = String.format("%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue()).toUpperCase();
																}
																if (t7 == 1 && "D15FEE".equals(hex)) {
																	tileOverlay[((x << 4) + xs) + ((y << 4) + ys) * (w << 4)] = pixLeft[xs + (ys + 16) * sprite.sheetWidth];
																	color = new Color(tileOverlay[((x << 4) + xs) + ((y << 4) + ys) * (w << 4)]);
																	hex = String.format("%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue()).toUpperCase();
																}
																if (t8 == 1 && "D15FEE".equals(hex)) {
																	tileOverlay[((x << 4) + xs) + ((y << 4) + ys) * (w << 4)] = pixLeft[xs + ys * sprite.sheetWidth];
																	color = new Color(tileOverlay[((x << 4) + xs) + ((y << 4) + ys) * (w << 4)]);
																	hex = String.format("%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue()).toUpperCase();
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}

		for (int i = 0; i < tileOverlay.length; i++) {
			color = new Color(tileOverlay[i]);
			hex = String.format("%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue()).toUpperCase();
			if ("D15FEE".equals(hex)) tileOverlay[i] = 0;
		}

		return tileOverlay;
	}
}