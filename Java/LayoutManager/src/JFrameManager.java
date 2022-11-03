import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JButton;

public class JFrameManager {
	private JFrame frame;
	private JFrame NFrame;

	public JFrameManager() {
		frame = new JFrame("2191298 김종현 HW6");
		buildGUI();
		frame.setSize(350, 420);
		frame.setLocation(400, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	private void buildGUI() {

		frame.add(createNorthPanel(), BorderLayout.NORTH);
		frame.add(createCenterPanel(), BorderLayout.CENTER);
		frame.add(createSouthPanel(), BorderLayout.SOUTH);

	}

	private JPanel createNorthPanel() {
		JPanel northPanel = new JPanel(new GridLayout(0, 1));
		northPanel.add(NameAndSex());
		northPanel.add(SerialNumber());
		northPanel.add(Address());
		northPanel.add(Department());
		northPanel.add(Hobby());
		return northPanel;
	}
	
	private JPanel createCenterPanel() {
		JPanel southPanel = new JPanel(new BorderLayout());
		southPanel.add(Introduce());
		return southPanel;
	}
	private JPanel createSouthPanel() {
		JPanel southPanel = new JPanel(new FlowLayout());
		southPanel.add(Button());
		return southPanel;
	}

	private JPanel NameAndSex() {
		JTextField text1 = new JTextField(8);
		JTextField text2 = new JTextField(3);
		JLabel name = new JLabel("성                 명");
		JLabel sex = new JLabel("성                 별");
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.add(name);
		panel.add(text1);
		panel.add(sex);
		panel.add(text2);
		frame.setLayout(new BorderLayout());
		return panel;
	}

	private JPanel SerialNumber() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JTextField text1 = new JTextField(6);
		JTextField text2 = new JTextField(7);
		JLabel serialNumber = new JLabel("주민등록번호");
		JLabel hypen = new JLabel("-");
		panel.add(serialNumber);
		panel.add(text1);
		panel.add(hypen);
		panel.add(text2);
		return panel;
	}

	private JPanel Address() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JTextField text = new JTextField(22);
		JLabel address = new JLabel("주                 소");
		panel.add(address);
		panel.add(text);
		return panel;
	}

	private JPanel Department() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JTextField text = new JTextField(8);
		JLabel department = new JLabel("부      서      명 ");
		panel.add(department);
		panel.add(text);
		return panel;
	}

	private JPanel Hobby() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JTextField text = new JTextField(22);
		JLabel hobby = new JLabel("취                 미");
		panel.add(hobby);
		panel.add(text);
		return panel;
	}

	private JPanel Introduce() {
		JPanel panel = new JPanel(new BorderLayout());
		JTextArea text = new JTextArea(" ", 9, 22);
		JLabel introduce = new JLabel("  자   기   소   개");
		panel.add(introduce, BorderLayout.NORTH);
		panel.add(text, BorderLayout.CENTER);
		return panel;
	}
	private JPanel Button() {
		JPanel panel = new JPanel(new GridLayout(1,0));
		JButton btn;

		btn = new JButton("저장");
		panel.add(btn, BorderLayout.EAST);
		btn = new JButton("종료");
		panel.add(btn, BorderLayout.EAST);

		return panel;
		
	}

}
