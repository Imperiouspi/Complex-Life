package lifeForms.animals;

import java.awt.Color;

import types.LifeForm;

public class Deer extends LifeForm{
	
	public Deer(int x, int y) {
		super(x, y);
		species = "Deer";
		MaxHealth = 30;
		MaxHunger = 200;
		healthLeft = MaxHealth;
		hungerLeft = MaxHunger;
		eats = new String[] {"Poison Ivy", "Grass", "Mushroom", "Clover", "Juneberry", "Mulberry", "Raspberry", "Strawberry", "Blueberry", "Blackberry", "Apple", "Pear", "Ash", "Oak", "Maple", "Birch", "Dogwood"};
		predators = new String[] {"Wolf", "Jaguar", "Lynx", "Coyote", "Bear", "Wolverine", "Cougar", "Bobcat", "Alligator", "Lion"};
		color = new Color (190, 130, 60);
		LifeSpan = 15;
		localx = x;
		localy = y;
	}
	@Override
	public void onEaten(LifeForm eating) {
		// TODO Auto-generated method stub
		
	}

}
