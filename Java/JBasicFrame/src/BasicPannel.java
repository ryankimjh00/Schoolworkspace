import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;

public class BasicPannel {
	private JFrame frame;

	public BasicPannel() {
		frame = new JFrame("Laydout Test");
		buildGUI();
		frame.setSize(500, 600);
		frame.setLocation(400, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	private void buildGUI() {

		JLabel label = new JLabel("Hello World");
		
		frame.add(createInputPanel(), BorderLayout.CENTER);
		frame.add(label, BorderLayout.SOUTH);

	}
	
	private JPanel createInputPanel() {
		JTextField text = new JTextField(10);
		JButton button = new JButton("확인");
		
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.add(text);
		panel.add(button);
		
		panel.setBackground(Color.orange);
		return panel;
		
	}
}
