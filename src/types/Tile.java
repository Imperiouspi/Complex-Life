package types;

import java.awt.Color;

public class Tile {
	public int Nutrients;
	public Weather weather;
	public boolean isOccupied;
	public LifeForm Occupant;
	public int x, y;
	public Color color;
	
	public Tile(int x, int y, Color color){
		this.color = color;
		this.x = x;
		this.y = y;
		isOccupied = false;
		Nutrients = 0;
		weather = Weather.CLEAR;
	}
	
	public Tile(int x, int y, Weather clouds){
		this.x = x;
		this.y = y;
		isOccupied = false;
		Nutrients = 0;
		weather = clouds;
	}
}