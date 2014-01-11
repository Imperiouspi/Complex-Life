package biomes;

import java.awt.Color;
import java.awt.Point;

import types.Biome;

public class Jungle extends Biome {

	public Jungle(int x, int y) {
		super("Jungle", new String[]{"VenusFlytrap"}, new String[]{"Lion"}, new Point(x, y), new Point(x + 20, y + 20), new Color(0, 100, 0));
	}
}
