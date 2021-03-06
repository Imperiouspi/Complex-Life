package lifeForms.animals;

import java.awt.Color;

import types.LifeForm;

/**
 * A standard LifeForm, no longer used.
 * @author Noah
 *
 */
@Deprecated
public class Wolf extends LifeForm {
	public static Color colour;
	public static int statBreedChance, statBreedCooldown;
	public Wolf(int x, int y) {
		super(x, y);
		species = "Wolf";
		MaxHealth = 30;
		MaxHunger = 200;
		healthLeft = MaxHealth;
		hungerLeft = MaxHunger;
		eats = new String[] {"Deer", "Moose", "Rabbit", "Squirrel", "Blueberry", "Nightshade", "Apple", "Pear"};
		predators = new String[] {"Tiger"};
		colour = Color.LIGHT_GRAY;
		LifeSpan = 30;
		statBreedChance = 3;
		statBreedCooldown = 7;
		localx = x;
		localy = y;
	}

	@Override
	public void onEaten(LifeForm eating) {
		// TODO Auto-generated method stub
		
	}

}
