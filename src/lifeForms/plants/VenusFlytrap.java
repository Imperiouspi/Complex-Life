package lifeForms.plants;

import java.awt.Color;

import types.LifeForm;
import types.Plant;

public class VenusFlytrap extends Plant {
	public static Color colour;
	public VenusFlytrap(int x, int y) {
		super(x, y);
		species = "Venus Flytrap";
		MaxHealth = 30;
		MaxHunger = 20;
		healthLeft = MaxHealth;
		hungerLeft = MaxHunger;
		eats = new String[] { null, null };
		predators = new String[] { "Lion" };
		colour = Color.green;
		LifeSpan = 20;
		localx = x;
		localy = y;
	}

	@Override
	public void Age() {
		// TODO Auto-generated method stub

	}

	@Override
	public void Die() {

	}
	
	@Override
	public void onEaten(LifeForm eating) {
		eating.healthLeft--;
		Die();
	}

}
