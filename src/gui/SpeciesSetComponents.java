package gui;

import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JSlider;

import lifeForms.animals.Horse;
import lifeForms.animals.Lion;
import lifeForms.animals.MountainGoat;

//the components to set the values of the species
public class SpeciesSetComponents {
	public JSlider R_Sl;
	public JSlider B_Sl;
	public JSlider G_Sl;
	public ButtonGroup breedGroup;
	public JRadioButton trueBreed;
	public JRadioButton falseBreed;
	public JSlider chanceBreed_Sl;
	public JSlider cool_Sl;
	public int currentCooldown;
	
	public SpeciesSetComponents (String species) {
		Color col = Color.black;
		int breedChance = 1;
		int breedCooldown = 3;
		switch (species) {
		case "Lion":
			col = Lion.colour;
			breedChance = Lion.statBreedChance;
			breedCooldown = Lion.statBreedCooldown; break;
		case "Horse":
			col = Horse.colour;
			breedChance = Horse.statBreedChance;
			breedCooldown = Horse.statBreedCooldown; break;
		case "Mountain Goat":
			col = MountainGoat.colour;
			breedChance = MountainGoat.statBreedChance;
			breedCooldown = MountainGoat.statBreedCooldown; break;
		}
		
		R_Sl = new JSlider(0, 255);
		R_Sl.setMajorTickSpacing(50);
		R_Sl.setPaintTicks(true);
		R_Sl.setPaintLabels(true);
		R_Sl.setValue(col.getRed());
		
		G_Sl = new JSlider(0, 255);
		G_Sl.setMajorTickSpacing(50);
		G_Sl.setPaintTicks(true);
		G_Sl.setPaintLabels(true);
		G_Sl.setValue(col.getGreen());
		
		B_Sl = new JSlider(0, 255);
		B_Sl.setMajorTickSpacing(50);
		B_Sl.setPaintTicks(true);
		B_Sl.setPaintLabels(true);
		B_Sl.setValue(col.getBlue());
		
		breedGroup = new ButtonGroup();
		trueBreed = new JRadioButton("True", true);
		breedGroup.add(trueBreed);
		falseBreed = new JRadioButton("False");
		breedGroup.add(falseBreed);
		
		chanceBreed_Sl = new JSlider(0, 100);
		chanceBreed_Sl.setMajorTickSpacing(20);
		chanceBreed_Sl.setPaintLabels(true);
		chanceBreed_Sl.setPaintTicks(true);
		chanceBreed_Sl.setValue(breedChance);
		
		cool_Sl = new JSlider(0, 1000);
		cool_Sl.setMajorTickSpacing(500);
		cool_Sl.setPaintTicks(true);
		cool_Sl.setPaintLabels(true);
		cool_Sl.setValue(breedCooldown);
		currentCooldown = breedCooldown;
	}
}
