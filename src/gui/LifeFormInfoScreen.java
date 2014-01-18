package gui;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import types.LifeForm;

public class LifeFormInfoScreen extends JPanel {
	JTextArea Info;
	public LifeFormInfoScreen(LifeForm life){
		super();
		Info = new JTextArea(life.species + ":\nHealth: " + life.healthLeft + "\nHunger: " + life.hungerLeft);
		Info.setBackground(life.color);
		Info.setEditable(false);
		add(Info);
	}
}