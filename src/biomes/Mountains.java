package biomes;

import java.awt.Color;
import java.awt.Point;

import types.Biome;

public class Mountains extends Biome {

	public Mountains(int x, int y) {
		super("Mountains", new String[]{"Grass"}, new String[]{"MountainGoat"}, new Point(x, y), new Point(x + 20, y + 20), Color.gray);
	}
}
