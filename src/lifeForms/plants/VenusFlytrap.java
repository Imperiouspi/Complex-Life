package lifeForms.plants;

import types.LifeForm;
import types.Plant;

public class VenusFlytrap extends Plant{

	public VenusFlytrap(LifeForm[] predators) {
		super("Venus Flytrap", predators);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void Age() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Die() {
		
	}

	@Override
	public void Breed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEat(LifeForm eating) {
		eating.healthLeft--;
		Die();
	}

}
