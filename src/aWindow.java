import javax.swing.JButton;
import javax.swing.JFrame;

public class aWindow extends JFrame {
	MenuPane MenuPanel;

	public aWindow() {
		super("Complex Life");
		setSize(1000, 625);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		add(new BackgroundPanel());
		MenuPanel = new MenuPane();
		MenuPanel.setOpaque(false);
		//add(MenuPanel);
		MenuPanel.setBounds(700, 0, 300, 600);
		MenuPanel.add(new JButton("Blerg"));
	}
}