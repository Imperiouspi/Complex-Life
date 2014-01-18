import gui.BackgroundPanel;
import gui.LabelButton;
import gui.LifeFormInfoScreen;
import gui.QuitButton;
import gui.WorldPanel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import types.World;

public class aWindow extends JFrame {
	BackgroundPanel back;
	LabelButton play, options;
	QuitButton quit;
	static World world;
	WorldPanel WorldlyPanel;

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

		quit = new QuitButton("src/resources/Quit.png");
		c.weightx = 0.5;
		c.anchor = GridBagConstraints.LINE_END;
		c.weighty = 0.5;
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(100, 900, 0, 40);
		quit.addMouseListener(new quitAction());
		back.add(quit, c);

		add(back);
	}

	public void play() {
		back.setVisible(false);
		repaint();
		world = new World(6, 10);
		WorldlyPanel = new WorldPanel(world);
		WorldlyPanel.addMouseListener(new worldClickAction());
		add(WorldlyPanel);
		Timer time = new Timer();
		time.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				world.advance();
				WorldlyPanel.repaint();
			}

		}, 100L, 100L);
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
				play.setImage(ImageIO.read(new File(
						"src/resources/PlayMoused.png")));
				play.repaint();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			try {
				play.setImage(ImageIO.read(new File("src/resources/Play.png")));
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
				options.setImage(ImageIO.read(new File(
						"src/resources/optionsMoused.png")));
				options.repaint();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			try {
				options.setImage(ImageIO.read(new File(
						"src/resources/options.png")));
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
				quit.setImage(ImageIO.read(new File(
						"src/resources/QuitMoused.png")));
				quit.repaint();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			try {
				quit.setImage(ImageIO.read(new File("src/resources/Quit.png")));
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

	class worldClickAction implements MouseListener {
		public worldClickAction() {
			super();
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (world.grid[e.getX() / 5][e.getY() / 5].isOccupied) {
				LifeFormInfoScreen window2 = new LifeFormInfoScreen(
						world.grid[e.getX() / 5][e.getY() / 5].Occupant);
				window2.setVisible(true);
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}
	}
}