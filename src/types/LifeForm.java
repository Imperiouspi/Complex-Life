package types;

import gui.aWindow;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import lifeForms.animals.Horse;
import lifeForms.animals.Lion;
import lifeForms.animals.MountainGoat;
import lifeForms.plants.Grass;

/**
 * Represents an abstract LifeForm on the board.
 * @author Noah
 *
 */
public abstract class LifeForm {
	public String species;
	public int MaxHealth, MaxHunger, maxLife;
	public int healthLeft, hungerLeft;
	public String[] eats, predators;
	public int LifeSpan;
	public int localx, localy, /*the coordinates*/ viewDistance = 5;
	public boolean alive, willBreed = false;

	public LifeForm(int x, int y) {
		localx = x;
		localy = y;
		hungerLeft = MaxHunger;
		alive = true;
	}

	public LifeForm Eat(LifeForm eaten) {
		eaten.onEaten(this);
		eaten.Die();
		aWindow.world.kill(aWindow.world.Life.indexOf(eaten));
		this.hungerLeft = MaxHunger;
		return eaten;
	}

	//decides where the lifeform will move
	public Tile[][] Move(Tile[][] grid) {
		Point moveTo = new Point(0, 0);
		Tile[][] seen = new Tile[5][5];

		//make an array of tiles observed.
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
		moveTo = getPoint((getAveragePredatorAngle(seen) + getAverageFoodAngle(seen)) / 2); //find a point based on predators and food.
		
		if (moveTo.x == 0 && moveTo.y == 0) { // if it hasn't moved, choose a random location.
			moveTo = moveRandom(grid);
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
		} else if ((grid[this.localx + moveTo.x][this.localy + moveTo.y].Occupant) != null
				&& !isFood(grid[this.localx + moveTo.x][this.localy + moveTo.y].Occupant)
				&& !isPredator(grid[this.localx + moveTo.x][this.localy
						+ moveTo.y].Occupant)
				&& !grid[this.localx + moveTo.x][this.localy + moveTo.y].Occupant.species
						.equals(this.species)) {//if the space is taken up by an animal that is not interacted with, move
			moveTo = moveRandom(grid);
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
			if (this.localx + moveTo.x > 0
					&& this.localx + moveTo.x < grid.length) {
				this.localx += moveTo.x;
			}
			if (this.localy + moveTo.y > 0
					&& this.localy + moveTo.y < grid.length) {
				this.localy += moveTo.y;
			}
		}
		if (grid[this.localx][this.localy].Occupant != null
				&& isFood(grid[this.localx][this.localy].Occupant)
				&& grid[this.localx][this.localy].Occupant.alive) {
			grid[this.localx][this.localy].Occupant = Eat(grid[this.localx][this.localy].Occupant); //eat it if it's food
		} else if (grid[this.localx][this.localy].Occupant != null
				&& grid[this.localx][this.localy].Occupant.species
						.equals(this.species)
				&& grid[this.localx][this.localy].Occupant.alive) {
			willBreed = true; //if the animal is the same species as the one it will land on
		} else {
			willBreed = false;
			hungerLeft--;
		}

		grid[localx][localy].Occupant = this;
		return grid;
	}

	public Point moveRandom(Tile[][] grid) { //random tile to move to.
		Point moveTo = new Point(0, 0);
		moveTo = new Point((int) (Math.random() * 2) + 1,
				(int) (Math.random() * 2) + 1);
		return moveTo;
	}

	public int getAveragePredatorAngle(Tile[][] seen) {
		int angle = 0;
		int predatorCount = 0;
		//find the average angle of the predators around the animal.
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (seen[i][j] != null && seen[i][j].Occupant != null) {
					if (isPredator(seen[i][j].Occupant)) {
						angle += (int) (Math.asin(Math.sqrt(Math.pow(i
								- this.localx, 2)
								+ Math.pow(j - this.localy, 2))));
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
				//return the opposite direction of the predators.
				return angle / predatorCount - 180;
			}
		} else {
			return 0;
		}
	}

	//get the average angle of the food around the animal.
	public int getAverageFoodAngle(Tile[][] seen) {
		int angle = 0;
		int foodCount = 0;

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (seen[i][j] != null && seen[i][j].Occupant != null) {
					if (isFood(seen[i][j].Occupant)
							|| seen[i][j].Occupant.species.equals(this.species)) {
						angle += (int) (Math.asin(Math.sqrt(Math.pow(i
								- this.localx, 2)
								+ Math.pow(j, this.localy))));
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

	//get an angle from a 5x5 array of tiles.
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

	//get a point from an angle
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

	//find if the specified animal is a predator
	public boolean isPredator(ArrayList<LifeForm> life) {
		for (int j = 0; j < life.size(); j++) {
			if (life.get(j) != null) {
				for (int i = 0; i < this.predators.length; i++) {
					if (this.predators[i] != null) {
						if (this.predators[i].equals(life.get(j).species)) {
							return true;
						}
					} else {
						return false;
					}
				}
				return false;
			}
		}
		return false;
	}

	//find if the specified animal is food.
	public boolean isFood(ArrayList<LifeForm> life) {
		for (int j = 0; j < life.size(); j++) {
			if (life.get(j) != null) {
				for (int i = 0; i < this.eats.length; i++) {
					if (this.eats[i] != null) {
						if (this.eats[i].equals(life.get(j).species)) {
							return true;
						}
					} else {
						return false;
					}
				}
				return false;
			}
		}
		return false;
	}
	
	//find if the specified animal is a predator
	public boolean isPredator(LifeForm life) {
		if (life != null) {
			for (int i = 0; i < this.predators.length; i++) {
				if (this.predators[i] != null) {
					if (this.predators[i].equals(life.species)) {
						return true;
					}
				} else {
					return false;
				}
				return false;
			}
		}
		return false;
	}

	//find if the specified animal is food.
	public boolean isFood(LifeForm life) {
		if (life != null) {
			for (int i = 0; i < this.eats.length; i++) {
				if (this.eats[i] != null) {
					if (this.eats[i].equals(life.species)) {
						return true;
					}
				} else {
					return false;
				}
			}
			return false;
		}
		return false;
	}

	public boolean Breed(World world) {
		//randomly make animals breed based on the chance.
		int breed = (int) (Math.random() * 100);
		int breedChance = 1;
		switch (this.species) {
		case "Lion":
			breedChance = Lion.statBreedChance;
			break;
		case "Horse":
			breedChance = Horse.statBreedChance;
			break;
		case "Mountain Goat":
			breedChance = MountainGoat.statBreedChance;
			break;
		}
		//if there will be an animal, add it.
		if (breed < breedChance) {
			world.Life.add(World.creature(this.species, localx, localy));
			return true;
		}
		return false;
	}

	//handles what happens when an animal is eaten.
	public abstract void onEaten(LifeForm eating);

	//an animal is dead if it's health, hunger, or life left is 0, or if it isn't alive.
	public boolean isDead() {
		if (healthLeft <= 0 || hungerLeft <= 0 || LifeSpan <= 0 || !alive) {
			return true;
		}
		return false;
	}

	//set alive to false.s
	public void Die() {
		this.alive = false;
	}

	//subtract one from LifeSpan.
	public void Age() {
		this.LifeSpan--;
	}

	//draw the animal on the grid.
	public void draw(Graphics g) {
		if (!this.isDead()) {
			Color col = Color.black;
			switch (this.species) {
			case "Lion":
				col = new Color(aWindow.lionSet.R_Sl.getValue(),
						aWindow.lionSet.G_Sl.getValue(),
						aWindow.lionSet.B_Sl.getValue());
				break;
			case "Horse":
				col = new Color(aWindow.horseSet.R_Sl.getValue(),
						aWindow.horseSet.G_Sl.getValue(),
						aWindow.horseSet.B_Sl.getValue());
				break;
			case "Mountain Goat":
				col = new Color(aWindow.mGoatSet.R_Sl.getValue(),
						aWindow.mGoatSet.G_Sl.getValue(),
						aWindow.mGoatSet.B_Sl.getValue());
				break;
			default:
				col = Grass.colour;
				break;
			}
			g.setColor(col);
			g.fillRect(this.localx * 5, this.localy * 5, 6, 6);
		}
	}

	@Override
	public String toString() {
		return species;
	}
}
