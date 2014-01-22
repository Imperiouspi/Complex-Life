package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import types.World;

public class WorldPanel extends JPanel{
	World world;

	public WorldPanel(World place) {
		this.world = place;
		setPreferredSize(new Dimension(600, 600));
		setMaximumSize(new Dimension(600, 600));
		setMinimumSize(new Dimension(600, 600));
	}

	@Override
	public void paintComponent(Graphics g) {
		for (int i = 0; i < world.grid.length; i++) {
			for (int j = 0; j < world.grid[i].length; j++) {
				g.setColor(world.grid[i][j].color);
				g.fillRect(i * 5, j * 5, 5, 5);
			}
		}

		for (int i = 0; i < world.Life.size(); i++) {
			Color col = Color.black;
			switch (world.Life.get(i).species) {
			case "Lion":
				col = new Color (aWindow.lionSet.R_Sl.getValue(), aWindow.lionSet.G_Sl.getValue(), aWindow.lionSet.B_Sl.getValue()); break;
			case "Horse":
				col = new Color (aWindow.horseSet.R_Sl.getValue(), aWindow.horseSet.G_Sl.getValue(), aWindow.horseSet.B_Sl.getValue()); break;
			case "Mountain Goat":
				col = new Color (aWindow.mGoatSet.R_Sl.getValue(), aWindow.mGoatSet.G_Sl.getValue(), aWindow.mGoatSet.B_Sl.getValue()); break;
			}
			g.setColor(col);
			if(world.Life.get(i).alive)
				world.Life.get(i).draw(g);
		}
	}
}