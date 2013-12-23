package types;
public abstract class Plant extends LifeForm{
	public Plant(String name, LifeForm[] predators){
		super(name, 1, 1, null, predators);
	}

	@Override
	public void Eat(){
		
	}
	
	@Override
	public void Move(){
		
	}
	
	public abstract void Age();
	public abstract void Die();
	public abstract void Breed();
	
	@Override
	public void findNutrients(){
		
	}
	
	public abstract void onEat(LifeForm eating);
}
