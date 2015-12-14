package com.organthia.tobenamedrpg.encounter;

import java.awt.Color;

import com.organthia.tobenamedrpg.graphics.SpriteSheet;

public class ImageToEncounter extends Encounter{
	
	public ImageToEncounter(int width, int height, SpriteSheet sheet) {
		super(width, height, sheet);
	}

	protected void generateEncounter() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				Color color = new Color(sheet.pixels[x+y*width]);
				String hex = String.format("%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue()).toUpperCase();
				if ("0000FF".equals(hex))tiles[x + y * width] = waterTileReturn;
				if ("00FF00".equals(hex))tiles[x + y * width] = grassTileReturn;
				if ("FF0000".equals(hex))tiles[x + y * width] = lavaTileReturn;
				if ("D15FEE".equals(hex))tiles[x + y * width] = 0;
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
