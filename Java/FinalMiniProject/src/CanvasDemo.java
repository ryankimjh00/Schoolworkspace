import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

public class CanvasDemo {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				buildGUI();
			}
		});
	}

	private static void buildGUI() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame("2191298김종현");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PaintPanel paintpanel = new PaintPanel();
		frame.add(paintpanel, BorderLayout.EAST);
		frame.pack();
		frame.setVisible(true);
		JMenuBar menuBar;
		JMenu menu = new JMenu("File");
		JMenuItem save = new JMenuItem("save"); 
		JMenuItem load = new JMenuItem("load");
		menuBar = new JMenuBar(); 
		menuBar.add(menu);
		menu.add(save); 
		menu.add(load);
		frame.setJMenuBar(menuBar);

	}
}





