package biomes;

import java.awt.Color;
import java.awt.Point;

import types.Biome;

/**
 * A Normal Biome.
 * Supports grass and Mountain Goats.
 * It has a color of (200, 200, 200).
 * @author Noah
 *
 */
public class Mountains extends Biome {

	public Mountains(int x, int y) {
		super("Mountains", new String[]{"Grass"}, new String[]{"Mountain Goat", "Mountain Goat", "Mountain Goat", "Mountain Goat", "Mountain Goat", "Mountain Goat"}, new Point(x, y), new Point(x + 20, y + 20), new Color(200,200,200));
	}
}
