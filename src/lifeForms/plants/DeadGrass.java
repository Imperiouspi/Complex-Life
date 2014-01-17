package lifeForms.plants;

import java.awt.Color;

public class DeadGrass extends Grass {

	public DeadGrass(int x, int y) {
		super(x, y);
		localx = x;
		localy = y;
		
		species = "Dead Grass";
		MaxHealth = 0;
		MaxHunger = 0;
		healthLeft = MaxHealth;
		hungerLeft = MaxHunger;
		eats = new String[] {null, null};
		predators = new String[] {"Horse", "Deer", "Rabbit", "MountainGoat"};
		color = new Color(129, 71, 64); // Brown
		LifeSpan = 0;
	}

}
