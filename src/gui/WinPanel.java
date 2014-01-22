package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class WinPanel extends JPanel{
	public WinPanel(Dimension d){
		super();
		setPreferredSize(d);
		setMaximumSize(d);
		setMinimumSize(d);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		BufferedImage background = null;
		try {
			background = ImageIO.read(new File("src/resources/WinScreen.png")); //TODO make sure path to win screen is correct
		} catch (IOException e) {
			System.out.println("Unable to find Win Screen.");
			e.printStackTrace();
		}
		g.drawImage(background, 0, 0, null);
	}
}
