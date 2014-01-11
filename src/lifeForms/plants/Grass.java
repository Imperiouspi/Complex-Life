package lifeForms.plants;

import java.awt.Color;

import types.LifeForm;
import types.Plant;

public class Grass extends Plant { // :)

	public Grass() {
		super();
		
		species = "Grass";
		MaxHealth = 5;
		MaxHunger = 1;
		healthLeft = MaxHealth;
		hungerLeft = MaxHunger;
		eats = new String[] {null, null};
		predators = new String[] {"Lion", "Venus Flytrap"};
		color = new Color(0, 200, 0);
		LifeSpan = 20;
	}

	public Grass(int x, int y) {
		super();
		localx = x;
		localy = y;
		
		species = "Grass";
		MaxHealth = 5;
		MaxHunger = 1;
		healthLeft = MaxHealth;
		hungerLeft = MaxHunger;
		eats = new String[] {null, null};
		predators = new String[] {"Lion", "Venus Flytrap"};
		color = new Color(10, 200, 10);
		LifeSpan = 20;
	}

	@Override
	public void Die() {
		// TODO Auto-generated method stub

	}

	@Override
	public void Breed() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onEaten(LifeForm eating) {
		healthLeft = Math.min(healthLeft ++, MaxHealth);
		hungerLeft = Math.min(hungerLeft ++, MaxHunger);

	}

}
