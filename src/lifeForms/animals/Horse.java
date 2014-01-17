package lifeForms.animals;

import java.awt.Color;
import java.awt.Graphics;

import types.LifeForm;

public class Horse extends LifeForm {

	public Horse() {
		super();
		species = "Horse";
		MaxHealth = 30;
		MaxHunger = 200;
		healthLeft = MaxHealth;
		hungerLeft = MaxHunger;
		eats = new String[] { "Grass" };
		predators = new String[] { "Lion" };
		color = Color.lightGray;
		LifeSpan = 30;
	}

	public Horse(int x, int y) {
		super();
		species = "Horse";
		MaxHealth = 30;
		MaxHunger = 200;
		healthLeft = MaxHealth;
		hungerLeft = MaxHunger;
		eats = new String[] { "Grass" };
		predators = new String[] { "Lion" };
		color = Color.lightGray;
		LifeSpan = 30;
		localx = x;
		localy = y;
	}

	@Override
	public void onEaten(LifeForm eating) {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub

	}

}
