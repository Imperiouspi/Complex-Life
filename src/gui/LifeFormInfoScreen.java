package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileFilter;

import types.LifeForm;

public class LifeFormInfoScreen extends JPanel {
	JTextArea Info;
	public JButton open;
	public JButton save;
	public JTextArea score;

	public LifeFormInfoScreen() {
		super();
		setPreferredSize(new Dimension(200, 1000));
		setMaximumSize(new Dimension(200, 1000));
		setMinimumSize(new Dimension(200, 1000));

		open = new JButton(new openAction());
		add(open);
		save = new JButton(new saveAction());
		add(save);
		score = new JTextArea(0, 10);
		add(score);
	}

	public void setAnimal(LifeForm life) {
		Info = new JTextArea(life.species + ":\nHealth: " + life.healthLeft
				+ "\nHunger: " + life.hungerLeft);
		Info.setBackground(life.color);
		Info.setEditable(false);
		add(Info);
	}

	public void setScore(int scoreNum) {
		score.setText((String) (scoreNum + ""));
	}

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

			int returnVal = openFile.showOpenDialog(new JFrame(
					"Choose a world"));

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

	class saveAction extends AbstractAction {
		public saveAction() {
			super("Save");
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			File save = new File(getSaveLocation().getAbsolutePath()
					+ ".world");
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