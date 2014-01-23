package biomes;

import java.awt.Color;
import java.awt.Point;

import types.Biome;

/**
 * A Normal Biome.
 * Supports grass, Lions, and Horses.
 * Lion:Horse ratio is 1:9.
 * It has a color of (180, 141, 56).
 * @author Noah
 */
public class Plains extends Biome {

	public Plains(int x, int y) {
		super("Plains", new String[]{"Grass"}, new String[]{"Lion", "Horse", "Horse", "Horse", "Horse", "Horse", "Horse", "Horse", "Horse", "Horse"}, new Point(x, y), new Point(x + 20, y + 20), new Color(180, 141, 56));
	}
}
