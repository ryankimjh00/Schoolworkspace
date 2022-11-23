import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class JDialLogTest extends JFrame {

	public JDialLogTest() {
		super("Jdiallog test");
		JFrame.setDefaultLookAndFeelDecorated(true);
		buildGUI();
		setBounds(120, 120, 300, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void buildGUI() {
		setLayout(new FlowLayout());
		
	}

}
