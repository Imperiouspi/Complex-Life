package lifeForms.animals;

import java.awt.Color;

import types.LifeForm;

public class Rabbit extends LifeForm{
	public Rabbit(int x, int y) {
		super(x, y);
		species = "Rabbit";
		MaxHealth = 30;
		MaxHunger = 200;
		healthLeft = MaxHealth;
		hungerLeft = MaxHunger;
		eats = new String[] {"Grass"};
		predators = new String[] {"Lion"};
		color = Color.GRAY;
		LifeSpan = 12;
		breedChance = 30;
		localx = x;
		localy = y;
	}

	@Override
	public void onEaten(LifeForm eating) {
		// TODO Auto-generated method stub
		
	}

}
