package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ApocalypsePane extends JPanel{
	BufferedImage image;
	public ApocalypsePane(String imagePath){
		super();
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
	public void paintComponent(Graphics g){
		g.drawImage(image, 0, 0, null);
	}
}
