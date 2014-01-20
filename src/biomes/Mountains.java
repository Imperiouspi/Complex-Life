package biomes;

import java.awt.Color;
import java.awt.Point;

import types.Biome;

public class Mountains extends Biome {

	public Mountains(int x, int y) {
		super("Mountains", new String[]{"Grass"}, new String[]{"Mountain Goat", "Mountain Goat", "Mountain Goat", "Mountain Goat", "Mountain Goat", "Mountain Goat"}, new Point(x, y), new Point(x + 20, y + 20), new Color(200,200,200));
	}
}
