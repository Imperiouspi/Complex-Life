package lifeForms.animals;

import java.awt.Color;

import types.LifeForm;

public class Sparrow extends LifeForm{

	public Sparrow() {
		super();
		species = "Sparrow";
		MaxHealth = 30;
		MaxHunger = 20;
		healthLeft = MaxHealth;
		hungerLeft = MaxHunger;
		eats = new String[] {"Poison Ivy", "Grass", "Mushroom", "Clover", "Juneberry", "Mulberry", "Raspberry", "Strawberry", "Blueberry", "Blackberry", "Apple", "Pear", "Ash", "Oak", "Maple", "Birch", "Dogwood"};
		predators = new String[] {"Wolf", "Jaguar", "Lynx", "Coyote", "Bear", "Wolverine", "Cougar", "Bobcat", "Alligator"};
		color = new Color (190, 130, 60);
		LifeSpan = 5;
	}
	
	public Sparrow(int x, int y) {
		super();
		species = "Sparrow";
		MaxHealth = 30;
		MaxHunger = 20;
		healthLeft = MaxHealth;
		hungerLeft = MaxHunger;
		eats = new String[] {"Poison Ivy", "Grass", "Mushroom", "Clover", "Juneberry", "Mulberry", "Raspberry", "Strawberry", "Blueberry", "Blackberry", "Apple", "Pear", "Ash", "Oak", "Maple", "Birch", "Dogwood"};
		predators = new String[] {"Wolf", "Jaguar", "Lynx", "Coyote", "Bear", "Wolverine", "Cougar", "Bobcat", "Alligator"};
		color = new Color (190, 130, 60);
		LifeSpan = 5;
		localx = x;
		localy = y;
	}

	@Override
	public void onEaten(LifeForm eating) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Eat(LifeForm eaten) {
		// TODO Auto-generated method stub
		
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
	public void findNutrients() {
		// TODO Auto-generated method stub
		
	}

}