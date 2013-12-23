public abstract class LifeForm {
	String species;
	int health, hunger;
	int healthLeft, hungerLeft;
	LifeForm[] eats, predators;
	
	public LifeForm(String name, int life, int stomach, LifeForm[] eats, LifeForm[] predators){
		species = name;
		health = life;
		hunger = stomach;
		this.eats = eats;
		this.predators = predators;
	}
	
	public abstract void Eat();
	public abstract void Move();
	public abstract void Age();
	public abstract void Die();
	public abstract void Breed();
	public abstract void findNutrients();
	public abstract void onEat();
}
