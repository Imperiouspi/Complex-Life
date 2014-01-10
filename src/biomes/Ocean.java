package biomes;

import types.Biome;

public class Ocean extends Biome{
	public Ocean(int x, int y) {
		super("Ocean", new String[]{"Kelp", "Algae"}, new String[]{"Fish", "Shark"}, new Point(x, y), new Point(x + 20, y + 20), new Color(0, 0, 255));
	}
}
