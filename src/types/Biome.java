package types;

import java.awt.Dimension;

public abstract class Biome {
	public String name;
	public Plant[] foods;
	public LifeForm[] support;
	public Dimension bounds;
	public int[][] Nutrients;

}
