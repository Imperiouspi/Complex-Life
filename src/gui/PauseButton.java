package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * shows the pause image.
 * @author Noah
 *
 */
public class PauseButton extends JPanel {
	BufferedImage image;
	public static boolean enabled;

	public PauseButton(String imagePath) {
		super();
		setPreferredSize(new Dimension(127, 50));
		setMinimumSize(new Dimension(127, 50));
		setMaximumSize(new Dimension(127, 50));
		setBounds(0,0,127, 50);
		setBorder(new EmptyBorder(new Insets(5, 5, 5, 5)));
		
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
