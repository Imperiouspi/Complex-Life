package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileFilter;

import lifeForms.plants.Grass;
import types.LifeForm;

public class LifeFormInfoScreen extends JPanel {
	JTextArea Info;
	public JButton open;
	public JButton save;
	public JTextArea score;
	public JPanel openSave;
	public JLabel speed_L;
	public JSlider speed_Sl;
	public AnimalSlide animalPic;
	JPanel speedPanel;

	public LifeFormInfoScreen() {
		super();
		setPreferredSize(new Dimension(180, 600));
		setMaximumSize(new Dimension(180, 600));
		setMinimumSize(new Dimension(180, 600));
		setLayout(new GridLayout(0, 1));
		
		score = new JTextArea(0, 10);
		score.setEditable(false);
		add(score);
		
		JPanel speedPanel = new JPanel();
		speed_L = new JLabel("Speed:");
		speedPanel.add(speed_L);
		
		speed_Sl = new JSlider(0, 1000, 100);
		speed_Sl.setPaintLabels(true);
		speed_Sl.setPaintTicks(true);
		speed_Sl.setMajorTickSpacing(200);
		
		speed_Sl.addMouseMotionListener(new MouseMotionListener(){

			@Override
			public void mouseDragged(MouseEvent e) {
				aWindow.speed = (long)(speed_Sl.getValue() + 1);
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		speedPanel.add(speed_Sl);

		add(speedPanel);
		
		animalPic = new AnimalSlide();
		add(animalPic);

		
		Info = new JTextArea();
		Info.setEditable(false);
		add(Info);
		
		//openSave = new JPanel();
		
		/*open = new JButton(new openAction());
		openSave.add(open);
		save = new JButton(new saveAction());
		openSave.add(save);*/

		//add(openSave);
	}

	public void setAnimal(LifeForm life) {
		animalPic.setAnimal(life);
		Info.setText(life.species + ":\nHealth: " + life.healthLeft
				+ "\nHunger: " + life.hungerLeft + "\nAge: " + (life.maxLife - life.LifeSpan) + "\nx: " + life.localx + "\ny: " + life.localy);
		Color col = Color.black;
		switch (life.species) {
		case "Lion":
			col = new Color (aWindow.lionSet.R_Sl.getValue(), aWindow.lionSet.G_Sl.getValue(), aWindow.lionSet.B_Sl.getValue()); break;
		case "Horse":
			col = new Color (aWindow.horseSet.R_Sl.getValue(), aWindow.horseSet.G_Sl.getValue(), aWindow.horseSet.B_Sl.getValue()); break;
		case "Mountain Goat":
			col = new Color (aWindow.mGoatSet.R_Sl.getValue(), aWindow.mGoatSet.G_Sl.getValue(), aWindow.mGoatSet.B_Sl.getValue()); break;
		case "Grass":
			col = Grass.colour; break;
		}
		Info.setBackground(col);
		if (life.species.equals("Horse")) {
			Info.setForeground(Color.white);
		} else {
			Info.setForeground(Color.black);
		}
	}

	public void setScore(int scoreNum) {
		score.setText((String) ("Score: " + scoreNum));
	}

	@Deprecated
	class openAction extends AbstractAction {
		public openAction() {
			super("Open");
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			JFileChooser openFile = new JFileChooser();
			openFile.addChoosableFileFilter(new FileFilter() {

				@Override
				public String getDescription() {
					return null;
				}

				@Override
				public boolean accept(File f) {
					if (f.isDirectory())
						return true;

					String extension = getExtension(f);
					if (extension != null)
						if (extension.equals("world")) {
							return true;
						}

					return false;
				}

				public String getExtension(File file) {
					String ext = null;
					String s = file.getName();
					int i = s.lastIndexOf('.');

					if (i > 0 && i < s.length() - 1) {
						ext = s.substring(i + 1).toLowerCase();
					}
					return ext;
				}
			});

			File opened;

			int returnVal = openFile
					.showOpenDialog(new JFrame("Choose a world"));

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				opened = openFile.getSelectedFile();
				try {
					aWindow.loadFile(opened);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	@Deprecated
	class saveAction extends AbstractAction {
		public saveAction() {
			super("Save");
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			File save = new File(getSaveLocation().getAbsolutePath() + ".world");
			aWindow.save(save);
		}
	}

	private File getSaveLocation() {
		JFileChooser saveFile = new JFileChooser();
		saveFile.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int result = saveFile.showSaveDialog(this);

		if (result == JFileChooser.APPROVE_OPTION) {
			return saveFile.getSelectedFile();
		} else {
			return null;
		}
	}
}