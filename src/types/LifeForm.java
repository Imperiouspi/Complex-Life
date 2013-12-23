package types;

import java.awt.Color;
//Maybe add id for easy access?

public abstract class LifeForm {
	public String species;
	public int health, hunger;
	public int healthLeft, hungerLeft;
	public LifeForm[] eats, predators;
	public Color color;
	
	public LifeForm(String name, int life, int stomach, LifeForm[] eats, LifeForm[] predators, Color color){
		species = name;
		health = life;
		hunger = stomach;
		this.eats = eats;
		this.predators = predators;
		this.color = color;
	}
	
	public abstract void Eat();
	public abstract void Move();
	public abstract void Age();
	public abstract void Die();
	public abstract void Breed();
	public abstract void findNutrients();
	public abstract void onEat(LifeForm eating);
}
