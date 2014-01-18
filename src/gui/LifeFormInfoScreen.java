package gui;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import types.LifeForm;

public class LifeFormInfoScreen extends JPanel {
	JTextArea Info;
	public LifeFormInfoScreen(){
		super();
		setPreferredSize(new Dimension(200, 600));
		setMaximumSize(new Dimension(200, 600));
		setMinimumSize(new Dimension(200, 600));
	}
	
	public void setAnimal(LifeForm life){
		Info = new JTextArea(life.species + ":\nHealth: " + life.healthLeft + "\nHunger: " + life.hungerLeft);
		Info.setBackground(life.color);
		Info.setEditable(false);
		add(Info);
	}
}