package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
	public ApocalypsePane apocalypse;
	
	public SetPanel(){
		super();
		setPreferredSize(new Dimension(220, 600));
		setMaximumSize(new Dimension(220, 600));
		setMinimumSize(new Dimension(220, 600));
		this.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
		
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		
		playPause = new PauseButton("src/resources/PlayPause.png");
		c.gridx = 0;
		c.gridy = 0;
		add(playPause, c);
		
		c.gridy++;
		JSeparator separate = new JSeparator(SwingConstants.HORIZONTAL);
		separate.setPreferredSize(new Dimension(separate.getMinimumSize().width, 20));
		separate.setMinimumSize(new Dimension(separate.getMinimumSize().width, 20));
		separate.setMaximumSize(new Dimension(separate.getMinimumSize().width, 20));
		add(separate, c);
		
		animals = new JComboBox<String>(new String[]{"Lion", "Mountain Goat", "Horse"});
		animals.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Color col = Color.black;
				switch (World.creature((String)(animals.getSelectedItem()), 0, 0).species) {
				case "Lion":
					col = new Color (aWindow.lionSet.R_Sl.getValue(), aWindow.lionSet.G_Sl.getValue(), aWindow.lionSet.B_Sl.getValue());
					R_Sl.setValue(aWindow.lionSet.R_Sl.getValue()); G_Sl.setValue(aWindow.lionSet.G_Sl.getValue());
					B_Sl.setValue(aWindow.lionSet.B_Sl.getValue());
					trueBreed.setSelected(aWindow.lionSet.trueBreed.isSelected()); falseBreed.setSelected(aWindow.lionSet.falseBreed.isSelected());
					chanceBreed_Sl.setValue(aWindow.lionSet.chanceBreed_Sl.getValue()); cool_Sl.setValue(aWindow.lionSet.cool_Sl.getValue());
					break;
				case "Horse":
					col = new Color (aWindow.horseSet.R_Sl.getValue(), aWindow.horseSet.G_Sl.getValue(), aWindow.horseSet.B_Sl.getValue());
					R_Sl.setValue(aWindow.horseSet.R_Sl.getValue()); G_Sl.setValue(aWindow.horseSet.G_Sl.getValue());
					B_Sl.setValue(aWindow.horseSet.B_Sl.getValue());
					trueBreed.setSelected(aWindow.horseSet.trueBreed.isSelected());
					falseBreed.setSelected(aWindow.horseSet.falseBreed.isSelected());
					chanceBreed_Sl.setValue(aWindow.horseSet.chanceBreed_Sl.getValue()); cool_Sl.setValue(aWindow.horseSet.cool_Sl.getValue());
					break;
				case "Mountain Goat":
					col = new Color (aWindow.mGoatSet.R_Sl.getValue(), aWindow.mGoatSet.G_Sl.getValue(), aWindow.mGoatSet.B_Sl.getValue());
					R_Sl.setValue(aWindow.mGoatSet.R_Sl.getValue()); G_Sl.setValue(aWindow.mGoatSet.G_Sl.getValue());
					B_Sl.setValue(aWindow.mGoatSet.B_Sl.getValue()); trueBreed.setSelected(aWindow.mGoatSet.trueBreed.isSelected());
					falseBreed.setSelected(aWindow.mGoatSet.falseBreed.isSelected());
					chanceBreed_Sl.setValue(aWindow.mGoatSet.chanceBreed_Sl.getValue()); cool_Sl.setValue(aWindow.mGoatSet.cool_Sl.getValue());
					break;
				}
				setBackground(col);
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
		c.gridy++;
		add(animals, c);
		
		c.gridy++;
		separate = new JSeparator(SwingConstants.HORIZONTAL);
		separate.setPreferredSize(new Dimension(separate.getMinimumSize().width, 20));
		separate.setMinimumSize(new Dimension(separate.getMinimumSize().width, 20));
		separate.setMaximumSize(new Dimension(separate.getMinimumSize().width, 20));
		add(separate, c);
		
		Color col = Color.black;
		int breedChance = 1;
		int breedCooldown = 3;
		switch (World.creature((String)(animals.getSelectedItem()), 0, 0).species) {
		case "Lion":
			col = new Color (aWindow.lionSet.R_Sl.getValue(), aWindow.lionSet.G_Sl.getValue(), aWindow.lionSet.B_Sl.getValue());
			breedChance = aWindow.lionSet.chanceBreed_Sl.getValue();
			breedCooldown = aWindow.lionSet.cool_Sl.getValue(); break;
		case "Horse":
			col = new Color (aWindow.horseSet.R_Sl.getValue(), aWindow.horseSet.G_Sl.getValue(), aWindow.horseSet.B_Sl.getValue());
			breedChance = aWindow.horseSet.chanceBreed_Sl.getValue();
			breedCooldown = aWindow.horseSet.cool_Sl.getValue(); break;
		case "Mountain Goat":
			col = new Color (aWindow.mGoatSet.R_Sl.getValue(), aWindow.mGoatSet.G_Sl.getValue(), aWindow.mGoatSet.B_Sl.getValue());
			breedChance = aWindow.mGoatSet.chanceBreed_Sl.getValue();
			breedCooldown = aWindow.mGoatSet.cool_Sl.getValue(); break;
		}
		
		C_L = new JLabel("Color:");
		c.gridy++;
		add(C_L, c);
		
		R_L = new JLabel("R: ");
		c.gridy++;
		add(R_L, c);
		R_Sl = new JSlider(0, 255);
		R_Sl.setMajorTickSpacing(50);
		R_Sl.setPaintTicks(true);
		R_Sl.setPaintLabels(true);
		R_Sl.setValue(col.getRed());
		R_Sl.addChangeListener(new ColourListener(R_Sl, G_Sl, B_Sl, animals, this, 0));
		c.gridy++;
		add(R_Sl, c);
		
		G_L = new JLabel("G: ");
		c.gridy++;
		add(G_L, c);
		G_Sl = new JSlider(0, 255);
		G_Sl.setMajorTickSpacing(50);
		G_Sl.setPaintTicks(true);
		G_Sl.setPaintLabels(true);
		G_Sl.setValue(col.getBlue());
		G_Sl.addChangeListener(new ColourListener(R_Sl, G_Sl, B_Sl, animals, this, 1));
		c.gridy++;
		add(G_Sl, c);
		
		B_L = new JLabel("B: ");
		c.gridy++;
		add(B_L, c);
		B_Sl = new JSlider(0, 255);
		B_Sl.setMajorTickSpacing(50);
		B_Sl.setPaintTicks(true);
		B_Sl.setPaintLabels(true);
		B_Sl.setValue(col.getGreen());
		B_Sl.addChangeListener(new ColourListener(R_Sl, G_Sl, B_Sl, animals, this, 2));
		c.gridy++;
		add(B_Sl, c);

		c.gridy++;
		separate = new JSeparator(SwingConstants.HORIZONTAL);
		separate.setPreferredSize(new Dimension(separate.getMinimumSize().width, 20));
		separate.setMinimumSize(new Dimension(separate.getMinimumSize().width, 20));
		separate.setMaximumSize(new Dimension(separate.getMinimumSize().width, 20));
		add(separate, c);
		
		c.gridy++;
		add(new JLabel("Breeding: "), c);
		breedGroup = new ButtonGroup();
		trueBreed = new JRadioButton("Enabled", true);
		trueBreed.addItemListener(new BreedEnableListener(trueBreed, animals));
		breedGroup.add(trueBreed);
		falseBreed = new JRadioButton("Disabled");
		breedGroup.add(falseBreed);
		c.gridy++;
		add(trueBreed, c);
		c.gridy++;
		add(falseBreed, c);
		
		chanceBreed_L = new JLabel("Chance of Breeding: ");
		c.gridy++;
		add(chanceBreed_L, c);
		chanceBreed_Sl = new JSlider(0, 100);
		chanceBreed_Sl.setMajorTickSpacing(20);
		chanceBreed_Sl.setPaintLabels(true);
		chanceBreed_Sl.setPaintTicks(true);
		chanceBreed_Sl.setValue(breedChance);
		chanceBreed_Sl.addChangeListener(new SliderListener(chanceBreed_Sl, cool_Sl, animals, 1));
		c.gridy++;
		add(chanceBreed_Sl, c);
		
		cool_L = new JLabel("Cooldown: ");
		c.gridy++;
		add(cool_L, c);
		cool_Sl = new JSlider(0, 1000);
		cool_Sl.setMajorTickSpacing(500);
		cool_Sl.setPaintTicks(true);
		cool_Sl.setPaintLabels(true);
		cool_Sl.setValue(breedCooldown);
		cool_Sl.addChangeListener(new SliderListener(chanceBreed_Sl, cool_Sl, animals, 2));
		c.gridy++;
		add(cool_Sl, c);

		c.gridy++;
		separate = new JSeparator(SwingConstants.HORIZONTAL);
		separate.setPreferredSize(new Dimension(separate.getMinimumSize().width, 20));
		separate.setMinimumSize(new Dimension(separate.getMinimumSize().width, 20));
		separate.setMaximumSize(new Dimension(separate.getMinimumSize().width, 20));
		add(separate, c);
		
		apocalypse = new ApocalypsePane("src/resources/ApocalypseButton.png");
		c.gridy++;
		add(apocalypse, c);
	}
	
	static class ColourListener implements ChangeListener {
		private JSlider R_Sl;
		private JSlider G_Sl;
		private JSlider B_Sl;
		private JComboBox<String> animals;
		private SetPanel setP;
		private int choice;
		
	    public ColourListener(JSlider R, JSlider G, JSlider B, JComboBox<String> comboBox, SetPanel sP, int choices) {
	    	super();
	    	R_Sl = R;
	    	G_Sl = G;
	    	B_Sl = B;
	    	animals = comboBox;
	    	setP = sP;
	    	choice = choices;
	    }
	    
		@Override
		public void stateChanged(ChangeEvent e) {
			if (choice == 0) {
				int newR = R_Sl.getValue();
				Color col = Color.black;
				switch (World.creature((String)(animals.getSelectedItem()), 0, 0).species) {
				case "Lion":
					col = new Color (newR, aWindow.lionSet.G_Sl.getValue(), aWindow.lionSet.B_Sl.getValue());
					aWindow.lionSet.R_Sl.setValue(newR); break;
				case "Horse":
					col = new Color (newR, aWindow.horseSet.G_Sl.getValue(), aWindow.horseSet.B_Sl.getValue());
					aWindow.horseSet.R_Sl.setValue(newR); break;
				case "Mountain Goat":
					col = new Color (newR, aWindow.mGoatSet.G_Sl.getValue(), aWindow.mGoatSet.B_Sl.getValue());
					aWindow.mGoatSet.R_Sl.setValue(newR); break;
				}
				setP.setBackground(col);
			} else if (choice == 1) {
				int newG = G_Sl.getValue();
				Color col = Color.black;
				switch (World.creature((String)(animals.getSelectedItem()), 0, 0).species) {
				case "Lion":
					col = new Color (aWindow.lionSet.R_Sl.getValue(), newG, aWindow.lionSet.B_Sl.getValue());
					aWindow.lionSet.G_Sl.setValue(newG); break;
				case "Horse":
					col = new Color (aWindow.horseSet.R_Sl.getValue(), newG, aWindow.horseSet.B_Sl.getValue());
					aWindow.horseSet.G_Sl.setValue(newG); break;
				case "Mountain Goat":
					col = new Color (aWindow.mGoatSet.R_Sl.getValue(), newG, aWindow.mGoatSet.B_Sl.getValue());
					aWindow.mGoatSet.G_Sl.setValue(newG); break;
				}
				setP.setBackground(col);
			} else if (choice == 2) {
				int newB = B_Sl.getValue();
				Color col = Color.black;
				switch (World.creature((String)(animals.getSelectedItem()), 0, 0).species) {
				case "Lion":
					col = new Color (aWindow.lionSet.R_Sl.getValue(), aWindow.lionSet.G_Sl.getValue(), newB);
					aWindow.lionSet.B_Sl.setValue(newB); break;
				case "Horse":
					col = new Color (aWindow.horseSet.R_Sl.getValue(), aWindow.horseSet.G_Sl.getValue(), newB);
					aWindow.horseSet.B_Sl.setValue(newB); break;
				case "Mountain Goat":
					col = new Color (aWindow.mGoatSet.R_Sl.getValue(), aWindow.mGoatSet.G_Sl.getValue(), newB);
					aWindow.mGoatSet.B_Sl.setValue(newB); break;
				}
				setP.setBackground(col);
			}
			aWindow.WorldlyPanel.repaint();
		}
	}
	
	static class BreedEnableListener implements ItemListener {
		private JRadioButton trueBreed;
		private JComboBox<String> animals;
		
		public BreedEnableListener(JRadioButton trueB, JComboBox<String> comboBox) {
			trueBreed = trueB;
			animals = comboBox;
		}
		
		@Override
		public void itemStateChanged(ItemEvent arg0) {
			boolean breedingEnabled = trueBreed.isSelected();
			switch (World.creature((String)(animals.getSelectedItem()), 0, 0).species) {
			case "Lion":
				aWindow.lionSet.trueBreed.setSelected(breedingEnabled); aWindow.lionSet.falseBreed.setSelected(!breedingEnabled); break;
			case "Horse":
				aWindow.horseSet.trueBreed.setSelected(breedingEnabled); aWindow.horseSet.falseBreed.setSelected(!breedingEnabled); break;
			case "Mountain Goat":
				aWindow.mGoatSet.trueBreed.setSelected(breedingEnabled); aWindow.mGoatSet.falseBreed.setSelected(!breedingEnabled); break;
			}
		}
	}
	
	static class SliderListener implements ChangeListener {
		private JSlider chanceBreed_Sl;
		private JSlider cool_Sl;
		private JComboBox<String> animals;
		private int choice;
		
	    public SliderListener(JSlider sliderCB, JSlider sliderCD, JComboBox<String> comboBox, int choices) {
	    	super();
	    	chanceBreed_Sl = sliderCB;
	    	cool_Sl = sliderCD;
	    	animals = comboBox;
	    	choice = choices;
	    }
	    
		@Override
		public void stateChanged(ChangeEvent e) {
			if (choice == 1) {
				int breedingChance = chanceBreed_Sl.getValue();
				switch (World.creature((String)(animals.getSelectedItem()), 0, 0).species) {
				case "Lion":
					aWindow.lionSet.chanceBreed_Sl.setValue(breedingChance); break;
				case "Horse":
					aWindow.horseSet.chanceBreed_Sl.setValue(breedingChance); break;
				case "Mountain Goat":
					aWindow.mGoatSet.chanceBreed_Sl.setValue(breedingChance); break;
				}
			} else if (choice == 2) {
				int breedingCooldown = cool_Sl.getValue();
				switch (World.creature((String)(animals.getSelectedItem()), 0, 0).species) {
				case "Lion":
					aWindow.lionSet.cool_Sl.setValue(breedingCooldown); break;
				case "Horse":
					aWindow.horseSet.cool_Sl.setValue(breedingCooldown); break;
				case "Mountain Goat":
					aWindow.mGoatSet.cool_Sl.setValue(breedingCooldown); break;
				}
			}
		}
	}
}