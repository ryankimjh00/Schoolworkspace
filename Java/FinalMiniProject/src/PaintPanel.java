import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

class PaintPanel extends JPanel implements ActionListener, MouseListener, MouseMotionListener {
	
	String shape = ""; // 모양을 저장하는 문자열
	Point P1 = new Point(0, 0);
	Point P2 = new Point(0, 0);
	BufferedImage bufferedImage;
	Color colors = Color.black;
	Float stroke = (float) 5;
	JComboBox<Color> colorComboBox;
	JComboBox<Float> strokeComboBox;
	JButton rectButton;
	JButton lineButton;
	JButton circleButton;
	JButton penButton;
	JButton eraseButton;
	int width;
	int height;
	int minX;
	int minY;


	public PaintPanel() {

		colorComboBox = new JComboBox<Color>();
		strokeComboBox = new JComboBox<Float>();
		rectButton = new JButton("⬛");
		lineButton = new JButton("Line");
		circleButton = new JButton("⚫");
		penButton = new JButton("🖊️");
		eraseButton = new JButton("지우개");

		JMenuItem save = new JMenuItem("save");
		JMenuItem load = new JMenuItem("load");
		this.add(penButton);
		this.add(lineButton);
		this.add(rectButton);
		this.add(circleButton);
		this.add(eraseButton);
		this.add(load);
		this.add(save);
		colorComboBox.setModel(new DefaultComboBoxModel<Color>(new Color[] { Color.black, Color.red, Color.blue,
				Color.green, Color.yellow, Color.pink, Color.magenta }));

		strokeComboBox.setModel(new DefaultComboBoxModel<Float>(
				new Float[] { (float) 5, (float) 10, (float) 15, (float) 20, (float) 25 }));

		add(colorComboBox);
		add(strokeComboBox);

		Dimension d = getPreferredSize();
		bufferedImage = new BufferedImage(d.width, d.height, BufferedImage.TYPE_INT_ARGB);
		setImageBackground(bufferedImage); // 인터넷에서 찾아본 결과 bufferdImage의 배경은 black 이기에 white로 변경했습니다.

		rectButton.addActionListener(this);
		lineButton.addActionListener(this);
		circleButton.addActionListener(this);
		penButton.addActionListener(this);
		eraseButton.addActionListener(this);
		colorComboBox.addActionListener(this);
		strokeComboBox.addActionListener(this);
		save.addActionListener(new FileSave(this, bufferedImage));
		load.addActionListener(new FileLoad(this, bufferedImage));

		addMouseListener(this);
		addMouseMotionListener(this);

	}


	public void mousePressed(MouseEvent e) {

		// 다시 클릭됐을경우 좌표 초기화
		P1.setLocation(0, 0);
		P2.setLocation(0, 0);

		P1.setLocation(e.getX(), e.getY());

	}

	public void mouseReleased(MouseEvent e) {

		if (shape != "🖊️") {
			P2.setLocation(e.getX(), e.getY());
			drawPaint();
		}
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource().getClass().toString().contains("JButton")) {
			shape = e.getActionCommand();
		}

		else if (e.getSource().equals(colorComboBox)) {
			colors = (Color) colorComboBox.getSelectedItem();
		}

		else if (e.getSource().equals(strokeComboBox)) {
			stroke = (float) strokeComboBox.getSelectedItem();
		}

	}

	public Dimension getPreferredSize() {
		return new Dimension(500, 700);
	}

	public void drawPaint() {

		width = Math.abs(P2.x - P1.x);
		height = Math.abs(P2.y - P1.y);

		minX = Math.min(P1.x, P2.x);
		minY = Math.min(P1.y, P2.y);

		Graphics2D g = bufferedImage.createGraphics();

		// draw on paintImage using Graphics
		switch (shape) {

		case ("Line"):
			g.setColor(colors);
			g.setStroke(new BasicStroke(stroke));
			g.drawLine(P1.x, P1.y, P2.x, P2.y);

			break;

		case ("⬛"):
			g.setColor(colors);
			g.setStroke(new BasicStroke(stroke));
			g.drawRect(minX, minY, width, height);

			break;

		case ("⚫"):
			g.setColor(colors);
			g.setStroke(new BasicStroke(stroke));
			g.drawOval(minX, minY, width, height);
			break;

		case ("🖊️"):
			g.setColor(colors);
			g.setStroke(new BasicStroke(stroke));
			g.drawLine(P1.x, P1.y, P2.x, P2.y);
			break;

		case ("지우개"):
			g.setColor(Color.white);
			g.setStroke(new BasicStroke(stroke));
			g.drawLine(P1.x, P1.y, P2.x, P2.y);
			break;


		default:
			break;

		}

		g.dispose();

		repaint();
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(bufferedImage, 0, 0, null);

	}

	public void setImageBackground(BufferedImage bi) {
		this.bufferedImage = bi;
		Graphics2D g = bufferedImage.createGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, 500, 700);
		g.dispose();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

		width = Math.abs(P2.x - P1.x);
		height = Math.abs(P2.y - P1.y);

		minX = Math.min(P1.x, P2.x);
		minY = Math.min(P1.y, P2.y);

		if (shape == "🖊️" | shape == "지우개") {
			if (P2.x != 0 && P2.y != 0) {
				P1.x = P2.x;
				P1.y = P2.y;
			}
			P2.setLocation(e.getX(), e.getY());
			drawPaint();
		} else if (shape == "선") {

			Graphics g = getGraphics();

			g.drawLine(P1.x, P1.y, P2.x, P2.y);
			P2.setLocation(e.getX(), e.getY());
			repaint();
			g.dispose();
		} else if (shape == "네모") {

			Graphics g = getGraphics();
			g.setColor(Color.BLACK);
			g.setXORMode(getBackground());

			g.drawRect(minX, minY, width, height);
			P2.setLocation(e.getX(), e.getY());
			repaint();
			g.dispose();
		} else if (shape == "원") {

			Graphics g = getGraphics();
			g.setColor(Color.BLACK);
			g.setXORMode(getBackground());

			g.drawOval(minX, minY, width, height);
			P2.setLocation(e.getX(), e.getY());

			g.dispose();
			repaint();
		}

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}// Class dotButton
