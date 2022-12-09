import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Canvas extends JFrame {
	JFrame frame;

	public Canvas() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame.setDefaultLookAndFeelDecorated(true);
				frame = new JFrame("2191298김종현 미니프로젝트");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.pack();
				frame.setVisible(true);
				buildGUI();
			}
		});

	}

	public void buildGUI() {
		PaintPanel paintpanel = new PaintPanel();
		frame.add(paintpanel);
		frame.setBounds(400, 200, 500, 500);
		JMenu menu = new JMenu("Menu");
		JMenuItem[] menuItem = new JMenuItem[3];
		String[] itemTitle = { "exit", "about", "help" };
		JMenuBar mb = new JMenuBar();
		mb.add(menu);
		helpListener helpListener = new helpListener();
		for (int i = 0; i < menuItem.length; i++) {
			menuItem[i] = new JMenuItem(itemTitle[i]);
			menu.add(menuItem[i]);
			menuItem[i].addActionListener(helpListener);
		}
		frame.setJMenuBar(mb);
	}
	
	class helpListener extends JFrame implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			switch (command) {
			case "exit":
				System.exit(0);
			case "about":
				JOptionPane.showMessageDialog(this, "제작자: 김종현\n자바 Swing 멀티쓰레딩을 이용한 그림판입니다.\n문의: ryankimjh546@gmail.com",
						"about", JOptionPane.INFORMATION_MESSAGE);
				break;
			case "help":
				JOptionPane.showMessageDialog(this,
						"원하는 모양(펜, 원, 사각형, 선)을 고르고\n원하는 선의 굵기와 색상을 선택해 주세요.\n기본값은 black, 5px입니다.\nsave 버튼을 눌러 그린 그림을 저장할수 있습니다."
						+ "\nload버튼으로 사진을 불러와 배경화면으로 설정할수 있습니다",
						"about", JOptionPane.INFORMATION_MESSAGE);
				break;
			}
		}
	}
}
