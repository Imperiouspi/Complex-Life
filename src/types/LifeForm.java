package types;

import java.awt.Color;
//Maybe add id for easy access?

public abstract class LifeForm {
	public String species;
	public int health, hunger;
	public int healthLeft, hungerLeft;
	public LifeForm[] eats, predators;
	public Color color;
	public int LifeSpan;
	
	public LifeForm(String name, int life, int stomach, LifeForm[] eats, LifeForm[] predators, Color color){
		species = name;
		health = life;
		hunger = stomach;
		this.eats = eats;
		this.predators = predators;
		this.color = color;
	}
	
	public abstract void Eat(LifeForm eaten);
	public abstract void Move();
	public void Age(){
		LifeSpan--;
	}
	public abstract void Die();
	public abstract void Breed();
	public abstract void findNutrients();
	public abstract void onEat(LifeForm eating);
}
