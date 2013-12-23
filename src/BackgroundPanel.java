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
		System.out.println("huh?");
		BufferedImage background = null;
		try {
			background = ImageIO.read(new File("src/resources/background.png"));
		} catch (IOException e) {
			System.out.println("Unable to find Background Image.");
			e.printStackTrace();
		}
		g.drawImage(background, 1000, 625, null);
	}
}
