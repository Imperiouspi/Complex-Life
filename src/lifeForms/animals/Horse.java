package lifeForms.animals;

import java.awt.Color;

import types.LifeForm;

/**
 * A Horse. Lives for 30 years. can move 500 squares without eating.
 * @author Noah
 *
 */
public class Horse extends LifeForm {
	public static Color colour;
	public static boolean breedEnabled;
	public static int statBreedChance, statBreedCooldown; //static variables of the cooldown and breedchance.
	public Horse(int x, int y) {
		super(x, y);
		species = "Horse";
		MaxHealth = 30;
		MaxHunger = 500;
		healthLeft = MaxHealth;
		hungerLeft = MaxHunger;
		eats = new String[] { "Grass" };
		predators = new String[] { "Lion" };
		colour = Color.black;
		LifeSpan = 30;
		maxLife = LifeSpan;
		localx = x;
		localy = y;
		breedEnabled = true;
		statBreedCooldown = 3;
		statBreedChance = 10;
	}

	@Override
	public void onEaten(LifeForm eating) {
		// TODO Auto-generated method stub

	}
}
