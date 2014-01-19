package types;

import java.awt.Point;
import java.util.HashMap;

public class Directions extends HashMap<String, Point> {
	{
		this.put("NORTH", new Point(0, 1));
		this.put("NORTHEAST", new Point(1, 1));
		this.put("EAST", new Point(1, 0));
		this.put("SOUTHEAST", new Point(1, -1));
		this.put("SOUTH", new Point(-1, 0));
		this.put("SOUTHWEST", new Point(-1, -1));
		this.put("WEST", new Point(-1, 0));
		this.put("NORTHWEST", new Point(-1, 1));
		this.put("NOWHERE", new Point(0, 0));
	}
}