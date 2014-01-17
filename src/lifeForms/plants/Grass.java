package lifeForms.plants;

import java.awt.Color;

import types.LifeForm;
import types.Plant;

public class Grass extends Plant { // :)

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
		color = new Color(0, 200, 0); // Should this be (0, 200, 0) like the one
										// above, or (10, 200, 10) like it used
										// to be?
		LifeSpan = 20;
	}

	@Override
	public void Die() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void onEaten(LifeForm eating) {
		// If you put healthLeft++, healthLeft will increase by one. I don't
		// think that's what you want.
		eating.healthLeft = Math.min(healthLeft + 1, MaxHealth);
		eating.hungerLeft = Math.max(hungerLeft - 1, 0);
		// I changed the hungerLeft assignment. I feel like hunger should
		// decrease, not increase.
		// These changes to healthLeft and hungerLeft should be to the life form
		// eating the grass, right? Not the grass itself?
	}

}
