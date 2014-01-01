import java.awt.Graphics;

import javax.swing.JPanel;

import types.World;

public class worldPanel extends JPanel {
	World world;
	public worldPanel(World place) {
		this.world = place;
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