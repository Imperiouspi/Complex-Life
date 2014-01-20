package lifeForms.animals;

import java.awt.Color;
import java.awt.Graphics;

import types.LifeForm;

public class Horse extends LifeForm {

	public Horse(int x, int y) {
		super(x, y);
		species = "Horse";
		MaxHealth = 30;
		MaxHunger = 500;
		healthLeft = MaxHealth;
		hungerLeft = MaxHunger;
		eats = new String[] { "Grass" };
		predators = new String[] { "Lion" };
		color = Color.black;
		LifeSpan = 30;
		maxLife = LifeSpan;
		localx = x;
		localy = y;
		breedCooldown = 0;
		breedChance = 10;
	}

	@Override
	public void onEaten(LifeForm eating) {
		// TODO Auto-generated method stub

	}
}
