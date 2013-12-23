public abstract class Plant extends LifeForm{
	public Plant(String name, LifeForm[] predators){
		super(name, 1, 1, null, predators);
	}

	public abstract void Eat();
	public abstract void Move();
	public abstract void Age();
	public abstract void Die();
	public abstract void Breed();
	public abstract void findNutrients();
	public abstract void onEat();
}
