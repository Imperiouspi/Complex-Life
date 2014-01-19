package types;

import java.awt.Color;
import java.awt.Point;

public abstract class Biome {
	public String name;
	public String[] foods;
	public String[] support;
	public int[][] Nutrients;
	public Color color;
	public Point close, far;
	
	public Biome(String name, String[] foods, String[] support, Point close, Point far, Color color){
		this.name = name;
		this.foods = foods;
		this.support = support;
		this.color = color;
		this.close = close;
		this.far = far;
	}
	
	@Override
	public String toString(){
		return name;
	}
}
