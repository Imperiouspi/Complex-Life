package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * The panel to click to summon an apocalypse. Leaves one animal alive.
 * @author Noah
 *
 */
public class ApocalypsePane extends JPanel{
	BufferedImage image;
	public ApocalypsePane(String imagePath){
		super();
		//set size.
		setPreferredSize(new Dimension(200, 50));
		setMinimumSize(new Dimension(200, 50));
		setMaximumSize(new Dimension(200, 50));
		setBounds(0,0,200, 50);
		image = null;
		try {
			image = ImageIO.read(new File(imagePath));
		} catch (IOException e) {
			System.out.println("Unable to find Apocalypse Image.");
			e.printStackTrace();
		}
	}
	
	@Override
	public void paintComponent(Graphics g){ //draw picture
		g.drawImage(image, 0, 0, null);
	}
}
