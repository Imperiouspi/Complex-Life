package lifeForms.animals;

import java.awt.Color;

import types.LifeForm;

/**
 * A Mountain Goat. Lives for 30 years. can move 200 squares without eating.
 * @author Noah
 *
 */
public class MountainGoat extends LifeForm{
	public static Color colour;
	public static boolean breedEnabled;
	public static int statBreedChance, statBreedCooldown;
	public MountainGoat(int x, int y) {
		super(x, y);
		species = "Mountain Goat";
		MaxHealth = 30;
		MaxHunger = 200;
		healthLeft = MaxHealth;
		hungerLeft = MaxHunger;
		eats = new String[] {"Grass"};
		predators = new String[] {"Lion"};
		colour = new Color(129, 89, 30);
		LifeSpan = 30;
		breedEnabled = true;
		statBreedChance = 20;
		statBreedCooldown = 3;
		localx = x;
		localy = y;
		maxLife = LifeSpan;
	}

	@Override
	public void onEaten(LifeForm eating) {
		// TODO Auto-generated method stub
		
	}
}