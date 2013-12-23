package types;

import java.awt.Color;

public abstract class Plant extends LifeForm{
	public Plant(String name, LifeForm[] predators, Color color){
		super(name, 1, 1, null, predators, color);
	}

	@Override
	public void Eat(LifeForm eaten){
		
	}
	
	@Override
	public void Move(){
		
	}
	
	public abstract void Die();
	public abstract void Breed();
	
	@Override
	public void findNutrients(){
		
	}
	
	public abstract void onEat(LifeForm eating);
}
