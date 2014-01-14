package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class LabelButton extends JPanel{
	public BufferedImage image;
	
	public LabelButton(String imagePath){
		super();
		setMaximumSize(new Dimension(127, 50));
		setPreferredSize(new Dimension(127, 50));
		setMinimumSize(new Dimension(127, 50));
		
		image = null;
		try {
			image = ImageIO.read(new File(imagePath));
		} catch (IOException e) {
			System.out.println("Unable to find Image.");
			e.printStackTrace();
		}
	}
	
	@Override
	public void paintComponent(Graphics g){
		g.drawImage(image, 0, 0, null);
	}
	
	public BufferedImage getImage () {
		return image;
	}
	
	public void setImage (BufferedImage pic) {
		image = pic;
	}
}
