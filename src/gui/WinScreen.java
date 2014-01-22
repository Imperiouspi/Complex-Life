package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class WinScreen extends JFrame {
	public WinScreen(int score) {
		super("YOU WIN");
		add(new WinPanel(new Dimension(945, 531)), BorderLayout.NORTH);
		JTextArea txtArea = new JTextArea("Your Score: " + score);
		txtArea.setFont(new Font("Papyrus", Font.PLAIN, 20));
		txtArea.setEditable(false);
		add(txtArea, BorderLayout.SOUTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(945, 611);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}