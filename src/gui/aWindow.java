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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import types.Biome;
import types.World;
import biomes.Mountains;
import biomes.Plains;

/**
 * The main Window class.
 * @author Noah
 *
 */
public class aWindow extends JFrame {
	BackgroundPanel back;
	LabelButton play, options;
	QuitButton quit;
	public static World world;
	public static WorldPanel WorldlyPanel;
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
	public static Long speed;

	public aWindow() {
		super("Complex Life");
		//init world
		world = new World(6, 10);
		speed = 100L;
		back = new BackgroundPanel(new Dimension(world.Lands.length * 100,
				world.Lands.length * 100)); //background picture.

		//set up the menu.
		setSize(1000, 625);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
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

		quit = new QuitButton("src/resources/Quit2.png");
		c.weightx = 0.5;
		c.anchor = GridBagConstraints.LINE_END;
		c.weighty = 0.5;
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(100, 900, 0, 40);
		quit.addMouseListener(new quitAction());
		back.add(quit, c);

		add(back);

		//set up the default values.
		lionSet = new SpeciesSetComponents("Lion");
		horseSet = new SpeciesSetComponents("Horse");
		mGoatSet = new SpeciesSetComponents("Mountain Goat");
	}

	public void play() { //called when played.
		//disable all components
		for (int i = 0; i < setCom.length; i++) {
			setCom[i].setEnabled(false);
		}
		for (int i = 0; i < infoCom.length; i++) {
			infoCom[i].setEnabled(false);
		}
		/*
		 * for (int i = 0; i < infoComPanelOpenSave.length; i++) {
		 * infoComPanelOpenSave[i].setEnabled(false); }
		 * 
		 * From when open/saving was in the process of being implemented
		 */
		
		//start timer.
		time = new Timer();
		time.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() { //advance the world and values.
				world.advance();
				count++;
				System.gc();
				repaint();
				WorldlyPanel.repaint();
				if (world.Life.size() > 0) {
					score++; //add to the score.
				}
				informations.setScore(score);
				news.setNumbers();
				checkEnd(); //make sure the game didn't end.
				news.getNews(); //update the news.
				repaint();
			}

		}, speed, speed);
	}

	public void pause() {
		for (int i = 0; i < setCom.length; i++) {
			setCom[i].setEnabled(true);
		}
		for (int i = 0; i < infoCom.length; i++) {
			infoCom[i].setEnabled(true);
		}
		/*
		 * for (int i = 0; i < infoComPanelOpenSave.length; i++) {
		 * infoComPanelOpenSave[i].setEnabled(true); }
		 */// from open/saving
		if (time != null) {
			time.cancel(); //stop from running.
			repaint();
		}
	}

	//no longer used
	@Deprecated
	public static void save(File file) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(file))));
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

	//no longer used
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

	//get Biome based on string.
	public static Biome getBiome(String name, int x, int y) {
		switch (name) {
		case "Plains":
			return new Plains(x, y);
		case "Mountains":
			return new Mountains(x, y);
		}
		return null;
	}

	//the action called when play is clicked in the main menu.
	class playAction implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			setSize(1000, 700);
			setLayout(new BorderLayout());
			back.setVisible(false);
			repaint();

			set = new SetPanel(); //add the setpanel
			set.setEnabled(false); //add apocalypse mouselistener
			set.apocalypse.addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent e) {
					world.Apocalypse();
					repaint();
				}

				@Override
				public void mousePressed(MouseEvent e) {
				}

				@Override
				public void mouseReleased(MouseEvent e) {
				}

				@Override
				public void mouseEntered(MouseEvent e) {
				}

				@Override
				public void mouseExited(MouseEvent e) {
				}

			});
			score = 0; //init score
			set.playPause.addMouseListener(new PauseAction());
			add(set, BorderLayout.WEST);

			informations = new LifeFormInfoScreen();
			informations.setBackground(set.getBackground());
			add(informations, BorderLayout.EAST);

			setCom = set.getComponents(); //init arrays of components for easy enable/disabling
			infoCom = informations.getComponents();
			// infoComPanelOpenSave = informations.openSave.getComponents();

			WorldlyPanel = new WorldPanel(world); //init panel with the world.
			WorldlyPanel.addMouseListener(new worldClickAction());
			add(WorldlyPanel, BorderLayout.CENTER);

			//init/add news.
			news = new NewsPanel();
			add(news, BorderLayout.SOUTH);

			isPause = true;
			Color col = Color.black;
			//set colour based on the animal selected from the combo box.
			switch (World.creature((String) (set.animals.getSelectedItem()), 0,
					0).species) {
			case "Lion":
				col = new Color(aWindow.lionSet.R_Sl.getValue(),
						aWindow.lionSet.G_Sl.getValue(),
						aWindow.lionSet.B_Sl.getValue());
				break;
			case "Horse":
				col = new Color(aWindow.horseSet.R_Sl.getValue(),
						aWindow.horseSet.G_Sl.getValue(),
						aWindow.horseSet.B_Sl.getValue());
				break;
			case "Mountain Goat":
				col = new Color(aWindow.mGoatSet.R_Sl.getValue(),
						aWindow.mGoatSet.G_Sl.getValue(),
						aWindow.mGoatSet.B_Sl.getValue());
				break;
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
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
		}

	}

	//called by the options button. Currently does nothing.
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
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
		}

	}

	//exits the game
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
				e.printStackTrace();
			}
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			try {
				quit.setImage(ImageIO.read(new File("src/resources/Quit2.png")));
				quit.repaint();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
		}

	}

	//shows the information of the animal clicked.
	class worldClickAction implements MouseListener {
		public worldClickAction() {
			super();
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getX() > 0 && e.getX() < 600 && e.getY() > 0 //if it is a valid animal and the space is within the world.
					&& e.getY() < 600
					&& world.grid[e.getX() / 5][e.getY() / 5].Occupant != null
					&& world.grid[e.getX() / 5][e.getY() / 5].Occupant.alive) {
				informations
						.setAnimal(world.grid[e.getX() / 5][e.getY() / 5].Occupant); //set the animal to show.
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
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}
	}

	//called when the play/pause button is clicked.
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
				switch (World.creature(
						(String) (set.animals.getSelectedItem()), 0, 0).species) {
				case "Lion":
					col = new Color(aWindow.lionSet.R_Sl.getValue(),
							aWindow.lionSet.G_Sl.getValue(),
							aWindow.lionSet.B_Sl.getValue());
					break;
				case "Horse":
					col = new Color(aWindow.horseSet.R_Sl.getValue(),
							aWindow.horseSet.G_Sl.getValue(),
							aWindow.horseSet.B_Sl.getValue());
					break;
				case "Mountain Goat":
					col = new Color(aWindow.mGoatSet.R_Sl.getValue(),
							aWindow.mGoatSet.G_Sl.getValue(),
							aWindow.mGoatSet.B_Sl.getValue());
					break;
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

	//counts the number of horses
	public static int getHorses() {
		int horses = 0;
		for (int i = 0; i < world.Life.size(); i++) {
			if (world.Life.get(i).species.equals("Horse")) {
				horses++;
			}
		}
		return horses;
	}

	//counts the number of lions
	public static int getLions() {
		int lions = 0;
		for (int i = 0; i < world.Life.size(); i++) {
			if (world.Life.get(i).species.equals("Lion")) {
				lions++;
			}
		}
		return lions;
	}

	//counts the number of goats
	public static int getGoats() {
		int goats = 0;
		for (int i = 0; i < world.Life.size(); i++) {
			if (world.Life.get(i).species.equals("Mountain Goat")) {
				goats++;
			}
		}
		return goats;
	}
	
	//makes the news based on what has happened in the world.
	public static String getNews() {
		String news = "";
		if (newWorld) { //if the world was just created.
			newWorld = false;
			news += "WORLD CREATED!\t\n";
		}
		if (getHorses() == 0) { //if there are no more horses
			news += "HORSES WENT EXTINCT!\t\n";
		}
		if (getLions() == 0) {//if there are no more lions
			news += "LIONS WENT EXTINCT!\t\n";
		}
		if (getGoats() == 0) {//if there are no more goats
			news += "GOATS WENT EXTINCT!\t\n";
		}

		if (getGoats() >= 17000) {//if there are more than 17000 goats.
			news += "Goats are reaching the limit!\t\n";
		}
		if (getLions() >= 17000) {//if there are more than 17000 lions.
			news += "Lions are reaching the limit!\t\n";
		}
		if (getHorses() >= 17000) {//if there are more than 17000 horses.
			news += "Horses are reaching the limit!\t\n";
		}

		if (getGoats() >= 20000) {//if there are more than 20000 goats.
			news += "Goats are at the limit!\t\n";
		}
		if (getLions() >= 20000) {//if there are more than 20000 lions.
			news += "Lions are at the limit!\t\n";
		}
		if (getHorses() >= 20000) {//if there are more than 20000 horses.
			news += "Horses are at the limit!\t\n";
		}
		return news;
	}

	//see if the game should end.
	public boolean checkEnd() {
		boolean win = false;
		if (getHorses() + getLions() + getGoats() == 0) {//if there are no more animals
			win = true;
			time.cancel();
			this.setVisible(false);
			JFrame WINdow = new JFrame("End");
			WINdow.setDefaultCloseOperation(EXIT_ON_CLOSE);
			WINdow.setSize(200, 100);
			WINdow.getContentPane().add(new EndScreen(score));
			WINdow.setSize(945, 531);
			WINdow.setLocationRelativeTo(null);
			WINdow.setVisible(true);
		}
		if (getHorses() >= 20000 || getLions() >= 20000 || getGoats() >= 20000) { //if there are more than 20000 of any animal.
			win = true;
			time.cancel();
			this.setVisible(false);
			JFrame WINdow = new JFrame("End");
			WINdow.setDefaultCloseOperation(EXIT_ON_CLOSE);
			WINdow.getContentPane().add(new EndScreen(score));
			WINdow.setSize(945, 531);
			WINdow.setLocationRelativeTo(null);
			WINdow.setVisible(true);
		}

		return win;
	}
}