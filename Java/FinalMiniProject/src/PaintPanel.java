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
	Point P1 = new Point(0, 0); // 마우스가 처음 클린된 위치
	Point P2 = new Point(0, 0); // 마우스가 최종적으로 머무른 위치
	BufferedImage bufferedImage; // 이미지 파일로 저장, 배경을 바꾸기 위해 필요한 buffered image 객체
	Color colors = Color.black; // 기본 색상
	Float stroke = (float) 5; // 선의 굵기 초기값
	JComboBox<Color> colorCombo; // 색을 바꾸기 위한 콤보박스
	JComboBox<Float> strokeCombo; // 굵기를 바꾸기 위한 콤보박스
	JButton rectbtn; // 사각형 그리기 버튼
	JButton linebtn; // 선 그리기 버튼
	JButton circlebtn; // 원 그리기 버튼
	JButton penbtn; // 펜 그리기 버튼
	JButton eraserbtn; // 지우개 버튼
	int width;
	int height;
	int minX;
	int minY;

	public PaintPanel() {
		colorCombo = new JComboBox<Color>();
		strokeCombo = new JComboBox<Float>();
		rectbtn = new JButton("⬛");
		linebtn = new JButton("Line");
		circlebtn = new JButton("⚫");
		penbtn = new JButton("🖊️");
		eraserbtn = new JButton("지우개");
		Dimension d = new Dimension(500, 500);
		JMenuItem save = new JMenuItem("save");
		JMenuItem load = new JMenuItem("load");

		this.add(load);
		this.add(save);
		this.add(penbtn);
		this.add(linebtn);
		this.add(rectbtn);
		this.add(circlebtn);
		this.add(eraserbtn);

		add(colorCombo);
		add(strokeCombo);
		// 선의 색깔을 선택하는 콤보박스
		colorCombo.setModel(new DefaultComboBoxModel<Color>(
				new Color[] { Color.black, Color.red, Color.blue, Color.green, Color.yellow }));
		// 선의 굵기를 선택하는 콤보박스
		strokeCombo.setModel(new DefaultComboBoxModel<Float>(
				new Float[] { (float) 5.0, (float) 10.0, (float) 15.0, (float) 20.0, (float) 25.0 }));

		bufferedImage = new BufferedImage(d.width, d.height, BufferedImage.TYPE_INT_ARGB);
		setBackground(bufferedImage); // 인터넷에서 찾아본 결과 bufferdImage의 배경은 black 이기에 white로 변경했습니다.

		rectbtn.addActionListener(this);
		linebtn.addActionListener(this);
		circlebtn.addActionListener(this);
		penbtn.addActionListener(this);
		eraserbtn.addActionListener(this);
		colorCombo.addActionListener(this);
		strokeCombo.addActionListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		save.addActionListener(new FileSave(this, bufferedImage)); // bufferedImage를 받아와야 하기에 이 클래스 내부에 구현하였습니다.
		load.addActionListener(new FileLoad(this, bufferedImage));
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
			draw();
		}
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource().getClass().toString().contains("JButton")) {
			shape = e.getActionCommand(); // 그릴 모양을 불러옵니다.
		}

		else if (e.getSource().equals(colorCombo)) {
			colors = (Color) colorCombo.getSelectedItem(); // 그릴 색깔을 정합니다.
		}

		else if (e.getSource().equals(strokeCombo)) {
			stroke = (float) strokeCombo.getSelectedItem(); // 그릴 굵기를 정합니다
		}

	}

	public void draw() {
		// 도형의 넓이와 높이를 마우스에 따라 동적으로 설정해줌니다.
		width = Math.abs(P2.x - P1.x);
		height = Math.abs(P2.y - P1.y);

		// 도형의 위치를 마우스에 따라 동적으로 설정해줌니다.
		minX = Math.min(P1.x, P2.x);
		minY = Math.min(P1.y, P2.y);

		// 좌표와 테두리 등 더 많이 조작이 가능한 Graphics2D를 상속받아 사용하였습니다.
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

		case ("지우개"): // 지우개는 흰색 펜으로 구현했습니다.
			g.setColor(Color.white);
			g.setStroke(new BasicStroke(stroke));
			g.drawLine(P1.x, P1.y, P2.x, P2.y);
			break;

		}

		g.dispose();

		repaint();
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(bufferedImage, 0, 0, null);
	}

	public void setBackground(BufferedImage bufferedimg) { // 기본 이미지 저장 배경이 검은색이라서 흰색으로 변경했습니다.
		this.bufferedImage = bufferedimg;
		Graphics2D g = bufferedImage.createGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, 500, 700);
		g.dispose();
	}

	@Override
	public void mouseDragged(MouseEvent e) { //
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
			draw();
		} else if (shape == "선") {
			Graphics g = getGraphics();
			g.drawLine(P1.x, P1.y, P2.x, P2.y);
			P2.setLocation(e.getX(), e.getY());
			repaint();
			g.dispose(); // dispose() 메서드로 그래픽에서 사용중인 시스템 자원을 해제해줍니다
		} else if (shape == "네모") {
			Graphics g = getGraphics();
			g.setColor(Color.BLACK);
			g.drawRect(minX, minY, width, height);
			P2.setLocation(e.getX(), e.getY());
			repaint();
			g.dispose();
		} else if (shape == "원") {
			Graphics g = getGraphics();
			g.setColor(Color.BLACK);
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

}
