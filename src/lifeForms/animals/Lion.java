package lifeForms.animals;

import java.awt.Color;

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
	public Tile[][] Move(Tile[][] grid) {
		int moveToX = 0, moveToY = 0;
		// look at hunger and health"
		// Move away from predators, towards food.
		Tile[][] seen = new Tile[(viewDistance * 2) - 1][(viewDistance * 2) - 1];
		for (int i = 0; i < viewDistance; i++) {
			for (int j = 0; j < viewDistance; j++) {
				if (localx + i < grid.length && localx + i > 0
						&& localy + j > 0 && localy + j < grid[i].length)
					seen[i][j] = grid[localx + i][localy + j];
			}
		}

		for (int i = 0; i < viewDistance; i++) {
			for (int j = 0; j < viewDistance; j++) {
				if (localx - i < grid.length && localx - i > 0
						&& localy - j > 0 && localy - j < grid[i].length)
					seen[i][j] = grid[localx - i][localy - j];
			}
		}

		for (int i = 1; i < viewDistance; i++) {
			for (int j = 1; j < viewDistance; j++) {
				if (localx + i < grid.length && localx + i > 0
						&& localy - j > 0 && localy - j < grid[i].length)
					seen[i][j] = grid[localx + i][localy - j];
			}
		}

		for (int i = 1; i < viewDistance; i++) {
			for (int j = 1; j < viewDistance; j++) {
				if (localx - i < grid.length && localx - i > 0
						&& localy + j > 0 && localy + j < grid[i].length)
					seen[i][j] = grid[localx - i][localy + j];
			}
		}

		for (int i = 1; i < seen.length; i++) {
			for (int j = 1; j < seen[i].length; j++) {
				if (seen[i][j] != null && seen[i][j].isOccupied) {
					for (int eatCount = 0; eatCount < eats.length; eatCount++) {
						if (seen[i][j].Occupant.equals(eats[eatCount])) {
							if (seen[i][j].Occupant.localx < this.localx) {
								moveToX--;
							}
							if (seen[i][j].Occupant.localx > this.localx) {
								moveToX--;
							}
							if (seen[i][j].Occupant.localy < this.localy) {
								moveToY--;
							}
							if (seen[i][j].Occupant.localy > this.localy) {
								moveToY--;
							}
						}
					}

					for (int PredatorCount = 0; PredatorCount < eats.length; PredatorCount++) {
						if (seen[i][j].Occupant.equals(predators)) {
							if (seen[i][j].Occupant.localx < this.localx) {
								moveToX++;
							}
							if (seen[i][j].Occupant.localx > this.localx) {
								moveToX++;
							}
							if (seen[i][j].Occupant.localy < this.localy) {
								moveToY++;
							}
							if (seen[i][j].Occupant.localy > this.localy) {
								moveToY++;
							}
						}
					}
				}
			}
		}
		grid[localx][localy].isOccupied = false;
		grid[localx + moveToX][localy + moveToY].isOccupied = true;
		grid[localx + moveToX][localy + moveToY].Occupant = this;
		grid[localx + moveToX][localy + moveToY].colouring();
		localx += moveToX;
		localy += moveToY;
		return grid;
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
		// TODO Auto-generated method stub

	}

}
