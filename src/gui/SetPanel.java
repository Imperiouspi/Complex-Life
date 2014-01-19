package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import lifeForms.animals.Lion;
import types.World;

public class SetPanel extends JPanel{
	public PauseButton playPause;
	public JComboBox<String> animals;
	public JLabel C_L;
	public JLabel R_L;
	public JSlider R_Sl;
	public JLabel B_L;
	public JSlider B_Sl;
	public JLabel G_L;
	public JSlider G_Sl;
	public ButtonGroup breedGroup;
	public JRadioButton trueBreed;
	public JRadioButton falseBreed;
	public JLabel chanceBreed_L;
	public JSlider chanceBreed_Sl;
	public JLabel cool_L;
	public JSlider cool_Sl;
	public JButton open;
	public JButton save;
	public ApocalypsePane apocalypse;
	
	public SetPanel(){
		super();
		setPreferredSize(new Dimension(200, 1000));
		setMaximumSize(new Dimension(200, 1000));
		setMinimumSize(new Dimension(200, 1000));
		this.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
		
		setLayout(new GridLayout(0, 1));
		
		playPause = new PauseButton("src/resources/PlayPause.png");
		add(playPause);
		
		add(new JSeparator(SwingConstants.HORIZONTAL));
		
		animals = new JComboBox<String>(new String[]{"Lion", "Mountain Goat", "Horse"});
		animals.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				setBackground(World.creature((String)(animals.getSelectedItem()), 0, 0).color);
				if(((String)(animals.getSelectedItem())).equals("Horse")){
					Component[] com = getComponents();
					for(int i = 3; i < com.length; i++){
						com[i].setForeground(Color.white);
					}
				}
				else{
					Component[] com = getComponents();
					for(int i = 3; i < com.length; i++){
						com[i].setForeground(Color.black);
					}
				}
			}
			
		});
		add(animals);
		C_L = new JLabel("Color:");
		add(C_L);
		
		R_L = new JLabel("R: ");
		add(R_L);
		R_Sl = new JSlider(0, 255);
		R_Sl.setMajorTickSpacing(50);
		R_Sl.setPaintTicks(true);
		R_Sl.setPaintLabels(true);
		R_Sl.setValue(Lion.staticColor.getRed());
		add(R_Sl);
		
		G_L = new JLabel("G: ");
		add(G_L);
		G_Sl = new JSlider(0, 255);
		G_Sl.setMajorTickSpacing(50);
		G_Sl.setPaintTicks(true);
		G_Sl.setPaintLabels(true);
		R_Sl.setValue(Lion.staticColor.getBlue());
		add(G_Sl);
		
		B_L = new JLabel("B: ");
		add(B_L);
		B_Sl = new JSlider(0, 255);
		B_Sl.setMajorTickSpacing(50);
		B_Sl.setPaintTicks(true);
		B_Sl.setPaintLabels(true);
		B_Sl.setValue(Lion.staticColor.getGreen());
		add(B_Sl);
		
		add(new JSeparator(SwingConstants.HORIZONTAL));
		
		add(new JLabel("Breeding: "));
		
		breedGroup = new ButtonGroup();
		trueBreed = new JRadioButton("True", true);
		breedGroup.add(trueBreed);
		add(trueBreed);
		
		falseBreed = new JRadioButton("False");
		breedGroup.add(falseBreed);
		add(falseBreed);
		
		chanceBreed_L = new JLabel("Chance of Breeding: ");
		add(chanceBreed_L);
		
		chanceBreed_Sl = new JSlider(0, 100);
		chanceBreed_Sl.setMajorTickSpacing(10);
		chanceBreed_Sl.setPaintLabels(true);
		chanceBreed_Sl.setPaintTicks(true);
		chanceBreed_Sl.setValue((World.creature((String)(animals.getSelectedItem()), 0, 0).breedChance));
		add(chanceBreed_Sl);
		
		cool_L = new JLabel("Cooldown: ");
		add(cool_L);
		
		cool_Sl = new JSlider(0, 1000);
		cool_Sl.setMajorTickSpacing(100);
		cool_Sl.setPaintTicks(true);
		cool_Sl.setPaintLabels(true);
		cool_Sl.setValue((World.creature((String)(animals.getSelectedItem()), 0, 0).breedCooldown));
		add(cool_Sl);
		
		add(new JSeparator(SwingConstants.HORIZONTAL));
		
		open = new JButton();
		add(open);
		save = new JButton();
		add(save);
		
		apocalypse = new ApocalypsePane();
		add(apocalypse);
	}
}