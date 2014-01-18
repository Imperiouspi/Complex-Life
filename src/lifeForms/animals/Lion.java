package lifeForms.animals;

import java.awt.Color;

import types.LifeForm;

public class Lion extends LifeForm {
	public Lion(int x, int y) {
		super(x, y);
		species = "Lion";
		MaxHealth = 30;
		MaxHunger = 50;
		healthLeft = MaxHealth;
		hungerLeft = MaxHunger;
		eats = new String[] { "Horse", "MountainGoat" };
		predators = new String[] {};
		color = Color.yellow;
		LifeSpan = 10;
		localx = x;
		localy = y;
		breedChance = 1;
	}

	@Override
	public void onEaten(LifeForm eating) {

	}
}
