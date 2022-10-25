
public class Rectangle {
	int x1;
	int x2;
	int y1;
	int y2;
	Point p1, p2;
	
	public Rectangle(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	public Rectangle(Point p1, Point p2) {
		this.x1 = p1.x;
		this.y1 = p1.y;
		this.x2 = p2.x;
		this.y2 = p2.y;
	}
	
	public int getArea() {
		return this.getWidth() * this.getHeight();
	}
	public int getLength() {
		return (this.getWidth() + this.getHeight()) * 2;
	}
	public int getWidth() {
		return Math.abs(x2-x1);
	}
	public int getHeight() {
		return Math.abs(y1-y2);
	}
	void show() {
		System.out.println("[직사각형 "+ this.getWidth() +
				"x"+ this.getHeight() + ": ("+ x1 + ","+y1+") , ("+x2+","+y2+")]");
	}
}


