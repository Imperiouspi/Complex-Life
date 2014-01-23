package types;

import java.awt.Color;

/**
 * A single tile on the large grid of the world.
 * @author Noah
 *
 */
public class Tile {
	public int Nutrients;//the nutrient level of the tile --unimplemented
	public Weather weather; //the weather on the tile
	public LifeForm Occupant; //the lifeform occupying the tile.
	public int x, y; // the tile's coordinates on the world grid.
	public Color color; //the colour of the tile (and the biome)
	public Biome location;//the biome the tile is situated in
	
	public Tile(int x, int y, Color color, Biome location){
		this.location = location;
		this.color = color;
		this.x = x;
		this.y = y;
		Nutrients = 0;
		weather = Weather.CLEAR;
	}
	
	public Tile(int x, int y, Color color, LifeForm Occupant){
		this.Occupant = Occupant;
		this.color = color;
		this.x = x;
		this.y = y;
		Nutrients = 0;
		weather = Weather.CLEAR;
	}
}