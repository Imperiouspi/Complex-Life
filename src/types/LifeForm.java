package types;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public abstract class LifeForm {
	public String species;
	public int MaxHealth, MaxHunger;
	public int healthLeft, hungerLeft;
	public String[] eats, predators;
	public Color color;
	public int LifeSpan;
	public int localx, localy, viewDistance = 5;
	public boolean alive = true;

	public LifeForm(int x, int y) {
		localx = x;
		localy = y;
	}

	public LifeForm() {

	}

	public LifeForm Eat(LifeForm eaten) {
		eaten.onEaten(this);
		eaten.Die();
		this.hungerLeft = MaxHunger;
		return eaten;
	}

	public Tile[][] Move(Tile[][] grid) {
		// if no prey or predators around, move randomly.
		Point moveTo = new Point(0, 0);

		for (int i = 0; i < 8; i++) {

			getAveragePredatorAndPrey(grid);

			if (moveTo.x == 0 && moveTo.y == 0) {
				moveTo = new Point((int) (Math.random() * 2) + 1,
						(int) (Math.random() * 2) + 1);
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
		if (grid[localx][localy].isOccupied) {
			Eat(grid[localx][localy].Occupant);
		} else {
			hungerLeft--;
		}

		grid[localx][localy].isOccupied = true;
		grid[localx][localy].Occupant = this;
		return grid;
	}

	public boolean isPredator(LifeForm life) {
		if (life != null) {
			for (int i = 0; i < this.predators.length; i++) {
				if (this.predators[i] != null) {
					if (this.predators[i].equals(life.species)
							&& this.predators[i] != null) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean isFood(LifeForm life) {
		if (life != null) {
			for (int i = 0; i < this.eats.length; i++) {
				if (this.eats[i] != null && this.eats[i].equals(life.species)
						&& this.eats[i] != null) {
					return true;
				}
			}
		}
		return false;
	}

	public Point getAveragePredatorAndPrey(Tile[][] grid) {
		Point toMove = new Point(0, 0);
		Point[] directions = directionalArray(grid);

		/*
		 * [-][+][-] [+][0][-] [+][+][-]
		 */

		int x = (directions[0].x + directions[1].x + directions[2].x
				+ directions[3].x + directions[4].x + directions[5].x
				+ directions[6].x + directions[7].x) / 7; // average x
		int y = (directions[0].y + directions[1].y + directions[2].y
				+ directions[3].y + directions[4].y + directions[5].y
				+ directions[6].y + directions[7].y) / 7; // average y

		// average point away from predators and towards food.
		toMove.x = x;
		toMove.y = y;

		return toMove;
	}

	public Point[] directionalArray(Tile[][] grid) {
		Directions d = new Directions();
		Point moveDirection[] = new Point[8];

		if (localx + 1 < grid.length && grid[localx + 1][localy] != null) {
			if (isPredator(grid[localx + 1][localy].Occupant)) {
				moveDirection[0] = new Point(-d.get("NORTH").x,
						-d.get("NORTH").y);
			} else if (isFood(grid[localx + 1][localy].Occupant)) {
				moveDirection[0] = d.get("NORTH");
			} else
				moveDirection[0] = d.get("NOWHERE");
		} else
			moveDirection[0] = d.get("NOWHERE");

		if (localx + 1 < grid.length && localy + 1 < grid.length
				&& grid[localx + 1][localy + 1] != null) {
			if (isPredator(grid[localx + 1][localy + 1].Occupant)) {
				moveDirection[1] = new Point(-d.get("NORTHEAST").x,
						-d.get("NORTHEAST").y);
			} else if (isFood(grid[localx + 1][localy + 1].Occupant)) {
				moveDirection[1] = d.get("NORTHEAST");
			} else
				moveDirection[1] = d.get("NOWHERE");
		} else
			moveDirection[1] = d.get("NOWHERE");

		if (localx + 1 < grid.length && localy + 1 < grid.length
				&& grid[localx + 1][localy + 1] != null) {
			if (isPredator(grid[localx + 1][localy].Occupant)) {
				moveDirection[2] = new Point(-d.get("EAST").x, -d.get("EAST").y);
			} else if (isFood(grid[localx + 1][localy].Occupant)) {
				moveDirection[2] = d.get("EAST");
			} else
				moveDirection[2] = d.get("NOWHERE");
		} else
			moveDirection[2] = d.get("NOWHERE");

		if (localx + 1 < grid.length && localy - 1 > 0
				&& grid[localx + 1][localy - 1] != null) {
			if (isPredator(grid[localx + 1][localy - 1].Occupant)) {
				moveDirection[3] = new Point(-d.get("SOUTHEAST").x,
						-d.get("SOUTHEAST").y);
			} else if (isFood(grid[localx + 1][localy - 1].Occupant)) {
				moveDirection[3] = d.get("SOUTHEAST");
			} else
				moveDirection[3] = d.get("NOWHERE");
		} else
			moveDirection[3] = d.get("NOWHERE");

		if (localy - 1 > 0 && grid[localx][localy - 1] != null) {
			if (isPredator(grid[localx][localy - 1].Occupant)) {
				moveDirection[4] = new Point(-d.get("SOUTH").x,
						-d.get("SOUTH").y);
				;
			} else if (isFood(grid[localx][localy - 1].Occupant)) {
				moveDirection[4] = d.get("SOUTH");
			} else
				moveDirection[4] = d.get("NOWHERE");
		} else
			moveDirection[4] = d.get("NOWHERE");

		if (localx - 1 > 0 && localy - 1 > 0
				&& grid[localx - 1][localy - 1] != null) {
			if (isPredator(grid[localx - 1][localy - 1].Occupant)) {
				moveDirection[5] = new Point(-d.get("SOUTHWEST").x,
						-d.get("SOUTHWEST").y);
			} else if (isFood(grid[localx - 1][localy - 1].Occupant)) {
				moveDirection[5] = d.get("SOUTHWEST");
			} else
				moveDirection[5] = d.get("NOWHERE");
		} else
			moveDirection[5] = d.get("NOWHERE");

		if (localx - 1 > 0 && grid[localx - 1][localy] != null) {
			if (isPredator(grid[localx - 1][localy].Occupant)) {
				moveDirection[6] = new Point(-d.get("WEST").x, -d.get("WEST").y);
			} else if (isFood(grid[localx - 1][localy].Occupant)) {
				moveDirection[6] = d.get("WEST");
			} else
				moveDirection[6] = d.get("NOWHERE");
		} else
			moveDirection[6] = d.get("NOWHERE");

		if (localx - 1 > 0 && localy + 1 < grid.length
				&& grid[localx - 1][localy + 1] != null) {
			if (isPredator(grid[localx - 1][localy + 1].Occupant)) {
				moveDirection[7] = new Point(-d.get("NORTHWEST").x,
						-d.get("NORTHWEST").y);
			} else if (isFood(grid[localx - 1][localy + 1].Occupant)) {
				moveDirection[7] = d.get("NORTHWEST");
			} else
				moveDirection[7] = d.get("NOWHERE");
		} else
			moveDirection[7] = d.get("NOWHERE");

		return moveDirection;
	}

	public void Die() {
		this.alive = false;
	}

	public Tile[] convertToTiles(Tile[][] grid) {
		Point[] surroundPoints = directionalArray(grid);
		Tile[] surroundTiles = new Tile[8];

		for (int i = 0; i < 8; i++) {
			try {
				surroundTiles[i] = grid[this.localx + surroundPoints[i].x][this.localy
						+ surroundPoints[i].y];// outside grid sometimes
			} catch (ArrayIndexOutOfBoundsException e) {

			}
		}
		return surroundTiles;
	}

	public boolean Breed(Tile[][] grid) {
		Tile[] tiles = convertToTiles(grid);
		boolean surrounded = false;
		for (int i = 0; i < 8; i++) {
			if (tiles[i] != null) {
				if (tiles[i].Occupant != null) {
					if (tiles[i].Occupant.equals(this.species)) { // so null
						surrounded = true;
					}
				}
			}
		}
		return surrounded;
	}

	public abstract void onEaten(LifeForm eating);

	public void isDead() {
		if (healthLeft == 0) {
			this.Die();
		}
	}

	public void Age() {
		LifeSpan--;
	}

	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect(this.localx * 5, this.localy * 5, 6, 6);
	}
}
