package types;

import java.awt.Color;

//Maybe add id for easy access?

public abstract class LifeForm {
	public static String species;
	public static int MaxHealth, MaxHunger;
	public int healthLeft, hungerLeft;
	public static String[] eats, predators;
	public static Color color;
	public static int LifeSpan;
	public int localx, localy;

	public LifeForm(int x, int y) {
		localx = x;
		localy = y;
	}

	public LifeForm() {

	}

	public abstract void Eat(LifeForm eaten);

	public abstract void Move();

	public void Age() {
		LifeSpan--;
	}

	public abstract void Die();

	public abstract void Breed();

	public abstract void findNutrients();

	public abstract void onEat(LifeForm eating);
}
