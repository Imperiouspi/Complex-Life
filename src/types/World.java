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

/**
 * The world.
 * @author Noah
 *
 */
public class World {
	public ArrayList<LifeForm> Life; //All lifeforms on the grid
	public Biome[][] Lands; //the grid of biomes
	public Tile[][] grid; //the grid of tiles.

	public World(int landnumber, int density) {
		// create biomes
		grid = new Tile[landnumber * 20][landnumber * 20];
		Lands = new Biome[landnumber][landnumber];
		Life = new ArrayList<LifeForm>();
		LifeForm occupy;
		//randomly generate biomes
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
		// populate with animals
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
						grid[i][j].Occupant = occupy;
						grid[i][j].Occupant.localx = i;
						grid[i][j].Occupant.localy = j;
						Life.add(occupy);
					}
				}
			}
		}
	}

	//jungle and oceans disabled.
	//randomly get a biome. 2 in 3 chance for plains. 1 in three for mountains.
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

	//finds an animal with which to populate the grid.
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

	//determines what type of LifeForm a creature is when given the string representation of the name.
	public static LifeForm creature(String species, int x, int y) { //most deprecated.
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
			if (!Life.get(i).isDead()) {
				if (i < Life.size()) //move everything
					grid = Life.get(i).Move(grid);
				boolean breedingEnabled = true;
				int breedCooldown = 0;
				//determine what will breed.
				if (i < Life.size()) {
					switch (Life.get(i).species) { //find values that will determine whether the animals will breed based on the species.
					case "Lion":
						breedingEnabled = aWindow.lionSet.trueBreed
								.isSelected();
						aWindow.lionSet.currentCooldown--;
						breedCooldown = aWindow.lionSet.currentCooldown;
						break;
					case "Horse":
						breedingEnabled = aWindow.horseSet.trueBreed
								.isSelected();
						aWindow.horseSet.currentCooldown--;
						breedCooldown = aWindow.horseSet.currentCooldown;
						break;
					case "Mountain Goat":
						breedingEnabled = aWindow.mGoatSet.trueBreed
								.isSelected();
						aWindow.mGoatSet.currentCooldown--;
						breedCooldown = aWindow.mGoatSet.currentCooldown;
						break;
					}
					if (i < Life.size()) {
						if (breedingEnabled && Life.get(i).willBreed //if the animal is on the same square as its own kind, and if the species as a whole can breed.
								&& (breedCooldown == 0 || breedCooldown == -1)) {
							if (i < Life.size())
								Life.get(i).Breed(this);
							if (i < Life.size())
								Life.get(i).willBreed = false;
							if (i < Life.size()) {
								switch (Life.get(i).species) {
								case "Lion":
									aWindow.lionSet.currentCooldown = aWindow.lionSet.cool_Sl
											.getValue();
									break;
								case "Horse":
									aWindow.horseSet.currentCooldown = aWindow.horseSet.cool_Sl
											.getValue();
									break;
								case "Mountain Goat":
									aWindow.mGoatSet.currentCooldown = aWindow.mGoatSet.cool_Sl
											.getValue();
									break;
								}
							}
						}
						if (breedCooldown == 0 || breedCooldown == -1) {//if the cooldown on breeding is done, reset it.
							if (i < Life.size()) {
								switch (Life.get(i).species) {
								case "Lion":
									aWindow.lionSet.currentCooldown = aWindow.lionSet.cool_Sl
											.getValue();
									break;
								case "Horse":
									aWindow.horseSet.currentCooldown = aWindow.horseSet.cool_Sl
											.getValue();
									break;
								case "Mountain Goat":
									aWindow.mGoatSet.currentCooldown = aWindow.mGoatSet.cool_Sl
											.getValue();
									break;
								}
							}
						}

						if (aWindow.count == 10) {//age the animals
							if (i == Life.size() - 1)
								aWindow.count = 0;
							if (i < Life.size()) {
								if (!(Life.get(i) instanceof Grass))
									Life.get(i).Age();
							}
						}
						if (i < Life.size()) { //check if any are dead.
							if (Life.get(i).isDead()) {
								if (i < Life.size())
									i = kill(i);
							}
						}
					}
				}
			}
		}
	}

	//kills the specified animal in the Life array
	public int kill(int i) {
		if (!(Life.get(i) instanceof Grass)) { //if it's not grass
			if (i < Life.size())
				Life.get(i).Die();//kill it
			if (i < Life.size())
				grid[Life.get(i).localx][Life.get(i).localy].Occupant = null;//the space it occupied is free
			if (i < Life.size())
				Life.remove(i);//remove from the arraylist
			i--; // Otherwise the next LifeForm in Life is skipped, because this one was removed.
		}
		return i;

	}

	//kill all animals exept one, but make sure that survivor is not grass, because that goes on forever.
	public void Apocalypse() {
		int index = (int) (Math.random() * Life.size());
		while (Life.get(index).species == "Grass") //while the survivor is grass, get a new one.
			index = (int) (Math.random() * Life.size());
		LifeForm survivor = Life.get(index);
		for (int i = 0; i < Life.size(); i++) {
			Life.get(i).Die();
			grid[Life.get(i).localx][Life.get(i).localy].Occupant = null;
			Life.remove(i);
			i--;
		}
		survivor.alive = true;
		grid[survivor.localx][survivor.localy].Occupant = survivor;
		Life.add(survivor);
	}
}
