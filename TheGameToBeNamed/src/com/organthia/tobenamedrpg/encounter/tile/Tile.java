package com.organthia.tobenamedrpg.encounter.tile;

import com.organthia.tobenamedrpg.encounter.Encounter;
import com.organthia.tobenamedrpg.graphics.Render;
import com.organthia.tobenamedrpg.graphics.Sprite;

public class Tile {

	public int x, y;
	public Sprite sprite;
	public int encounterSize;

	// Tiles
	public static Tile voidTile = new VoidTile(Sprite.voidSprite);
	public static Tile grassTile = new GrassTile(Sprite.grassTile);
	public static Tile waterTile = new WaterTile(Sprite.waterTile);
	public static Tile lavaTile = new LavaTile(Sprite.lavaTile);

	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}

	public void render(int x, int y, Render render) {

	}
	
	public void render(int x, int y, Render render, Encounter encounter){
		encounterSize=encounter.getWidth()*encounter.getHeight();
	}

	public void initialize(Encounter encounter) {

	}

	public void update() {

	}

	public boolean solid() {
		return false;
	}

	public int[] getTileIndex(Encounter encounter, int[] encounterIndex) {
		int[] returnIndex = new int[encounter.getWidth()*encounter.getHeight()];
		for (int yp = 0; yp < encounter.getHeight(); yp++) {
			for (int xp = 0; xp < encounter.getWidth(); xp++) {
				int index = encounterIndex[xp + yp * encounter.getWidth()];
				int iteration = 0;
				for (int t1 = 0; t1 < 2; t1++) {
					for (int t2 = 0; t2 < 2; t2++) {
						for (int t3 = 0; t3 < 2; t3++) {
							for (int t4 = 0; t4 < 2; t4++) {
								for (int t5 = 0; t5 < 2; t5++) {
									for (int t6 = 0; t6 < 2; t6++) {
										for (int t7 = 0; t7 < 2; t7++) {
											for (int t8 = 0; t8 < 2; t8++) {
												if (t1 + 100 * t2 + 2 * t3 + 200 * t4 + 4 * t5 + 400 * t6 + 8 * t7 + 800 * t8 == index) {
													if (iteration == 0) returnIndex[xp+yp*encounter.getWidth()]=0;
													for (int pixY = 0; pixY < 64; pixY++) {
														for (int pixX = 0; pixX < 4; pixX++) {
															if (pixX + pixY * 4 == iteration) {
																returnIndex[xp+yp*encounter.getWidth()]=pixX*100+pixY;
															}
														}
													}
												}
												iteration++;
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
		return returnIndex;
	}
}
/*
 * How to make a tile 101
 * 
 * first, write this in Tile.java:
 * 
 * public static Tile [name]Tile = new [Name]Tile(Sprite.[name]Sprite);
 * 
 * -----------------------------------------------------------------------------
 * Next create the [Name]Tile class and write this code
 * 
 * package com.organthia.tobenamedrpg.encounter.tile;
 * 
 * import com.organthia.tobenamedrpg.graphics.Render; import
 * com.organthia.tobenamedrpg.graphics.Sprite;
 * 
 * public class [Name]Tile extends Tile {
 * 
 * public [Name]Tile(Sprite sprite) { super(sprite); }
 * 
 * public void render(int x, int y, Render render) { render.renderTile(x<<4,
 * y<<4, this); }
 * 
 * }
 * 
 * -----------------------------------------------------------------------------
 * Next create the Sprite in the Sprite.java class with this:
 * 
 * public static Sprite [name] = new Sprite([size], [x], [y],
 * SpriteSheet.tiles);
 * 
 * NOTE: x AND y START AT 0, NOT 1
 * 
 * and you are done as long as there is an image of a tile to make it appear,
 * add this in the getTile method before the final return line (the default
 * return):
 * 
 * if (tiles[x + y * width] == [tile ID]) return Tile.[name];
 * 
 * Lastly, go into RandomEncounter and change the Random.nextInt([something]) +
 * 1; to include the tile ID you gave your tile
 */