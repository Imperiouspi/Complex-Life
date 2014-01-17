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

	public LifeForm Eat(LifeForm eaten) {
		eaten.onEaten(this);
		eaten.Die();
		this.hungerLeft = MaxHunger;
		return eaten;
	}

	public Tile[][] Move(Tile[][] grid) {
		// if no prey or predators around, move randomly.
		Point moveTo = new Point(0, 0);
		Tile[][] seen = new Tile[5][5];

		int a = 0, b = 0;
		for (int i = -2; i <= 2; i++, a++) {
			for (int j = -2; j <= 2; j++, b++) {
				try {
					seen[a][b] = grid[localx + i][localy + j];
				} catch (NullPointerException e) {

				} catch (ArrayIndexOutOfBoundsException e) {

				}
			}
		}
		moveTo = getPoint(getAveragePredatorAngle(seen)
				+ getAverageFoodAngle(seen));

		if (moveTo.x == 0 && moveTo.y == 0) { // if it hasn't moved
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
		} else {
			if(localx + moveTo.x > 0 && localx + moveTo.x < grid.length){
				localx += moveTo.x;
			}
			if(localy + moveTo.y > 0 && localy + moveTo.y < grid.length){
				localy += moveTo.y;
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

	public int getAveragePredatorAngle(Tile[][] seen) {
		int angle = 0;
		int predatorCount = 0;

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (seen[i][j] != null && seen[i][j].Occupant != null) {
					if (isPredator(seen[i][j].Occupant)) {
						angle += getAngle(seen, i, j);
						predatorCount++;
					}
				}
			}
		}

		if (predatorCount != 0) {
			if (angle / predatorCount > 0 && angle / predatorCount < 180) {
				return angle / predatorCount + 180;
			} else {
				if (angle / predatorCount < 0) {
					angle = 360 - angle;
				}
				return angle / predatorCount - 180;
			}
		} else {
			return 0;
		}
	}

	public int getAverageFoodAngle(Tile[][] seen) {
		int angle = 0;
		int foodCount = 0;

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (seen[i][j] != null && seen[i][j].Occupant != null) {
					if (isFood(seen[i][j].Occupant)) {
						angle += getAngle(seen, i, j);
						foodCount++;
					}
				}
			}
		}
		if (foodCount != 0) {
			if (angle / foodCount > 0 && angle / foodCount < 180) {
				return angle / foodCount + 180;
			} else {
				if (angle / foodCount < 0) {
					angle = 360 - angle;
				}
				return angle / foodCount - 180;
			}
		} else {
			return 0;
		}
	}

	public int getAngle(Tile[][] seen, int x, int y) {
		switch (y) {
		case 0:
			switch (x) {
			case 0:
				return 135;
			case 1:
				return 112;
			case 2:
				return 90;
			case 3:
				return 67;
			case 4:
				return 45;
			}
		case 1:
			switch (x) {
			case 0:
				return 157;
			case 1:
				return 135;
			case 2:
				return 90;
			case 3:
				return 45;
			case 4:
				return 22;
			}
		case 2:
			switch (x) {
			case 0:
				return 180;
			case 1:
				return 180;
			case 2:
				return 0;
			case 3:
				return 0;
			case 4:
				return 0;
			}
		case 3:
			switch (x) {
			case 0:
				return 157 + 180;
			case 1:
				return 135 + 180;
			case 2:
				return 270;
			case 3:
				return 45 + 180;
			case 4:
				return 22 + 180;
			}
		case 4:
			switch (x) {
			case 0:
				return 135 + 180;
			case 1:
				return 112 + 180;
			case 2:
				return 270;
			case 3:
				return 67 + 180;
			case 4:
				return 45 + 180;
			}
		}
		return 0;
	}

	public Point getPoint(int angle) {
		if (angle < 0) {
			angle = 360 - angle;
		}
		Point moveTo = new Point(0, 0);

		// 22-67
		if (22 <= angle && angle < 67) {
			return new Point(1, 1);
		}
		// 67-112
		if (67 <= angle && angle < 112) {
			return new Point(1, 0);
		}
		// 112-157
		if (112 <= angle && angle < 157) {
			return new Point(1, -1);
		}
		// 157-202
		if (157 <= angle && angle < 202) {
			return new Point(0, -1);
		}
		// 202-247
		if (202 <= angle && angle < 247) {
			return new Point(-1, -1);
		}
		// 247-292
		if (237 <= angle && angle < 292) {
			return new Point(-1, 0);
		}
		// 292-337
		if (292 <= angle && angle < 337) {
			return new Point(1, -1);
		}
		// 337-22
		if (337 <= angle && angle < 22) {
			return new Point(0, 1);
		}

		return moveTo;
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
