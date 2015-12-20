package com.organthia.tobenamedrpg.graphics;

public class Sprite {

	public final int SIZE;
	private int x, y = 0;
	public int[] pixels;
	protected SpriteSheet sheet;
	public int tileWidth, tileHeight;
	public int sheetWidth, sheetHeight;

	// Tiles
	public static Sprite voidSprite = new Sprite(16, 0x000000);
	public static Sprite grassTile = new Sprite(16, SpriteSheet.grassTile);
	//public static Sprite waterTile = new Sprite(1024, 16, SpriteSheet.waterTile);
	public static Sprite waterTile = new Sprite(16, 16, SpriteSheet.waterTile);
	public static Sprite lavaTile = new Sprite(16, 2, 0, SpriteSheet.tiles);

	// Tile Overlays
	public static Sprite grassTileOverlay = new Sprite(32, 16, SpriteSheet.grassTileOverlay);

	protected Sprite(SpriteSheet sheet, int width, int height) {
		SIZE = width == height ? width : -1;
		pixels = new int[width * height];
		this.sheet = sheet;
		this.tileWidth = width;
		this.tileHeight = height;
		this.sheetWidth = width;
		this.sheetHeight = height;
	}

	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		this.tileWidth = size;
		this.tileHeight = size;
		this.sheetWidth = size;
		this.sheetHeight = size;
		loadImage();
	}

	public Sprite(int size, SpriteSheet sheet) {
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		this.sheet = sheet;
		this.tileWidth = size;
		this.tileHeight = size;
		this.sheetWidth = size;
		this.sheetHeight = size;
		loadImage();
	}

	public Sprite(int sheetSize, int tileSize, SpriteSheet sheet) {
		SIZE = sheetSize;
		pixels = new int[sheetSize * sheetSize];
		this.sheet = sheet;
		this.tileWidth = tileSize;
		this.tileHeight = tileSize;
		this.sheetWidth = sheetSize;
		this.sheetHeight = sheetSize;
		loadImage();
	}

	public Sprite(int size, int color) {
		SIZE = size;
		this.tileWidth = size;
		this.tileHeight = size;
		this.sheetWidth = size;
		this.sheetHeight = size;
		pixels = new int[SIZE * SIZE];
		setColor(color);
	}

	public Sprite(int[] pixels, int width, int height) {
		SIZE = width == height ? width : -1;
		this.tileWidth = width;
		this.tileHeight = height;
		this.sheetWidth = width;
		this.sheetHeight = height;
		this.pixels = pixels;
	}

	private void setColor(int color) {
		for (int i = 0; i < SIZE * SIZE; i++) {
			pixels[i] = color;
		}
	}

	private void loadImage() {
		for (int y = 0; y < sheetHeight; y++) {
			for (int x = 0; x < sheetWidth; x++) {
				pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
			}
		}
	}
}
