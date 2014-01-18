package gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class SetPanel extends JPanel{
	public PauseButton playPause;
	
	public SetPanel(){
		super();
		setPreferredSize(new Dimension(200, 600));
		setMaximumSize(new Dimension(200, 600));
		setMinimumSize(new Dimension(200, 600));
		this.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
		
		setLayout(new GridLayout(0, 1));
		
		playPause = new PauseButton("src/resources/PlayPause.png");
		add(playPause, 0);
		
		add(new JSeparator(SwingConstants.HORIZONTAL), 1);
		
		JComboBox<String> animals = new JComboBox<String>(new String[]{"Lion", "Mountain Goat", "Horse"});
		add(animals);
		
		JLabel C_L = new JLabel("Color:");
		add(C_L);
		
		JLabel R_L = new JLabel("R: ");
		add(R_L);
		JSlider R_Sl = new JSlider(0, 255);
		R_Sl.setMajorTickSpacing(50);
		R_Sl.setPaintTicks(true);
		R_Sl.setPaintLabels(true);
		add(R_Sl);
		
		JLabel G_L = new JLabel("G: ");
		add(G_L);
		JSlider G_Sl = new JSlider(0, 255);
		G_Sl.setMajorTickSpacing(50);
		G_Sl.setPaintTicks(true);
		G_Sl.setPaintLabels(true);
		add(G_Sl);
		
		JLabel B_L = new JLabel("B: ");
		add(B_L);
		JSlider B_Sl = new JSlider(0, 255);
		B_Sl.setMajorTickSpacing(50);
		B_Sl.setPaintTicks(true);
		B_Sl.setPaintLabels(true);
		add(B_Sl);
	}
}