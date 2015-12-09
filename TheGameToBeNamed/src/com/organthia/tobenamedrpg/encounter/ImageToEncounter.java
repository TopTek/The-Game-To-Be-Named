package com.organthia.tobenamedrpg.encounter;

import com.organthia.tobenamedrpg.graphics.SpriteSheet;

public class ImageToEncounter extends Encounter{
	
	public ImageToEncounter(int width, int height, SpriteSheet sheet) {
		super(width, height, sheet);
		System.out.println(sheet.HEIGHT);
	}

	protected void generateEncounter() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (sheet.pixels[x + y * width] == 0x0000FF)tiles[x + y * width] = this.waterTileReturn;
				if (sheet.pixels[x + y * width] == 0x00FF00)tiles[x + y * width] = this.grassTileReturn;
				if (sheet.pixels[x + y * width] == 0xFF0000)tiles[x + y * width] = this.lavaTileReturn;
				if (sheet.pixels[x + y * width] == 0xD15FEE)tiles[x + y * width] = 0;
			}
		}
		
		this.grassTileIndex = generateTileIndex(grassTileIndex, grassTileReturn, this.width, this.height);
		
		for(int y = 0; y< height; y++){
			for(int x = 0; x< width; x++){
				getTile(x,y).initialize(getEncounter());
			}
		}
	}
}
