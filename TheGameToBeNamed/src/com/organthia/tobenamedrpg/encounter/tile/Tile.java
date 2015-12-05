package com.organthia.tobenamedrpg.encounter.tile;

import com.organthia.tobenamedrpg.graphics.Render;
import com.organthia.tobenamedrpg.graphics.Sprite;
import com.organthia.tobenamedrpg.graphics.SpriteSheet;

public class Tile {

	public int x, y;
	public Sprite sprite;

	public static Tile voidTile = new VoidTile(Sprite.voidSprite);
	public static Tile grassTile = new GrassTile(Sprite.grassTile);
	public static Tile waterTile = new WaterTile(Sprite.waterTile);
	public static Tile lavaTile = new LavaTile(Sprite.lavaTile);
	
	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}

	public void render(int x, int y, Render render) {

	}

	public boolean solid() {
		return false;
	}
}
/*
How to make a tile 101

first, write this in Tile.java:

public static Tile [name]Tile = new [Name]Tile(Sprite.[name]Sprite);

-----------------------------------------------------------------------------
Next create the [Name]Tile class and write this code

package com.organthia.tobenamedrpg.encounter.tile;

import com.organthia.tobenamedrpg.graphics.Render;
import com.organthia.tobenamedrpg.graphics.Sprite;

public class [Name]Tile extends Tile {

	public [Name]Tile(Sprite sprite) {
		super(sprite);
	}

	public void render(int x, int y, Render render) {
		render.renderTile(x<<4, y<<4, this);
	}

}

-----------------------------------------------------------------------------
Next create the Sprite in the Sprite.java class with this:

public static Sprite [name] = new Sprite([size], [x], [y], SpriteSheet.tiles);

NOTE: x AND y START AT 0, NOT 1

and you are done as long as there is an image of a tile
to make it appear, add this in the getTile method before the final return line (the default return):

if (tiles[x + y * width] == [tile ID]) return Tile.[name];

Lastly, go into RandomEncounter and change the Random.nextInt([something]) + 1; to include the tile ID you gave your tile
*/