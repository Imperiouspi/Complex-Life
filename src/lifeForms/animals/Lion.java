package lifeForms.animals;

import java.awt.Color;
import java.awt.Graphics;

import types.LifeForm;
import types.Tile;

public class Lion extends LifeForm {

	public Lion() {
		super();
		species = "Lion";
		MaxHealth = 30;
		MaxHunger = 20;
		healthLeft = MaxHealth;
		hungerLeft = MaxHunger;
		eats = new String[] { "Horse" };
		predators = new String[] { null };
		color = Color.yellow;
		LifeSpan = 10;
		viewDistance = 5;
	}

	public Lion(int x, int y) {
		super();
		species = "Lion";
		MaxHealth = 30;
		MaxHunger = 20;
		healthLeft = MaxHealth;
		hungerLeft = MaxHunger;
		eats = new String[] { "Horse" };
		predators = new String[] { null };
		color = Color.yellow;
		LifeSpan = 10;
		localx = x;
		localy = y;
	}

	@Override
	public void Eat(LifeForm eaten) {
		eaten.Die();
		this.healthLeft++;
	}

	@Override
	public void Move(Tile[][] grid) {
		//get Viewed spaces
		//don't need this array
		Tile[][]seen = new Tile[2* viewDistance + 1][2 * viewDistance + 1];
		int MoveToX = 0, MoveToY = 0;

		seen = getSeenSquares(grid);

		for(int i = 0; i < seen.length; i++){
			for(int j = 0; j < seen[i].length; j++){
				if(seen[i][j] != null && seen[i][j].isOccupied && isPredator(seen[i][j].Occupant)){
					MoveToX = getSideX(seen[i][j].Occupant.localx, localx);
				}
				else if(seen[i][j] != null && seen[i][j].isOccupied && isFood(seen[i][j].Occupant)){
					MoveToY = getSideY(seen[i][j].Occupant.localy, localy);
				}
			}
		}
		this.localx += MoveToX;
		this.localy += MoveToY;
		//move away from predators and towards food
	}

	@Override
	public void Die() {

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
	public void onEaten(LifeForm eating) {

	}
}
