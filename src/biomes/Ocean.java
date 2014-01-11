package biomes;

import java.awt.Color;
import java.awt.Point;

import types.Biome;

public class Ocean extends Biome{
	public Ocean(int x, int y) {
		super("Ocean", new String[]{"Kelp", "Algae"}, new String[]{"Fish", "Shark"}, new Point(x, y), new Point(x + 20, y + 20), new Color(36, 117, 177));
	}
}
