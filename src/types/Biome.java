package types;

import java.awt.Color;
import java.awt.Point;

/**
 * An abstract Biome on the board
 * @author Noah
 *
 */
public abstract class Biome {
	public String name; //the name of the biome
	public String[] foods; //the plants supported
	public String[] support; //the animals supported
	public int[][] Nutrients;//unused
	public Color color; //the colour displayed on the board
	public Point close, far; //the points describing the square that the biome takes up.
	
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
