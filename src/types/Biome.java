package types;

import java.awt.Color;
import java.awt.Dimension;

public abstract class Biome {
	public String name;
	public Plant[] foods;
	public LifeForm[] support;
	public Dimension bounds;
	public int[][] Nutrients;
	public Color color;
	
	public Biome(String local, Plant[] foods, LifeForm[] support, Dimension bounds, Color color){
		name = local;
		this.foods = foods;
		this.support = support;
		this.bounds = bounds;
		this.color = color;
	}
}
