package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
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
	public Timer time;
	public LifeFormInfoScreen informations;
	SetPanel set;
	Component[] setCom;
	boolean isPause;

	public aWindow() {
		super("Complex Life");
		world = new World(6, 10);
		back = new BackgroundPanel(new Dimension(world.Lands.length*100, world.Lands.length*100));

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
		for(int i = 0; i < setCom.length; i++){
			setCom[i].setEnabled(false);
		}
		time = new Timer();
		time.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				world.advance();
				System.gc();
				repaint();
				WorldlyPanel.repaint();
			}

		}, 100L, 100L);
	}

	public void pause() {
		for(int i = 0; i < setCom.length; i++){
			setCom[i].setEnabled(true);
		}
		if(time !=null)
			time.cancel();
	}

	class playAction implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			setSize(1000, 800);
			setLayout(new BorderLayout());
			back.setVisible(false);
			repaint();

			set = new SetPanel();
			set.setEnabled(false);
			set.playPause.addMouseListener(new PauseAction());
			add(set, BorderLayout.WEST);
			informations = new LifeFormInfoScreen();
			informations.addMouseListener(new Click());
			add(informations);
			setCom = set.getComponents();

			WorldlyPanel = new WorldPanel(world);
			WorldlyPanel.addMouseListener(new worldClickAction());
			add(WorldlyPanel, BorderLayout.CENTER);

			isPause = true;
			set.setBackground(World.creature((String)(set.animals.getSelectedItem()), 0, 0).color);
			pause();
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			try {
				play.setImage(ImageIO.read(new File(
						"src/resources/PlayMoused.png")));
				play.repaint();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			try {
				play.setImage(ImageIO.read(new File("src/resources/Play.png")));
				play.repaint();
			} catch (IOException e) {
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
			if (e.getX() > 0 && e.getX() < 600 && e.getY() > 0
					&& e.getY() < 600
					&& world.grid[e.getX() / 5][e.getY() / 5].isOccupied) {
				informations
				.setAnimal(world.grid[e.getX() / 5][e.getY() / 5].Occupant);
				repaint();
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

	class PauseAction implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (isPause) {
				play();
				isPause = false;
				set.setEnabled(false);
			} else {
				pause();
				isPause = true;
				set.setEnabled(true);
				set.setBackground(World.creature((String)(set.animals.getSelectedItem()), 0, 0).color);
				repaint();
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {

		}

		@Override
		public void mouseReleased(MouseEvent e) {

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			BufferedImage image2 = null;
			try {
				image2 = ImageIO.read(new File("src/resources/PlayPauseSelected.png"));
			} catch (IOException e2) {
				System.out.println("Unable to find Image.");
				e2.printStackTrace();
			}
			set.playPause.setImage(image2);
			repaint();
		}

		@Override
		public void mouseExited(MouseEvent e) {
			BufferedImage image = null;
			try {
				image = ImageIO.read(new File("src/resources/PlayPause.png"));
			} catch (IOException e2) {
				System.out.println("Unable to find Image.");
				e2.printStackTrace();
			}
			set.playPause.setImage(image);
			repaint();
		}

	}

	class Click implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			System.out.println("TO TEST");
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
			System.out.println("IN");

		}

		@Override
		public void mouseExited(MouseEvent e) {
			System.out.println("OUT");

		}

	}
}