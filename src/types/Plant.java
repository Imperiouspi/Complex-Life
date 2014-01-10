package types;


public abstract class Plant extends LifeForm{
	public Plant(){
		super();
	}
	
	public Plant(int x, int y){
		super();
		localx = x;
		localy = y;
	}

	@Override
	public void Eat(LifeForm eaten){
		
	}
	
	@Override
	public void Move(Tile[][] grid){		
	}
	
	public abstract void Die();
	public abstract void Breed();
	
	@Override
	public void findNutrients(){
		
	}
	
	public abstract void onEaten(LifeForm eating);
}
