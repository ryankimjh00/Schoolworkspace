import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StopWatch extends JFrame{
	JFrame frame;
	JButton b_start, b_pause, b_reset;
	JLabel l_min, l_colon1, sec, l_colon2, msec;
	public StopWatch() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		frame = new JFrame("StopWatch");
		buildGUI();
		frame.setBounds(100, 100, 500, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	public void buildGUI() {
		frame.add(CreateSouthPanel(), BorderLayout.SOUTH);
	}
	private JPanel CreateSouthPanel() {
		JPanel southPanel = new JPanel(new BorderLayout());
		southPanel.add(makeButton());
		return southPanel;
	}
	
	public JPanel makeButton() {
		JPanel btnpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		b_start = new JButton("START");
		b_pause = new JButton("STOP");
		b_reset = new JButton("RESET");
		btnpanel.add(b_start);
		btnpanel.add(b_pause);
		btnpanel.add(b_reset);
		return btnpanel;
	}
	
	public JPanel makeClock() {
		return null;
	}
	
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if(src == b_start) {
			b_start.setEnabled(true);
			b_pause.setEnabled(false);
			b_reset.setEnabled(false);
		}
	}
}
