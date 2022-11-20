import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.PointerInfo;

import javax.swing.JFrame;

public class MakeCircle extends JFrame{
	public MakeCircle() {
		super("MakeCircle");
		//buildGUI();
		this.setBounds(400, 200, 380, 420);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void buildGUI() {
		MakeCircle drawingCanvas = new MakeCircle();
		add(drawingCanvas);
	}
	
    @Override
    public void paint(Graphics g) {
    	PointerInfo pt = MouseInfo.getPointerInfo();
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.red);
        System.out.println(pt.getLocation().x);
        System.out.print(pt.getLocation().y);
        g2d.fillOval(pt.getLocation().x, pt.getLocation().y, 50, 50);
        

    }

}
