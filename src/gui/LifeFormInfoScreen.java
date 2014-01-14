package gui;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import types.LifeForm;

public class LifeFormInfoScreen extends JFrame {
	JTextArea Info;
	public LifeFormInfoScreen(LifeForm life){
		super(life.species);
		Info = new JTextArea(life.species + ":\nHealth: " + life.healthLeft + "\nHunger: " + life.hungerLeft);
		Info.setBackground(life.color);
		add(Info);
		pack();
	}
}