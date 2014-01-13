package lifeForms.animals;

import java.awt.Color;

import types.LifeForm;
import types.Tile;

public class MountainGoat extends LifeForm{

	public MountainGoat() {
		super();
		species = "MountainGoat";
		MaxHealth = 30;
		MaxHunger = 20;
		healthLeft = MaxHealth;
		hungerLeft = MaxHunger;
		eats = new String[] {"Grass"};
		predators = new String[] {"MountainLion"};
		color = Color.DARK_GRAY;
		LifeSpan = 10;
	}
	
	public MountainGoat(int x, int y) {
		super();
		species = "MountainGoat";
		MaxHealth = 30;
		MaxHunger = 20;
		healthLeft = MaxHealth;
		hungerLeft = MaxHunger;
		eats = new String[] {"Grass"};
		predators = new String[] {"MountainLion"};
		color = new Color(129, 89, 30);
		LifeSpan = 10;
		localx = x;
		localy = y;
	}

	@Override
	public void Eat(LifeForm eaten) {
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
	public void onEaten(LifeForm eating) {
		// TODO Auto-generated method stub
		
	}

}
