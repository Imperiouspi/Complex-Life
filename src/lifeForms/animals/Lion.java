package lifeForms.animals;

import java.awt.Color;

import types.LifeForm;

public class Lion extends LifeForm {
	public static Color colour;
	public static boolean breedEnabled;
	public static int statBreedChance, statBreedCooldown;
	public Lion(int x, int y) {
		super(x, y);
		species = "Lion";
		MaxHealth = 30;
		MaxHunger = 50;
		healthLeft = MaxHealth;
		hungerLeft = MaxHunger;
		eats = new String[] { "Horse", "Mountain Goat" };
		predators = new String[] {};
		colour = Color.yellow;
		LifeSpan = 10;
		maxLife = LifeSpan;
		localx = x;
		localy = y;
		breedEnabled = true;
		statBreedChance = 1;
		statBreedCooldown = 10;
	}
	
	@Override
	public void onEaten(LifeForm eating) {
		
	}
}
