import java.awt.Graphics;

import javax.swing.JPanel;

import types.Tile;
import types.World;

@SuppressWarnings("serial")
public class WorldPanel extends JPanel {
	World world;
	public WorldPanel(World world) {
		this.world = world;
		for (int i = 0; i < world.grid.length; i++) {
			for (int j = 0; j < world.grid[i].length; j++) {
				world.grid[i][j] = new Tile(i, j, aWindow.TileColourer(i, j));
			}
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		for (int i = 0; i < world.grid.length; i++) {
			for (int j = 0; j < world.grid[i].length; j++) {
				g.setColor(world.grid[i][j].color);
				g.fillOval(i*5, j*5, 5, 5);
			}
		}
	}
}
