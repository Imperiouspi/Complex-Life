package biomes;

import java.awt.Color;
import java.awt.Point;

import types.Biome;

/**
 * A normal biome, but was discarded in the favor of simplicity. Supports marine life, also never implemented.
 * @author Noah
 *
 */
@Deprecated
public class Ocean extends Biome{
	public Ocean(int x, int y) {
		super("Ocean", new String[]{"Kelp", "Algae"}, new String[]{"Fish", "Shark"}, new Point(x, y), new Point(x + 20, y + 20), new Color(36, 117, 177));
	}
}
