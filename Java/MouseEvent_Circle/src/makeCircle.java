import java.awt.BorderLayout;
import java.awt.Graphics;

import javax.swing.JFrame;

public class makeCircle extends JFrame {
	public makeCircle(){
		super("makeCiecle");
		
		buildGUI();
		this.setBounds(400, 200, 380, 420);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	private void buildGUI() {
		
	}
	public void paint(Graphics g) {
		g.drawArc(x, y, w, h, startAngle, arcAngle)
	}
}
