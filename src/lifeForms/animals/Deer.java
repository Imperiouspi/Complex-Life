package lifeForms.animals;

import java.awt.Color;

import types.LifeForm;
import types.Tile;

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
		color = new Color (190, 130, 60);
		LifeSpan = 15;
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
		color = new Color (190, 130, 60);
		LifeSpan = 15;
		localx = x;
		localy = y;
	}

	@Override
	public void Eat(LifeForm eaten) {
		eaten.Die();
		this.healthLeft++;
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
	public void Move(Tile[][] grid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEaten(LifeForm eating) {
		// TODO Auto-generated method stub
		
	}

}
