package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileFilter;

import lifeForms.animals.Horse;
import lifeForms.animals.Lion;
import lifeForms.animals.MountainGoat;
import types.LifeForm;

public class LifeFormInfoScreen extends JPanel {
	JTextArea Info;
	public JButton open;
	public JButton save;
	public JTextArea score;
	public JPanel openSave;

	public LifeFormInfoScreen() {
		super();
		setPreferredSize(new Dimension(200, 1000));
		setMaximumSize(new Dimension(200, 1000));
		setMinimumSize(new Dimension(200, 1000));
		setLayout(new GridLayout(0, 1));

		openSave = new JPanel();
		
		open = new JButton(new openAction());
		openSave.add(open);
		save = new JButton(new saveAction());
		openSave.add(save);
		score = new JTextArea(0, 10);
		score.setEditable(false);
		add(openSave);
		add(score);
		
		Info = new JTextArea();
		Info.setEditable(false);
		add(Info);
	}

	public void setAnimal(LifeForm life) {
		Info.setText(life.species + ":\nHealth: " + life.healthLeft
				+ "\nHunger: " + life.hungerLeft + "\nAge: " + (life.maxLife - life.LifeSpan));
		Color col = Color.black;
		switch (life.species) {
		case "Lion":
			col = new Color (aWindow.lionSet.R_Sl.getValue(), aWindow.lionSet.G_Sl.getValue(), aWindow.lionSet.B_Sl.getValue()); break;
		case "Horse":
			col = new Color (aWindow.horseSet.R_Sl.getValue(), aWindow.horseSet.G_Sl.getValue(), aWindow.horseSet.B_Sl.getValue()); break;
		case "Mountain Goat":
			col = new Color (aWindow.mGoatSet.R_Sl.getValue(), aWindow.mGoatSet.G_Sl.getValue(), aWindow.mGoatSet.B_Sl.getValue()); break;
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