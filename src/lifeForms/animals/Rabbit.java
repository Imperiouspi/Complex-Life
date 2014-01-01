package lifeForms.animals;

import java.awt.Color;

import types.LifeForm;
import types.Tile;

public class Rabbit extends LifeForm{

	public Rabbit() {
		super();
		species = "Rabbit";
		MaxHealth = 30;
		MaxHunger = 20;
		healthLeft = MaxHealth;
		hungerLeft = MaxHunger;
		eats = new String[] {"Grass"};
		predators = new String[] {"Lion"};
		color = Color.GRAY;
		LifeSpan = 12;
	}
	
	public Rabbit(int x, int y) {
		super();
		species = "Rabbit";
		MaxHealth = 30;
		MaxHunger = 20;
		healthLeft = MaxHealth;
		hungerLeft = MaxHunger;
		eats = new String[] {"Grass"};
		predators = new String[] {"Lion"};
		color = new Color(129, 89, 30);
		LifeSpan = 12;
		localx = x;
		localy = y;
	}

	@Override
	public void Eat(LifeForm eaten) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Tile[][] Move(Tile[][]grid) {
		return grid;
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
