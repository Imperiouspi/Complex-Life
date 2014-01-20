package gui;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class WinScreen extends JFrame {
 public WinScreen(int score){
 	super("YOU WIN!");
 	add(new JTextArea("Your Score: " + score));
 	setDefaultCloseOperation(EXIT_ON_CLOSE);
 	setSize(100, 100);
 	setVisible(true);
 }
}