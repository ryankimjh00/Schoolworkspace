import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class makeCircle extends JFrame {
	public makeCircle(){
		super("makeCiecle");
		JFrame frame = new JFrame("hello");
		frame.setBounds(400, 200, 380, 420);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		JMenuBar menuBar; //메뉴바 선언
		JMenu menu; //메뉴 선언
		JMenuItem menuItem; //메뉴 항목 선언
		menuBar = new JMenuBar(); //메뉴바 초기화
		menu = new JMenu("메뉴1");
		menuBar.add(menu);
		menuItem = new JMenuItem("메뉴항목1"); //메뉴항목 선언
		//menuItem = new JMenuItem(“메뉴항목1”, KeyEvent.VK_T); ->키보드 단축키 설정 가능
		menu.add(menuItem); //메뉴에 메뉴항목 추가
		frame.setJMenuBar(menuBar); //프레임에 메뉴바 설정
		frame.setVisible(true);
	}


}
