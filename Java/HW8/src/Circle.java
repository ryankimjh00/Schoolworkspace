import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Circle extends JFrame {

	public Circle() {
		setTitle("Paint Ex_2191298김종현");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(new CirclePanel());
		this.setBounds(400, 200, 380, 420);
		setVisible(true);
		JMenuBar mb = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		mb.add(fileMenu);
		fileMenu.add(new JMenuItem("Save"));
		this.setJMenuBar(mb);
	}
}