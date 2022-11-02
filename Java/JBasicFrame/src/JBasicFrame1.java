import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class JBasicFrame1 {
	private JFrame frame;

	public JBasicFrame1() {
		frame = new JFrame("Laydout Test");
		// buildGUI();
		buildLayout();
		frame.setSize(500, 600);
		frame.setLocation(400, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	private void buildGUI() {
		JButton btn = new JButton("확인");
		JButton b_cancel = new JButton("취소");

//		Container cp = frame.getContentPane();
//		cp.add(btn); 이거 두줄이랑 frame.add()랑 같은의미

		frame.add(btn);
		frame.add(b_cancel);
	}

	private void buildLayout() {
		// frame.setLayout(null);
		// frame.setLayout(new FlowLayout(FlowLayout.RIGHT));
		// frame.setLayout(new GridLayout(0, 2));
		frame.setLayout(new BorderLayout());

		JButton btn;
//		for (int i = 0; i < 5; i++) {
//			btn = new JButton("" + (i + 1));
//			btn.setBounds(0, 40 * i, 100, 30);
//			frame.add(btn);
//		}
		btn = new JButton("1");
		frame.add(btn, BorderLayout.NORTH);
//		btn = new JButton("2");
//		frame.add(btn, BorderLayout.EAST);
//		btn = new JButton("3");
//		frame.add(btn, BorderLayout.SOUTH);
//		btn = new JButton("4");
//		frame.add(btn, BorderLayout.WEST);
		btn = new JButton("5");
		frame.add(btn, BorderLayout.CENTER);

	}
}
