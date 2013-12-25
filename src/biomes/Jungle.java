package biomes;

import java.awt.Color;
import java.awt.Dimension;

import lifeForms.animals.Lion;
import lifeForms.plants.VenusFlytrap;
import types.Biome;
import types.LifeForm;
import types.Plant;

public class Jungle extends Biome {

	public Jungle(int x, int y) {
		super("Jungle", new Plant[]{new VenusFlytrap()}, new LifeForm[]{new Lion()}, new Dimension(x, y), new Color(0, 100, 0));
	}
}
