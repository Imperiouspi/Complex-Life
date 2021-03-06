package types;

/**
 * extends LifeForm
 * overrides moving and breeding, making them do nothing for plants.
 * @author Noah
 *
 */
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
	
	@Override
	public boolean Breed(World world){
		return false;
	}
	
	@Override
	public void Die(){
		this.alive = false;
	}
	public abstract void onEaten(LifeForm eating);
}
