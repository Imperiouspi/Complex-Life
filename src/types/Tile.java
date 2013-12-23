package types;

public class Tile {
	public int Nutrients, Weather;
	public boolean isOccupied;
	public LifeForm Occupant;
	public int x, y;
	
	public Tile(int x, int y){
		this.x = x;
		this.y = y;
		isOccupied = false;
		Nutrients = 0;
		Weather = 0;
	}
}