package com.organthia.tobenamedrpg.graphics;

public class RotateImage {

	public static int[] rotateRight(int[] pixels, int width, int height) {
		int[] newPix = new int[width * height];
		for (int y = 0; y < width; y++) {
			for (int x = 0; x < height; x++) {
				int yp = height - x - 1;
				int xp = y;
				newPix[x + y * height] = pixels[xp + yp * width];
			}
		}
		return newPix;
	}

	public static int[] rotateLeft(int[] pixels, int width, int height) {
		int[] newPix = new int[width * height];
		for (int y = 0; y < width; y++) {
			for (int x = 0; x < height; x++) {
				int yp = x;
				int xp = width - y - 1;
				newPix[x + y * height] = pixels[xp + yp * width];
			}
		}
		return newPix;
	}

	public static int[] rotateHalf(int[] pixels, int width, int height) {
		int[] newPix = new int[width * height];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int yp = height - y - 1;
				int xp = width - x - 1;
				newPix[x + y * height] = pixels[xp + yp * width];
			}
		}
		return newPix;
	}
}
