import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Circle extends JFrame {

	public Circle() {
		setTitle("마우스로 원그리기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(new MyPanel());
		setSize(300, 300);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Circle();
	}

	class MyPanel extends JPanel {
		Point s = null;
		Vector<Point> v = new Vector<Point>();

		public MyPanel() {
			addMouseListener(new MouseListener() { // 익명내부클래스로 만들었습니다.
				@Override
				public void mouseReleased(MouseEvent e) {
				}

				@Override
				public void mousePressed(MouseEvent e) {
				}

				@Override
				public void mouseExited(MouseEvent e) {
				}

				@Override
				public void mouseEntered(MouseEvent e) {
				}

				@Override
				public void mouseClicked(MouseEvent e) {
					if (v.size() <= 9) {
						s = e.getPoint();
						v.add(s); // vector에 저장하는 이유는 저장하지 않으면 repaint를 하면 없어지기 때문입니다.
						// repaint()는 화면에 다시 그리는 메소드이므로 화면에 마우스를 클릭할때 마다 호출해주어야 합니다.
					} else
						return;
					repaint();
				}
			});

		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			if (s == null)
				return;
			g.setColor(Color.BLUE);
			for (int i = 0; i < v.size(); i++)
				g.fillOval(v.get(i).x - 15, v.get(i).y - 15, 30, 30);
		}
	}
}