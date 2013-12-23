package lifeForms.animals;

import java.awt.Color;

import types.LifeForm;

public class Lion extends LifeForm {

	public Lion(LifeForm[] eats, LifeForm[] predators) {
		super("Lion", 20, 20, eats, predators, Color.yellow);
		LifeSpan = 10;
	}

	@Override
	public void Eat(LifeForm eaten) {
		eaten.Die();		
	}

	@Override
	public void Move() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Die() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Breed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void findNutrients() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEat(LifeForm eating) {
		// TODO Auto-generated method stub
		
	}

}
