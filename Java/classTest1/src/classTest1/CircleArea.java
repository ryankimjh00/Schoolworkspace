package classTest1;

import java.util.Scanner;

public class CircleAreaTest {
    public static void main(String[] args) {
        Scanner s;

        Circle c1 = new Circle();
        c1.setRadius(10);

        double area = c1.getArea();
        System.out.print("반지름: " + c1.getRadius());
        System.out.println("인 원의 넓이는 "+ area);
    }
}
