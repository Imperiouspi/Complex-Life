   package lifeForms.animals;

import java.awt.Color;

import types.LifeForm;

public class MountainGoat extends LifeForm{
	public MountainGoat(int x, int y) {
		super(x, y);
		species = "MountainGoat";
		MaxHealth = 30;
		MaxHunger = 200;
		healthLeft = MaxHealth;
		hungerLeft = MaxHunger;
		eats = new String[] {"Grass"};
		predators = new String[] {"Lion"};
		color = new Color(129, 89, 30);
		LifeSpan = 10;
		breedChance = 100;
		breedCooldown = 0;
		localx = x;
		localy = y;
	}

	@Override
	public void onEaten(LifeForm eating) {
		// TODO Auto-generated method stub
		
	}

}
