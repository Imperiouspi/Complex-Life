package types;


public abstract class Plant extends LifeForm{
	public Plant(int x, int y){
		super(x, y);
		localx = x;
		localy = y;
	}
	
	@Override
	public Tile[][] Move(Tile[][]grid){
		return grid;
		
	}
	
	public abstract void onEaten(LifeForm eating);
}
