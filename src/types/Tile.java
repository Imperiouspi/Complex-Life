package types;

import java.awt.Color;

public class Tile {
	public int Nutrients;
	public Weather weather;
	public boolean isOccupied;
	public LifeForm Occupant;
	public int x, y;
	public Color color;
	public Biome location;
	
	public Tile(int x, int y, Color color, Biome location){
		this.location = location;
		this.color = color;
		this.x = x;
		this.y = y;
		isOccupied = false;
		Nutrients = 0;
		weather = Weather.CLEAR;
	}
	
	public Tile(int x, int y, Color color, LifeForm Occupant){
		this.Occupant = Occupant;
		this.color = color;
		this.x = x;
		this.y = y;
		isOccupied = false;
		Nutrients = 0;
		weather = Weather.CLEAR;
	}
}