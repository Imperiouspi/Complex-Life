import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;


public class LabelButton extends JLabel{
	BufferedImage image;
	
	public LabelButton(String imagePath){
		image = null;
		try {
			image = ImageIO.read(new File("imagePath"));
		} catch (IOException e) {
			System.out.println("Unable to find Image.");
			e.printStackTrace();
		}
	}
	
	@Override
	public void paintComponent(Graphics g){
		g.drawImage(image, 0, 0, null);
	}
}
