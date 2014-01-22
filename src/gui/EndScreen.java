package gui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class EndScreen extends JFrame {
 public EndScreen(int score){
 	super("END");
 	JTextArea txtArea = new JTextArea("Sorry, You Lose.\nYour Score: " + score);
 	txtArea.setFont(new Font("Papyrus", Font.PLAIN, 20));
 	txtArea.setEditable(false);
 	add(txtArea, BorderLayout.CENTER);
 	setDefaultCloseOperation(EXIT_ON_CLOSE);
 	setSize(200, 100);
 	setLocationRelativeTo(null);
 	setVisible(true);
 }
}