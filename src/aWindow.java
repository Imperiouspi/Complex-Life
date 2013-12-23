import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class aWindow extends JFrame {
	BackgroundPanel back;
	LabelButton play, options;

	public aWindow() {
		super("Complex Life");
		back = new BackgroundPanel();

		setSize(1000, 625);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		back.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;

		play = new LabelButton("src/resources/Play.png");
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(100, 800, 100, 100);		
		back.add(play, c);
		
		options = new LabelButton("src/resources/Play.png");
		c.weightx = 0.5;
		c.anchor = GridBagConstraints.LINE_END;
		c.weighty = 0.5;
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(100, 800, 100, 100);
		back.add(options, c);
		add(back);
	}
}