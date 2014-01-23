import gui.aWindow;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 * The main class.
 * @author Noah
 *
 */
public class Birth {
	public static aWindow window;

	public static void main(String[] args) {
		window = new aWindow();
		window.setVisible(true);
		JFrame Help = new JFrame();
		JTextArea helpText = new JTextArea("How to play:"
			+"\n-select an animal from the combo box."
			+"\n-Adjust the RGB sliders to change the color of the species."
			+"\n-The chance of breeding is the percent chance that an animal will breed."
			+"\n- The cool down is how long the animal has to wait before breeding again."
			+"\n- The longer the delay slider is set for, the larger the delay between movements"
			+"\n- Press apocalypse to kill everything except one animal"
			+"\n- At the bottom is displayed the number of animals left, and some news based on what is happening in the world.");
		helpText.setEditable(false);
		Help.add(helpText);
		Help.setSize(new Dimension(500, 200));
		Help.setLocationRelativeTo(null);
		Help.setVisible(true);
	}
}