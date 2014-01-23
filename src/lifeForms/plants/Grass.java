package lifeForms.plants;

import java.awt.Color;

import types.LifeForm;
import types.Plant;

/**
 * Grass overrides the death method, and so cannot die. otherwise, a standard plant.
 * 
 * @author Noah
 * 
 */
public class Grass extends Plant {
	public static Color colour;

	public Grass(int x, int y) {
		super(x, y);
		localx = x;
		localy = y;

		species = "Grass";
		MaxHealth = 5;
		MaxHunger = 1;
		healthLeft = MaxHealth;
		hungerLeft = MaxHunger;
		eats = new String[] { null, null };
		predators = new String[] { "Horse", "Deer", "Rabbit", "MountainGoat" };
		colour = new Color(0, 200, 0);
		maxLife = 30;
		LifeSpan = 30;
	}

	@Override
	public void Die() {
	}

	@Override
	public void onEaten(LifeForm eating) {
	}

}
