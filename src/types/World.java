package types;

import gui.aWindow;

import java.util.ArrayList;

import lifeForms.animals.Deer;
import lifeForms.animals.Horse;
import lifeForms.animals.Lion;
import lifeForms.animals.MountainGoat;
import lifeForms.animals.Rabbit;
import lifeForms.animals.Wolf;
import lifeForms.plants.DeadGrass;
import lifeForms.plants.Grass;
import lifeForms.plants.VenusFlytrap;
import biomes.Mountains;
import biomes.Plains;

public class World {
	public ArrayList<LifeForm> Life;
	public Biome[][] Lands;
	public Tile[][] grid;

	public World(int landnumber, int density) {
		// create biomes
		grid = new Tile[landnumber * 20][landnumber * 20];
		Lands = new Biome[landnumber][landnumber];
		Life = new ArrayList<LifeForm>();
		LifeForm occupy;
		for (int i = 0; i < landnumber; i++) {
			for (int j = 0; j < landnumber; j++) {
				Lands[i][j] = worldBiomeGen(i, j);
				for (int l = 0; l < 20; l++) {
					for (int m = 0; m < 20; m++) {
						grid[i * 20 + l][j * 20 + m] = new Tile(i + l, j + m,
								Lands[i][j].color, Lands[i][j]);
					}
				}
			}
		}
		// populate
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				int spawnChance = (int) ((Math.random() * 100) + 1);
				if (spawnChance <= density) {
					// spawn living things on tiles
					int animalOrPlant = (int) (Math.random() * 2);
					if (animalOrPlant == 0) {
						occupy = populate(grid[i][j], "Plant", i, j);

					} else {
						occupy = populate(grid[i][j], "Animal", i, j);
					}
					if (occupy != null) {
						grid[i][j].Occupant.add(occupy);
						grid[i][j].Occupant.get(grid[i][j].Occupant.size() - 1).localx = i;
						grid[i][j].Occupant.get(grid[i][j].Occupant.size() - 1).localy = j;
						Life.add(occupy);
					}
				}
			}
		}
	}

	private Biome worldBiomeGen(int i, int j) {
		Biome biome = null;
		int random = (int) (Math.random() * 3) + 1;
		/*
		 * if (random == 0) { biome = new Jungle(i, j); }
		 */
		if (random == 1 || random == 3) {
			biome = new Plains(i, j);
		}
		if (random == 2) {
			biome = new Mountains(i, j);
		}
		/*
		 * if (random == 3) { biome = new Ocean(i, j); }
		 */

		return biome;
	}

	public static LifeForm populate(Tile tile, String spawnType, int x, int y) {
		LifeForm Occupant = null;
		int spawnChance;
		if (spawnType.equals("Plant")) {
			spawnChance = (int) (Math.random() * tile.location.foods.length);
			Occupant = creature(tile.location.foods[spawnChance], x, y);
		} else {
			spawnChance = (int) (Math.random() * tile.location.support.length);
			Occupant = creature(tile.location.support[spawnChance], x, y);
		}

		return Occupant;
	}

	public static LifeForm creature(String species, int x, int y) {
		LifeForm living = null;

		switch (species) {
		case "Horse":
			living = new Horse(x, y);
			break;
		case "Lion":
			living = new Lion(x, y);
			break;
		case "Mountain Goat":
			living = new MountainGoat(x, y);
			break;
		case "Rabbit":
			living = new Rabbit(x, y);
			break;
		case "DeadGrass":
			living = new DeadGrass(x, y);
			break;
		case "Grass":
			living = new Grass(x, y);
			break;
		case "VenusFlytrap":
			living = new VenusFlytrap(x, y);
			break;
		case "Deer":
			living = new Deer(x, y);
			break;
		case "Wolf":
			living = new Wolf(x, y);
			break;
		}

		return living;
	}

	public void advance() {
		for (int i = 0; i < Life.size(); i++) {
			grid = Life.get(i).Move(grid);
			boolean breedingEnabled = true;
			int breedCooldown = 0;
			switch (Life.get(i).species) {
			case "Lion":
				breedingEnabled = Lion.breedEnabled;
				breedCooldown = Lion.statBreedCooldown;
				Lion.statBreedCooldown--; break;
			case "Horse":
				breedingEnabled = Horse.breedEnabled;
				breedCooldown = Horse.statBreedCooldown;
				Horse.statBreedCooldown--; break;
			case "Mountain Goat":
				breedingEnabled = MountainGoat.breedEnabled;
				breedCooldown = MountainGoat.statBreedCooldown;
				MountainGoat.statBreedCooldown--; break;
			}
			if (breedingEnabled && Life.get(i).willBreed && breedCooldown == 0) {
				Life.get(i).Breed(this);
				Life.get(i).willBreed = false;
				switch (Life.get(i).species) {
				case "Lion": Lion.statBreedCooldown = 10; break;
				case "Horse": Horse.statBreedCooldown = 3; break;
				case "Mountain Goat": MountainGoat.statBreedCooldown = 3; break;
				}
			}
			if (breedCooldown == 0) {
				switch (Life.get(i).species) {
				case "Lion": Lion.statBreedCooldown = 10; break;
				case "Horse": Horse.statBreedCooldown = 3; break;
				case "Mountain Goat": MountainGoat.statBreedCooldown = 3; break;
				}
			}
			
			if (Life.get(i).isDead()) {
				Life.get(i).Die();
				grid[Life.get(i).localx][Life.get(i).localy].Occupant.remove(grid[Life.get(i).localx][Life.get(i).localy].Occupant.indexOf(creature(Life.get(i).species, Life.get(i).localx, Life.get(i).localx)));
				Life.remove(i);
			}
			if(aWindow.count == 10){
				aWindow.count = 0;
				Life.get(i).Age();
			}
		}
	}

	public void Apocalypse() {
		for (int i = 0; i < Life.size(); i++) {
			Life.get(i).Die();
			grid[Life.get(i).localx][Life.get(i).localy].Occupant.clear();;
			Life.remove(i);
		}
	}
}
