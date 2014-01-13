package gui;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class BackgroundPanel extends JPanel{
	public BackgroundPanel(){
		super();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		BufferedImage background = null;
		try {
			background = ImageIO.read(new File("src/resources/BackgroundWithText.png"));
		} catch (IOException e) {
			System.out.println("Unable to find Background Image.");
			e.printStackTrace();
		}
		g.drawImage(background, 0, 0, null);
	}
}
