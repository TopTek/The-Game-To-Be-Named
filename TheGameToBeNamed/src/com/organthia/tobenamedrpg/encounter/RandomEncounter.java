package com.organthia.tobenamedrpg.encounter;

import java.util.Random;

public class RandomEncounter extends Encounter {

	private static final Random random = new Random();

	public RandomEncounter(int width, int height) {
		super(width, height);
	}

	protected void generateEncounter() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				tiles[x + y * width] = random.nextInt(3)+1;
			}
		}
	}
}