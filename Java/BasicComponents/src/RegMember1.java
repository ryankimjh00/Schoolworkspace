import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Arrays;
import java.util.Enumeration;
import java.awt.BorderLayout;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;

public class RegMember1 extends JFrame {
	JTextField name;
	ButtonGroup group;
	JTextField Serial_1;
	JTextField Serial_2;
	JTextField department;
	JTextArea introducetext;
	JCheckBox[] hobbys;
	String hobby_collector = "";
	String sex = "";

	public RegMember1() {
		super("2191298 김종현 HW7");
		buildGUI();
		this.setBounds(400, 200, 380, 420);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	private void buildGUI() {
		this.add(createNorthPanel(), BorderLayout.NORTH);
		this.add(createCenterPanel(), BorderLayout.CENTER);
		this.add(createSouthPanel(), BorderLayout.SOUTH);
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
		name = new JTextField(8);
		group = new ButtonGroup();

		JRadioButton man = new JRadioButton("남성", false);
		JRadioButton woman = new JRadioButton("여성", false);
		man.addActionListener(btn_handler);
		woman.addActionListener(btn_handler);
		group.add(man);
		group.add(woman);
		JLabel name_index = new JLabel("성                 명");
		JLabel sex = new JLabel("성         별   ");
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.add(name_index);
		panel.add(name);
		panel.add(sex);
		panel.add(man);
		panel.add(woman);

		this.setLayout(new BorderLayout());
		return panel;
	}

	private JPanel SerialNumber() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Serial_1 = new JTextField(6);
		Serial_2 = new JTextField(7);
		JLabel serialNumber = new JLabel("주민등록번호 ");
		JLabel hypen = new JLabel("-");
		panel.add(serialNumber);
		panel.add(Serial_1);
		panel.add(hypen);
		panel.add(Serial_2);
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
		department = new JTextField(8);
		JLabel department_index = new JLabel("부      서      명 ");
		panel.add(department_index);
		panel.add(department);
		return panel;
	}

	private JPanel Hobby() {
		hobbys = new JCheckBox[4];
		String[] hobby_name = { "영화", "음악감상", "사진", "운동" };
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel hobby = new JLabel("취               미");
		panel.add(hobby);
		for (int i = 0; i < hobbys.length; i++) {
			hobbys[i] = new JCheckBox(hobby_name[i]);
			panel.add(hobbys[i]);
			hobbys[i].addItemListener(handler);
		}
		return panel;
	}

	private JPanel Introduce() {
		JPanel panel = new JPanel(new BorderLayout());
		introducetext = new JTextArea(" ", 9, 22);
		JLabel introduce = new JLabel(" 자   기   소   개");
		panel.add(introduce, BorderLayout.NORTH);
		panel.add(introducetext, BorderLayout.CENTER);
		return panel;
	}

	private JPanel Button() {
		JPanel panel = new JPanel(new GridLayout(1, 0));
		JButton savebtn;
		JButton exitbtn;

		savebtn = new JButton("저장");
		panel.add(savebtn, BorderLayout.EAST);
		exitbtn = new JButton("종료");
		panel.add(exitbtn, BorderLayout.EAST);
		savebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == savebtn) {
					String name_t = name.getText();
					String serial_t = Serial_1.getText();
					String serial_t2 = Serial_2.getText();
					String department_t = department.getText();
					String hobby_t = hobby_collector;
					introducetext.setText("이름: " + name_t + '\n' + "주민등록번호: " + serial_t + "-" + serial_t2 + '\n'
							+ "부서명: " + department_t + '\n' + "취미: " + hobby_t + '\n' + "성별: " + sex);
				}

			}
		});
		return panel;
	}

	private ItemListener handler = new ItemListener() {
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				for(int i=0; i<hobbys.length; i++) {
					if (e.getItem() == hobbys[i]) {
						hobby_collector += hobbys[i].getText() + " ";
						
					}
				}
			}
		}
	};

	private ActionListener btn_handler = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			JRadioButton src = (JRadioButton) e.getSource();
			sex = e.getActionCommand();
		}

	};
}
