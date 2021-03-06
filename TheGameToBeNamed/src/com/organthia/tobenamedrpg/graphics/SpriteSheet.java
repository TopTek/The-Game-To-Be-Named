package com.organthia.tobenamedrpg.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

	private String path;
	public final int SIZE;
	public final int WIDTH, HEIGHT;
	public int[] pixels;

	public static SpriteSheet nullSheet = new SpriteSheet(1, 1, 0);

	// tiles
	public static SpriteSheet tiles = new SpriteSheet("/textures/spritesheet.png", 256);
	public static SpriteSheet grassTile = new SpriteSheet("/textures/GrassTile.png", 16);
	//public static SpriteSheet waterTile = new SpriteSheet("/textures/WaterTile.png", 1024);
	public static SpriteSheet waterTile = new SpriteSheet("/textures/WaterTile1.png", 16);

	// Tile Overlays
	
	public static SpriteSheet grassTileOverlay = new SpriteSheet("/textures/GrassTileOverlay.png",32);
	
	// Encounters
	public static SpriteSheet testSwag = new SpriteSheet("/textures/testSwag.png", 64);

	private Sprite[] sprites;

	public SpriteSheet(int width, int height, int color) {
		this.path = null;
		this.SIZE = -1;
		this.WIDTH = width;
		this.HEIGHT = height;
		pixels = new int[width * height];
		for (int y = 0; y < HEIGHT; y++) {
			for (int x = 0; x < WIDTH; x++) {
				pixels[x + y * WIDTH] = color;
			}
		}
	}

	public SpriteSheet(SpriteSheet sheet, int x, int y, int width, int height, int spriteSize) {
		int xx = x * spriteSize;
		int yy = y * spriteSize;
		int w = width * spriteSize;
		int h = height * spriteSize;
		if (width == height)
			SIZE = width;
		else
			SIZE = -1;
		WIDTH = w;
		HEIGHT = h;
		pixels = new int[w * h];
		for (int y0 = 0; y0 < h; y0++) {
			int yp = yy + y0;
			for (int x0 = 0; x0 < w; x0++) {
				int xp = xx + x0;
				pixels[x0 + y0 * w] = sheet.pixels[xp + yp * sheet.WIDTH];
			}
		}

		int frame = 0;
		sprites = new Sprite[width * height];
		for (int ya = 0; ya < height; ya++) {
			for (int xa = 0; xa < width; xa++) {
				int[] spritePixels = new int[spriteSize * spriteSize];
				for (int y0 = 0; y0 < spriteSize; y0++) {
					for (int x0 = 0; x0 < spriteSize; x0++) {
						spritePixels[x0 + y0 * spriteSize] = pixels[(x0 + xa * spriteSize)
								+ (y0 + ya * spriteSize) * WIDTH];
					}
				}
				Sprite sprite = new Sprite(spritePixels, spriteSize, spriteSize);
				sprites[frame++] = sprite;
			}
		}
	}

	public SpriteSheet(String path, int size) {
		this.path = path;
		SIZE = size;
		WIDTH = size;
		HEIGHT = size;
		pixels = new int[SIZE * SIZE];
		loadImage();
	}

	public SpriteSheet(String path, int width, int height) {
		this.path = path;
		SIZE = -1;
		WIDTH = width;
		HEIGHT = height;
		loadImage();
	}

	public Sprite[] getSprites() {
		return sprites;
	}

	private void loadImage() {
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, pixels, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
