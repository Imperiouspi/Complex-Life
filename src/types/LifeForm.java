package types;

import java.awt.Color;
import java.awt.Graphics;

//Maybe add id for easy access?

public abstract class LifeForm {
	public static String species;
	public static int MaxHealth, MaxHunger;
	public int healthLeft, hungerLeft;
	public static String[] eats, predators;
	public Color color;
	public static int LifeSpan;
	public int localx, localy, viewDistance;

	public LifeForm(int x, int y) {
		localx = x;
		localy = y;
	}

	public LifeForm() {

	}

	public abstract void Eat(LifeForm eaten);

	public abstract void Move(Tile[][] grid);

	public void Age() {
		LifeSpan--;
	}

	public abstract void Die();

	public abstract void Breed();

	public abstract void findNutrients();

	public abstract void onEaten(LifeForm eating);

	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect(localx * 5, localy * 5, 5, 5);
	}
}
