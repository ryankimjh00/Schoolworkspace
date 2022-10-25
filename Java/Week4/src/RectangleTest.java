
public class RectangleTest {
	static void show(Rectangle r) {
		r.show();
		System.out.print("넓이: "+r.getArea());
		System.out.print(" , ");
		System.out.println("둘레: "+r.getLength());
	}
	
	public static void main(String[] args) {
		Rectangle r1 = new Rectangle(0,0,10,5);
		RectangleTest.show(r1);
		
		System.out.println();
		
		Rectangle r2 = new Rectangle(new Point(0,0), new Point(5,10));
		RectangleTest.show(r2);
		
	}
}
