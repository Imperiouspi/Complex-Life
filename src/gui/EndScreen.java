package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class EndScreen extends JPanel {
	BufferedImage img;
	public EndScreen(int score) {
		super();
		JTextArea txtArea = new JTextArea("GAME OVER.\nYour Score: "
				+ score);
		txtArea.setFont(new Font("Papyrus", Font.PLAIN, 20));
		txtArea.setEditable(false);
		add(txtArea, BorderLayout.CENTER);
	}
	
	@Override
	public void paintComponent(Graphics g){
		img = null;
		try {
			img = ImageIO.read(new File("src/resources/Win.png"));
		} catch (IOException e) {
			System.out.println("Unable to find Image.");
			e.printStackTrace();
		}
		g.drawImage(img, 0, 0, null);
		setSize(945, 631);
	}
}