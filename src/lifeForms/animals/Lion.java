package lifeForms.animals;

import gui.aWindow;

import java.awt.Color;

import types.LifeForm;
import types.Tile;

public class Lion extends LifeForm {
	public static Color staticColor;
	public Lion(int x, int y) {
		super(x, y);
		species = "Lion";
		MaxHealth = 30;
		MaxHunger = 50;
		healthLeft = MaxHealth;
		hungerLeft = MaxHunger;
		eats = new String[] { "Horse", "Mountain Goat" };
		predators = new String[] {};
		color = Color.yellow;
		LifeSpan = 10;
		localx = x;
		localy = y;
		breedChance = 1;
		maxLife = LifeSpan;
		staticColor = this.color;
	}
	@Override
	public Tile[][]Move(Tile[][]grid){
		grid = super.Move(grid);
		cannibal();
		return grid;
	}
	public void cannibal(){
		if(aWindow.getHorses() + aWindow.getGoats() == 0){
			eats = new String[] {"Horse", "Mountain Goat", "Lion"};
		}
	}

	@Override
	public void onEaten(LifeForm eating) {

	}
}
