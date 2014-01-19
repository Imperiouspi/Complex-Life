package gui;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import types.LifeForm;

public class LifeFormInfoScreen extends JPanel {
	JTextArea Info;
	public JButton open;
	public JButton save;
	public JTextArea score;
	
	public LifeFormInfoScreen(){
		super();
		setPreferredSize(new Dimension(200, 1000));
		setMaximumSize(new Dimension(200, 1000));
		setMinimumSize(new Dimension(200, 1000));
		
		open = new JButton("open");
		add(open);
		save = new JButton("save");
		add(save);
		score = new JTextArea(0, 10);
		add(score);
	}
	
	public void setAnimal(LifeForm life){
		Info = new JTextArea(life.species + ":\nHealth: " + life.healthLeft + "\nHunger: " + life.hungerLeft);
		Info.setBackground(life.color);
		Info.setEditable(false);
		add(Info);
	}
	
	public void setScore(int scoreNum){
		score.setText((String)(scoreNum + ""));
	}
}