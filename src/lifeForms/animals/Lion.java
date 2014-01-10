package lifeForms.animals;

import java.awt.Color;

import types.LifeForm;
import types.Tile;

public class Lion extends LifeForm {

	public Lion() {
		super();
		species = "Lion";
		MaxHealth = 30;
		MaxHunger = 20;
		healthLeft = MaxHealth;
		hungerLeft = MaxHunger;
		eats = new String[] { "Horse" };
		predators = new String[] { null };
		color = Color.yellow;
		LifeSpan = 10;
		viewDistance = 5;
	}

	public Lion(int x, int y) {
		super();
		species = "Lion";
		MaxHealth = 30;
		MaxHunger = 20;
		healthLeft = MaxHealth;
		hungerLeft = MaxHunger;
		eats = new String[] { "Horse" };
		predators = new String[] { null };
		color = Color.yellow;
		LifeSpan = 10;
		localx = x;
		localy = y;
	}

	@Override
	public void Eat(LifeForm eaten) {
		eaten.Die();
		this.healthLeft++;
	}

	@Override
	public void Move(Tile[][] grid) {
		//get Viewed spaces
		Tile[][] seen = new Tile[2* viewDistance + 1][2 * viewDistance + 1];
		//move away from predators and towards food
	}

	@Override
	public void Die() {
		
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

	}
}
