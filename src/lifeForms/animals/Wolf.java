package lifeForms.animals;

import java.awt.Color;

import types.LifeForm;
import types.Tile;

public class Wolf extends LifeForm {

	public Wolf() {
		super();
		species = "Wolf";
		MaxHealth = 30;
		MaxHunger = 20;
		healthLeft = MaxHealth;
		hungerLeft = MaxHunger;
		eats = new String[] {"Deer", "Moose", "Rabbit", "Squirrel", "Blueberry", "Nightshade", "Apple", "Pear"};
		predators = new String[] {"Tiger"};
		color = Color.LIGHT_GRAY;
		LifeSpan = 30;
	}
	
	public Wolf(int x, int y) {
		super();
		species = "Wolf";
		MaxHealth = 30;
		MaxHunger = 20;
		healthLeft = MaxHealth;
		hungerLeft = MaxHunger;
		eats = new String[] {"Deer", "Moose", "Rabbit", "Squirrel", "Blueberry", "Nightshade", "Apple", "Pear"};
		predators = new String[] {"Tiger"};
		color = Color.LIGHT_GRAY;
		LifeSpan = 30;
		localx = x;
		localy = y;
	}

	@Override
	public void Eat(LifeForm eaten) {
		eaten.Die();
		this.healthLeft++;
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
	public void Move(Tile[][] grid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEaten(LifeForm eating) {
		// TODO Auto-generated method stub
		
	}

}
