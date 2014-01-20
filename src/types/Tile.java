package types;

import java.awt.Color;
import java.util.ArrayList;

public class Tile {
	public int Nutrients;
	public Weather weather;
	public ArrayList<LifeForm> Occupant;
	public int x, y;
	public Color color;
	public Biome location;
	
	public Tile(int x, int y, Color color, Biome location){
		Occupant = new ArrayList<LifeForm>();
		this.location = location;
		this.color = color;
		this.x = x;
		this.y = y;
		Nutrients = 0;
		weather = Weather.CLEAR;
	}
	
	public Tile(int x, int y, Color color, ArrayList<LifeForm> Occupant){
		Occupant = new ArrayList<LifeForm>();
		this.Occupant.addAll(Occupant);
		this.color = color;
		this.x = x;
		this.y = y;
		Nutrients = 0;
		weather = Weather.CLEAR;
	}
}