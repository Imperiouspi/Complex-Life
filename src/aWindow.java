import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import types.World;

public class aWindow extends JFrame {
	BackgroundPanel back;
	LabelButton play, options, quit;
	static World world;

	public aWindow() {
		super("Complex Life");
		back = new BackgroundPanel();

		setSize(1000, 625);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		back.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		play = new LabelButton("src/resources/Play.png");
		c.anchor = GridBagConstraints.LINE_END;
		c.weighty = 0.0;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(100, 800, 100, 50);

		play.addMouseListener(new playAction());
		back.add(play, c);

		options = new LabelButton("src/resources/Options.png");
		c.weightx = 0.5;
		c.anchor = GridBagConstraints.LINE_END;
		c.weighty = 0.0;
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(0, 800, 100, 50);

		options.addMouseListener(new optionsAction());
		back.add(options, c);

		quit = new LabelButton("src/resources/Quit.png");
		c.weightx = 0.5;
		c.anchor = GridBagConstraints.LINE_END;
		c.weighty = 0.5;
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(100, 900, 0, 10);
		quit.addMouseListener(new quitAction());
		back.add(quit, c);

		add(back);
	}

	public void play() {
		back.setVisible(false);
		repaint();
		world = new World(6);
		add(new worldPanel(world));
	}

	public static Color TileColourer(int x, int y) {
		for (int i = world.Lands.length-1; i >= 0; i--) {
			if (x < i * 20) {
				for (int j = world.Lands[i].length-1; j >= 0; j--) {
					if (y < i * 20) {
						return world.Lands[i-1][j-1].color;
					}
				}
			}
		}
		return null;
	}

	class playAction implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Play
			play();
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			try {
				play.image = ImageIO.read(new File(
						"src/resources/PlayMoused.png"));
				play.repaint();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			try {
				play.image = ImageIO.read(new File("src/resources/Play.png"));
				play.repaint();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

	}

	class optionsAction implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Options Tab- Dialogue?
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			try {
				options.image = ImageIO.read(new File(
						"src/resources/optionsMoused.png"));
				options.repaint();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			try {
				options.image = ImageIO.read(new File(
						"src/resources/options.png"));
				options.repaint();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

	}

	class quitAction implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			System.exit(0);
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			try {
				quit.image = ImageIO.read(new File(
						"src/resources/QuitMoused.png"));
				quit.repaint();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			try {
				quit.image = ImageIO.read(new File("src/resources/Quit.png"));
				quit.repaint();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

	}
}