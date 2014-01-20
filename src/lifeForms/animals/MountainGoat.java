   package lifeForms.animals;

import java.awt.Color;

import types.LifeForm;

public class MountainGoat extends LifeForm{
	public static Color staticColor;
	
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
		color = new Color(129, 89, 30);
		LifeSpan = 10;
		statBreedChance = 100;
		statBreedCooldown = 3;
		localx = x;
		localy = y;
		staticColor = color;
		maxLife = LifeSpan;
	}

	@Override
	public void onEaten(LifeForm eating) {
		// TODO Auto-generated method stub
		
	}
}