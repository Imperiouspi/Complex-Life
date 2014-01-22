package gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import types.LifeForm;

public class AnimalSlide extends JPanel{
	LifeForm animal;
	BufferedImage img;
	public AnimalSlide(){
		super();
	}

	public void setAnimal(LifeForm life){
		animal = life;
		try {
			img = ImageIO.read(new File("src/resources/" + animal.species + ".png"));
		} catch (IOException e) {
			System.out.println("Unable to find Image.");
			e.printStackTrace();
		}
	}

	@Override
	public void paintComponent(Graphics g){
		if(img != null){
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);
		}
	}
}