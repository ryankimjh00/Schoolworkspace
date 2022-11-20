import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.util.Vector;
import javax.swing.JPanel;

public class CirclePanel extends JPanel {
   Point mousepoint = null;
   Point movedPoint = null;
   Rectangle box;
   static final int width = 50;
   static final int height = 50;
   Vector<Point> pointList = new Vector<Point>();
   boolean dragID = false;
   boolean isOn = false;
   
   public CirclePanel() {
      addMouseListener(new MouseListener() {
 
         @Override
         public void mouseClicked(MouseEvent e) {
            mousepoint = e.getPoint();
            pointList.add(mousepoint);
            repaint();
         }

         @Override
         public void mousePressed(MouseEvent e) {
        	 if(pointList.contains(movedPoint)) {
        		 isOn = true;
        		 System.out.print("들어있음 ");
        	 }else
        		 isOn = false;
         }

         @Override
         public void mouseReleased(MouseEvent e) {      
        	 if(isOn == true) {
                 pointList.add(movedPoint);
                 System.out.print(movedPoint.x + ",");
                 System.out.println(movedPoint.y);
                 repaint();
        	 }
         }

         @Override
         public void mouseEntered(MouseEvent e) {
            // TODO Auto-generated method stub
            
         }

         @Override
         public void mouseExited(MouseEvent e) {
            // TODO Auto-generated method stub
            
         }
      });

      addMouseMotionListener(new MouseMotionAdapter() {
         @Override
         public void mouseDragged(MouseEvent e) {
        	movedPoint = e.getPoint();
            movedPoint.x = e.getX();
            movedPoint.y = e.getY();
         }
      });

   }

   public void paint(Graphics g) {
      super.paintComponent(g);
      g.setColor(Color.red);
      for (int i = 0; i < pointList.size(); i++) {
         g.fillOval(pointList.get(i).x - width / 2, pointList.get(i).y - height / 2, width, height);
      }
   }
   //마우스 포인터가 들어있는 공간을 사각형으로 표현해주는 함수
   public void getRect() { 
	   for (int i = 0; i < pointList.size(); i++) {
		   box = new Rectangle(pointList.get(i).x, pointList.get(i).y, width, height);
	   }
   }

}