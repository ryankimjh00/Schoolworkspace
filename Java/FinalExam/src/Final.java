import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JFrame;

public class Final extends JFrame {
	private Vector<PhoneInfo> mData = new Vector<PhoneInfo>();

	public Final() {
		super("JCom");
		readData();
		buildGUI();
		setBounds(120, 120, 300, 100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void buildGUI() {
		JComboBox<PhoneInfo> cbox = new JComboBox<PhoneInfo>(mData);
		cbox.setMaximumRowCount(3);
		cbox.addItemListener(choiceEventListener);
		cbox.addActionListener(selectEventListener);
		
		setLayout(new FlowLayout());
		add(new Label("이름"));
		add(cbox);
	}

	private void readData() {
		mData.add(new PhoneInfo("홍길동", "0000/00/00", "000-0000-0000"));
		mData.add(new PhoneInfo("홍길동", "0000/00/00", "000-0000-0000"));
		mData.add(new PhoneInfo("홍길동", "0000/00/00", "000-0000-0000"));
		mData.add(new PhoneInfo("홍길동", "0000/00/00", "000-0000-0000"));
	}

	private ItemListener choiceEventListener = new ItemListener() {
		public void itemStateChanged(ItemEvent e) {
			if(e.getStateChange() == ItemEvent.SELECTED)
				System.out.print("selected");
			else
				System.out.print("deselected");
			
			((PhoneInfo)e.getItem()).show();
			System.out.println();
		}
	};
	
	private ActionListener selectEventListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			JComboBox<PhoneInfo> cb = (JComboBox<PhoneInfo>)e.getSource();
			System.out.print("선택");
			
			((PhoneInfo)(cb.getSelectedItem())).show();
			System.out.println();
		}
	};
}
