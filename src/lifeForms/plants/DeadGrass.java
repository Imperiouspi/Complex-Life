package lifeForms.plants;

import java.awt.Color;

/**
 * A standard Plant, no longer used. A joke plant, as it would disappear as soon as it was put on the board.
 * @author Noah
 *
 */
@Deprecated
public class DeadGrass extends Grass {
	public static Color colour;

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
		colour = new Color(129, 71, 64); // Brown
		LifeSpan = 0;
	}

}
