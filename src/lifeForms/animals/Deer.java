package lifeForms.animals;

import java.awt.Color;

import types.LifeForm;

public class Deer extends LifeForm{

	public Deer() {
		super();
		species = "Deer";
		MaxHealth = 30;
		MaxHunger = 20;
		healthLeft = MaxHealth;
		hungerLeft = MaxHunger;
		eats = new String[] {"Poison Ivy", "Grass", "Mushroom", "Clover", "Juneberry", "Mulberry", "Raspberry", "Strawberry", "Blueberry", "Blackberry", "Apple", "Pear", "Ash", "Oak", "Maple", "Birch", "Dogwood"};
		predators = new String[] {"Wolf", "Jaguar", "Lynx", "Coyote", "Bear", "Wolverine", "Cougar", "Bobcat", "Alligator"};
		color = Color.BLACK; //TODO make brown
		LifeSpan = 40;
	}
	
	public Deer(int x, int y) {
		super();
		species = "Deer";
		MaxHealth = 30;
		MaxHunger = 20;
		healthLeft = MaxHealth;
		hungerLeft = MaxHunger;
		eats = new String[] {"Poison Ivy", "Grass", "Mushroom", "Clover", "Juneberry", "Mulberry", "Raspberry", "Strawberry", "Blueberry", "Blackberry", "Apple", "Pear", "Ash", "Oak", "Maple", "Birch", "Dogwood"};
		predators = new String[] {"Wolf", "Jaguar", "Lynx", "Coyote", "Bear", "Wolverine", "Cougar", "Bobcat", "Alligator"};
		color = Color.LIGHT_GRAY;
		LifeSpan = 40;
		localx = x;
		localy = y;
	}

	@Override
	public void Eat(LifeForm eaten) {
		eaten.Die();
		
	}

	@Override
	public void Move() {
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

	@Override
	public void onEat(LifeForm eating) {
		// TODO Auto-generated method stub
		
	}

}
