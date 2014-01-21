package gui;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class EndScreen extends JFrame {
 public EndScreen(int score){
 	super("END");
 	add(new JTextArea("Your Score: " + score));
 	setDefaultCloseOperation(EXIT_ON_CLOSE);
 	setSize(100, 100);
 	setVisible(true);
 }
}