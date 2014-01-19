package gui;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import types.World;

public class WorldPanel extends JPanel{
	World world;

	public WorldPanel(World place) {
		this.world = place;
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
			g.setColor(world.Life.get(i).color);
			if(world.Life.get(i).alive)
				world.Life.get(i).draw(g);
		}
	}
}