package types;


public abstract class Plant extends LifeForm{
	public Plant(int x, int y){
		super(x, y);
		localx = x;
		localy = y;
	}
	
	@Override
	public Tile[][] Move(Tile[][]grid){
		isDead();
		LifeSpan--;
		return grid;
	}
	
	@Override
	public boolean Breed(World world){
		return false;
	}
	
	@Override
	public void Die(){

	}
	public abstract void onEaten(LifeForm eating);
}
