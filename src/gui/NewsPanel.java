package gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class NewsPanel extends JPanel {
	public JTextArea animals, news;
	public int horseNum, lionNum, goatNum;
	public NewsPanel(){
		super();
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
		animals = new JTextArea();
		setNumbers();
		add(animals, BorderLayout.WEST);
		
		news = new JTextArea();
		getNews();
		add(news, BorderLayout.EAST);
	}
	
	public void setNumbers(){
		horseNum = aWindow.getHorses();
		lionNum = aWindow.getLions();
		goatNum = aWindow.getGoats();
		animals.setText("Horses: " + horseNum + "\nLions: " + lionNum + "\nMountain Goats: " + goatNum);
	}
	
	public void getNews(){
		news.setText(aWindow.getNews());
	}
}
