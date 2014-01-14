package types;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public abstract class LifeForm {
	public String species;
	public static int MaxHealth, MaxHunger;
	public int healthLeft, hungerLeft;
	public static String[] eats, predators;
	public Color color;
	public static int LifeSpan;
	public int localx, localy, viewDistance = 5;

	public LifeForm(int x, int y) {
		localx = x;
		localy = y;
	}

	public LifeForm() {

	}

	public abstract void Eat(LifeForm eaten);

	public void Move(Tile[][] grid) {
		// if no prey or predators around, move randomly.
		boolean surrounded = false;
		int predatorsAround = 0, preyAround = 0;
		Point moveTo = new Point((int) (Math.random() * 2) + 1,
				(int) (Math.random() * 2) + 1);

		for (int i = 0; i < 8; i++) {
			if (localx - 1 > 0 && grid[localx - 1][localy].Occupant != null) {
				surrounded = true;
				if (isPredator(grid[localx - 1][localy].Occupant)) {
					predatorsAround++;
				} else if (isFood(grid[localx - 1][localy].Occupant)) {
					preyAround++;
				}
			}
			if (localx - 1 > 0 && grid[localx - 1][localy - 1] != null) {
				surrounded = true;
				if (isPredator(grid[localx - 1][localy - 1].Occupant)) {
					predatorsAround++;
				} else if (isFood(grid[localx - 1][localy - 1].Occupant)) {
					preyAround++;
				}
			}
			if (localx + 1 < grid.length && grid[localx + 1][localy] != null) {
				surrounded = true;
				if (isPredator(grid[localx + 1][localy].Occupant)) {
					predatorsAround++;
				} else if (isFood(grid[localx + 1][localy].Occupant)) {
					preyAround++;
				}
			}
			if (localx + 1 < grid.length && localy + 1 < grid.length
					&& grid[localx + 1][localy + 1] != null) {
				surrounded = true;
				if (isPredator(grid[localx + 1][localy + 1].Occupant)) {
					predatorsAround++;
				} else if (isFood(grid[localx + 1][localy + 1].Occupant)) {
					preyAround++;
				}
			}
			if (localy - 1 > 0 && grid[localx][localy - 1] != null) {
				surrounded = true;
				if (isPredator(grid[localx][localy - 1].Occupant)) {
					predatorsAround++;
				} else if (isFood(grid[localx][localy - 1].Occupant)) {
					preyAround++;
				}
			}
			if (localy + 1 < grid.length && grid[localx][localy + 1] != null) {
				surrounded = true;
				if (isPredator(grid[localx][localy + 1].Occupant)) {
					predatorsAround++;
				} else if (isFood(grid[localx][localy + 1].Occupant)) {
					preyAround++;
				}
			}
			if (localx + 1 < grid.length && localy - 1 > 0
					&& grid[localx + 1][localy - 1] != null) {
				surrounded = true;
				if (isPredator(grid[localx + 1][localy - 1].Occupant)) {
					predatorsAround++;
				} else if (isFood(grid[localx + 1][localy - 1].Occupant)) {
					preyAround++;
				}
			}
			if (localx - 1 > 0 && localy + 1 < grid.length
					&& grid[localx - 1][localy + 1] != null) {
				surrounded = true;
				if (isPredator(grid[localx - 1][localy + 1].Occupant)) {
					predatorsAround++;
				} else if (isFood(grid[localx - 1][localy + 1].Occupant)) {
					preyAround++;
				}
			}
		}

		if (surrounded) {

		} else {
			int chance = (int) (Math.random() * 2);
			if (chance == 1 && this.localx + moveTo.x < grid.length) {
				this.localx += moveTo.x;
			} else {
				if (this.localx - moveTo.x > 0) {
					this.localx -= moveTo.x;
				} else {
					this.localx += moveTo.x;
				}
			}
			chance = (int) (Math.random() * 2);
			if (chance == 1 && this.localy + moveTo.y < grid.length) {
				this.localy += moveTo.y;
			} else {
				if (this.localy - moveTo.y > 0) {
					this.localy -= moveTo.y;
				} else {
					this.localy += moveTo.y;
				}
			}
		}
	}

	public boolean isPredator(LifeForm life) {
		for (int i = 0; i < predators.length; i++) {
			if (predators[i] != null) {
				if (predators[i].equals(life.species)) {
					System.out.println("Predator");
					return true;
				}
			}
		}
		return false;
	}

	public boolean isFood(LifeForm life) {
		for (int i = 0; i < eats.length; i++) {
			if (eats[i] != null && eats[i].equals(life.species)) {
				System.out.println("Prey");
				return true;
			}
		}
		return false;
	}

	public abstract void Die();

	public abstract void Breed();

	public abstract void findNutrients();

	public abstract void onEaten(LifeForm eating);

	public void Age() {
		LifeSpan--;
	}

	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect(this.localx * 5, this.localy * 5, 6, 6);
	}
}
