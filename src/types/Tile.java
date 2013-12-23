package types;

public class Tile {
	public int Nutrients;
	public Weather weather;
	public boolean isOccupied;
	public LifeForm Occupant;
	public int x, y;
	
	public Tile(int x, int y){
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