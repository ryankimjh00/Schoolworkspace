
public class RectangleTest {
	int x, y;
	Point left_top = new Point(3, 4);
	Point right_bottom = new Point(6, 10);

	public int get_area(Point p1, Point p2) {
		int area =  (p2.x - p1.x) * (p1.y - p2.y);
		return area;
	}
	
	public int get_length(Point p1, Point p2) {
		int length = ((p2.x - p1.x) + (p1.y - p2.y)) * 2;
		return length;
	}
	
	public static void main(String args[]) {
		
		System.out.println("넓이는: "+ get_area(left_top, right_bottom));
		System.out.println("둘레는: "+ get_length(left_top, right_bottom));
	}
}
