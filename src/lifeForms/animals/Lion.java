package lifeForms.animals;

import gui.aWindow;

import java.awt.Color;

import types.LifeForm;
import types.Tile;

public class Lion extends LifeForm {
	public static Color staticColor;
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
		color = Color.yellow;
		LifeSpan = 10;
		localx = x;
		localy = y;
		statBreedChance = 1;
		statBreedCooldown = 10;
		staticColor = this.color;
	}
	
	@Override
	public void onEaten(LifeForm eating) {
		
	}
}
