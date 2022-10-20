package classTest;

import java.util.Scanner;

public class CircleAreaTest {
	public static void main(String[] args) {
		int array[] = new int[5];
		for(int i = 0; i< array.length; i++) {
			System.out.print(array[i]);

		}
		Scanner s = new Scanner(System.in);
		
		System.out.print("반지름 입력: ");
		double r = s.nextDouble();
		
		Circle c1 = new Circle();
		c1.setRadius(r);
		
		double area = c1.getArea();
		System.out.print("반지름: " + c1.getRadius());
		System.out.println("인 원의 넓이는 "+ area);
	}
}
