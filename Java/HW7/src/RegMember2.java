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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Enumeration;
import java.awt.BorderLayout;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;

public class RegMember2 extends JFrame {
	JTextField name;
	ButtonGroup group;
	JTextField Serial_1;
	JTextField Serial_2;
	JTextField address;
	JTextField department;
	JTextArea introduce;
	JCheckBox[] hobbys;
	String hobby_collector = "";

	public RegMember2() {
		super("2191298 김종현 HW7_단계 (2)");
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
		return southPanel;
	}

	private JPanel NameAndSex() {
		name = new JTextField(8);
		
		name.addKeyListener(new MyKeyListener());
		JRadioButton man = new JRadioButton("남성", false);
		JRadioButton woman = new JRadioButton("여성", false);
		//man.addActionListener(radiobutton_handler);
		//woman.addActionListener(radiobutton_handler);
		man.addItemListener(radiobutton_item_handler);
		woman.addItemListener(radiobutton_item_handler);
		group = new ButtonGroup();
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
		Serial_1.addKeyListener(new MyKeyListener());
		Serial_2.addKeyListener(new MyKeyListener());
		JLabel serialNumberLabel = new JLabel("주민등록번호 ");
		JLabel hypen = new JLabel("-");
		panel.add(serialNumberLabel);
		panel.add(Serial_1);
		panel.add(hypen);
		panel.add(Serial_2);
		return panel;
	}

	private JPanel Address() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		address = new JTextField(22);
		address.addKeyListener(new MyKeyListener());
		JLabel addressLabel = new JLabel("주                 소");
		panel.add(addressLabel);
		panel.add(address);
		return panel;
	}

	private JPanel Department() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		department = new JTextField(8);
		department.addKeyListener(new MyKeyListener());
		JLabel departmentLabel = new JLabel("부      서      명 ");
		panel.add(departmentLabel);
		panel.add(department);
		return panel;
	}

	private JPanel Hobby() {
		hobbys = new JCheckBox[4];
		String[] hobby_name = { "영화", "음악감상", "사진", "운동" };
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel hobbyLabel = new JLabel("취               미");
		panel.add(hobbyLabel);
		for (int i = 0; i < hobbys.length; i++) {
			hobbys[i] = new JCheckBox(hobby_name[i]);
			panel.add(hobbys[i]);
			// hobbys[i].addActionListener(checkbox_handler);
			hobbys[i].addItemListener(checkbox_item_handler);
			
		}
		return panel;
	}

	private JPanel Introduce() {
		JPanel panel = new JPanel(new BorderLayout());
		introduce = new JTextArea(" ", 9, 22);
		JLabel introduceLabel = new JLabel(" 자   기   소   개");
		panel.add(introduceLabel, BorderLayout.NORTH);
		panel.add(introduce, BorderLayout.CENTER);
		return panel;
	}

// radio button이 눌리면 바로 출력해주는 매서드
	private ActionListener radiobutton_handler = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			String sex = e.getActionCommand();
			introduce.append(sex + "\n");
		}
	};
	
//ActionListener radiobutton_handler 가 중복되는 눌림이 계속 뜨는것을 보완하기 위해 item 리스너를 사용한다.
	private ItemListener radiobutton_item_handler = new ItemListener() {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if(e.getStateChange() == ItemEvent.SELECTED) {
				JRadioButton src = (JRadioButton)e.getSource(); // 다운캐스팅 해서 유지
				String txt = src.getText();
				
				introduce.append("성별: " + txt + "\n");
			}
		}
		
	};
	
// check box가 눌리면 바로 출력해주는 매서드

	private ActionListener checkbox_handler = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			hobby_collector = e.getActionCommand();
			introduce.append(hobby_collector + " ");
		}
	};
	
	private ItemListener checkbox_item_handler = new ItemListener() {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if(e.getStateChange() == ItemEvent.SELECTED) {
				JCheckBox src = (JCheckBox)e.getSource(); // 다운캐스팅 해서 유지
				String txt = src.getText();
				
				introduce.append("취미: " + txt + "\n");
			}
		}
		
	};

// 엔터를 치면 바로 출력해주는 내부 클래스
	public class MyKeyListener extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				JTextField lableName = (JTextField) e.getSource();
				String inputText = lableName.getText();
				introduce.append(inputText + "\n");
			}
		}
	}
}
