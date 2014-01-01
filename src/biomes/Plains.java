package biomes;

import java.awt.Color;
import java.awt.Point;

import types.Biome;

public class Plains extends Biome {

	public Plains(int x, int y) {
		super("Plains", new String[]{"Grass"}, new String[]{"Lion", "Horse"}, new Point(x, y), new Point(x + 20, y + 20), new Color(180, 141, 56));
	}
}
