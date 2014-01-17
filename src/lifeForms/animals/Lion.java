package lifeForms.animals;

import java.awt.Color;

import types.LifeForm;

public class Lion extends LifeForm {

	public Lion() {
		super();
		species = "Lion";
		MaxHealth = 30;
		MaxHunger = 20;
		healthLeft = MaxHealth;
		hungerLeft = MaxHunger;
		eats = new String[] { "Horse", "MountainGoat" };
		predators = new String[] { null };
		color = Color.yellow;
		LifeSpan = 10;
	}

	public Lion(int x, int y) {
		super();
		species = "Lion";
		MaxHealth = 30;
		MaxHunger = 200;
		healthLeft = MaxHealth;
		hungerLeft = MaxHunger;
		eats = new String[] { "Horse", "MountainGoat" };
		predators = new String[] { null };
		color = Color.yellow;
		LifeSpan = 10;
		localx = x;
		localy = y;
	}

	@Override
	public void onEaten(LifeForm eating) {

	}
}
