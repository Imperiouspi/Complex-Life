package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import lifeForms.animals.Horse;
import lifeForms.animals.Lion;
import lifeForms.animals.MountainGoat;
import types.Biome;
import types.World;
import biomes.Mountains;
import biomes.Plains;

public class aWindow extends JFrame {
	BackgroundPanel back;
	LabelButton play, options;
	QuitButton quit;
	static World world;
	WorldPanel WorldlyPanel;
	public Timer time;
	public LifeFormInfoScreen informations;
	public SetPanel set;
	public NewsPanel news;
	Component[] setCom;
	Component[] infoCom;
	Component[] infoComPanelOpenSave;
	boolean isPause;
	static boolean newWorld = true;
	public int score;
	public static int count = 0;
	public static SpeciesSetComponents lionSet;
	public static SpeciesSetComponents horseSet;
	public static SpeciesSetComponents mGoatSet;

	public aWindow() {
		super("Complex Life");
		world = new World(6, 10);
		back = new BackgroundPanel(new Dimension(world.Lands.length * 100,
				world.Lands.length * 100));

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
		
		lionSet = new SpeciesSetComponents ("Lion");
		horseSet = new SpeciesSetComponents ("Horse");
		mGoatSet = new SpeciesSetComponents ("Mountain Goat");
	}

	public void play() {
		for (int i = 0; i < setCom.length; i++) {
			setCom[i].setEnabled(false);
		}
		for (int i = 0; i < infoCom.length; i++) {
			infoCom[i].setEnabled(false);
		}
		for (int i = 0; i < infoComPanelOpenSave.length; i++) {
			infoComPanelOpenSave[i].setEnabled(false);
		}
		time = new Timer();
		time.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				world.advance(lionSet, horseSet, mGoatSet);
				count++;
				System.gc();
				repaint();
				WorldlyPanel.repaint();
				if (world.Life.size() > 0) {
					score++;
				}
				informations.setScore(score);
				news.setNumbers();
				checkWin();
				news.getNews();
			}

		}, 100L, 100L);
	}

	public void pause() {
		for (int i = 0; i < setCom.length; i++) {
			setCom[i].setEnabled(true);
		}
		for (int i = 0; i < infoCom.length; i++) {
			infoCom[i].setEnabled(true);
		}
		for (int i = 0; i < infoComPanelOpenSave.length; i++) {
			infoComPanelOpenSave[i].setEnabled(true);
		}
		if (time != null)
			time.cancel();
	}

	@Deprecated
	public static void save(File file) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(file);
		} catch (FileNotFoundException e) {
			System.out.println("File not created");
			e.printStackTrace();
		}

		writer.write(world.Lands.length);

		for (int i = 0; i < world.grid.length; i++) {
			for (int j = 0; j < world.grid[i].length; j++) {
				writer.write(world.grid[i][j].x);
				writer.write(world.grid[i][j].y);
			}
		}
		for (int n = 0; n < world.Lands.length; n++) {
			for (int m = 0; m < world.Lands[n].length; m++) {
				for (int k = 0; n < 20; n++) {
					for (int l = 0; l < 20; l++) {
						writer.write(world.grid[(n * 20) + k][(m * 20) + l].location
								.toString());
					}
				}
			}
		}
		// color, isOccupied, Occupant

		for (int i = 0; i < world.grid.length; i++) {
			for (int j = 0; j < world.grid[i].length; j++) {
				writer.write(world.grid[i][j].color.getRed());
				writer.write(world.grid[i][j].color.getGreen());
				writer.write(world.grid[i][j].color.getBlue());
				writer.write(world.grid[i][j].Occupant.toString());

			}
		}
		writer.close();
	}

	@Deprecated
	public static void loadFile(File file) throws NumberFormatException,
	IOException {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			System.out.println("File not found in loading");
			e.printStackTrace();
		}

		int length = Integer.parseInt(br.readLine());
		world.Lands = new Biome[length][length];

		for (int i = 0; i < world.grid.length; i++) {
			for (int j = 0; j < world.grid[i].length; j++) {
				world.grid[i][j].x = Integer.parseInt(br.readLine());
				world.grid[i][j].y = Integer.parseInt(br.readLine());
			}
		}

		for (int n = 0; n < world.Lands.length; n++) {
			for (int m = 0; m < world.Lands[n].length; m++) {
				for (int k = 0; n < 20; n++) {
					for (int l = 0; l < 20; l++) {
						world.grid[n * 20 + k][m * 20 + l].location = getBiome(
								br.readLine(), n * 20 + k, m * 20 + l);
					}
				}
				world.Lands[n][m] = world.grid[n * 20][m * 20].location;
			}
		}
		for (int i = 0; i < world.grid.length; i++) {
			for (int j = 0; j < world.grid[i].length; j++) {
				world.grid[i][j].color = new Color(Integer.parseInt(br
						.readLine()), Integer.parseInt(br.readLine()),
						Integer.parseInt(br.readLine()));
				world.grid[i][j].Occupant = World.creature(br.readLine(), i, j);
			}
		}

		world.Life.clear();
		for (int i = 0; i < world.grid.length; i++) {
			for (int j = 0; j < world.grid[i].length; j++) {
				world.Life.add(world.grid[i][j].Occupant);
			}
		}
	}

	public static Biome getBiome(String name, int x, int y) {
		switch (name) {
		case "Plains":
			return new Plains(x, y);
		case "Mountains":
			return new Mountains(x, y);
		}
		return null;
	}

	class playAction implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			setSize(1000, 1000);
			setLayout(new BorderLayout());
			back.setVisible(false);
			repaint();

			set = new SetPanel();
			set.setEnabled(false);
			set.apocalypse.addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent e) {
					world.Apocalypse();
					repaint();
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
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseExited(MouseEvent e) {

				}

			});
			score = 0;
			set.playPause.addMouseListener(new PauseAction());
			add(set, BorderLayout.WEST);

			informations = new LifeFormInfoScreen();
			informations.setBackground(set.getBackground());
			add(informations, BorderLayout.EAST);

			setCom = set.getComponents();
			infoCom = informations.getComponents();
			infoComPanelOpenSave = informations.openSave.getComponents();

			WorldlyPanel = new WorldPanel(world);
			WorldlyPanel.addMouseListener(new worldClickAction());
			add(WorldlyPanel, BorderLayout.CENTER);

			news = new NewsPanel();
			add(news, BorderLayout.SOUTH);

			isPause = true;
			Color col = Color.black;
			switch (World.creature((String)(set.animals.getSelectedItem()), 0, 0).species) {
			case "Lion": col = Lion.colour; break;
			case "Horse": col = Horse.colour; break;
			case "Mountain Goat": col = MountainGoat.colour; break;
			}
			set.setBackground(col);
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
					&& world.grid[e.getX() / 5][e.getY() / 5].Occupant != null) {
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
				repaint();
			} else {
				pause();
				isPause = true;
				set.setEnabled(true);
				Color col = Color.black;
				switch (World.creature((String)(set.animals.getSelectedItem()), 0, 0).species) {
				case "Lion": col = Lion.colour; break;
				case "Horse": col = Horse.colour; break;
				case "Mountain Goat": col = MountainGoat.colour; break;
				}
				set.setBackground(col);
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
				image2 = ImageIO.read(new File(
						"src/resources/PlayPauseSelected.png"));
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

	public static int getHorses() {
		int horses = 0;
		for (int i = 0; i < world.Life.size(); i++) {
			if (world.Life.get(i).species.equals("Horse")) {
				horses++;
			}
		}
		return horses;
	}

	public static int getLions() {
		int lions = 0;
		for (int i = 0; i < world.Life.size(); i++) {
			if (world.Life.get(i).species.equals("Lion")) {
				lions++;
			}
		}
		return lions;
	}

	public static int getGoats() {
		int goats = 0;
		for (int i = 0; i < world.Life.size(); i++) {
			if (world.Life.get(i).species.equals("Mountain Goat")) {
				goats++;
			}
		}
		return goats;
	}

	public static String getNews() {
		String news = "";
		if (newWorld) {
			newWorld = false;
			news += "WORLD CREATED!\t\n";
		}
		if (getHorses() == 0) {
			news += "HORSES WENT EXTINCT!\t\n";
		}
		if (getLions() == 0) {
			news += "LIONS WENT EXTINCT!\t\n";
		}
		if (getGoats() == 0) {
			news += "GOATS WENT EXTINCT!\t\n";
		}
		return news;
	}

	public boolean checkWin() {
		boolean win = false;
		if (getHorses() + getLions() + getGoats() == 0) {
			win = true;
			time.cancel();
			this.setVisible(false);
			new WinScreen(score);
		}

		return win;
	}
}