package com.organthia.tobenamedrpg.graphics;

public class Sprite {

	public final int SIZE;
	private int x, y;
	public int[] pixels;
	private SpriteSheet sheet;

	public static Sprite voidSprite = new Sprite(16, 0x000000);
	public static Sprite grassTile = new Sprite(16, 0, 0, SpriteSheet.tiles);
	public static Sprite waterTile = new Sprite(16, 1, 0, SpriteSheet.tiles);
	public static Sprite lavaTile = new Sprite (16, 2, 0, SpriteSheet.tiles);

	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		loadImage();
	}
	
	public Sprite(int size, int color){
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		setColor(color);
	}
	
	private void setColor(int color){
		for(int i = 0; i <SIZE *SIZE; i++){
			pixels[i] = color;
		}
	}

	private void loadImage() {
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
			}
		}
	}
}
