import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Circle extends JFrame {

	public Circle() {
		setTitle("Paint Ex_2191298김종현");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(new CirclePanel());
		this.setBounds(400, 200, 380, 420);
		setVisible(true);
	}
}