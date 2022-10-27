package hw.account;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputTest2 {
	static int getInteger() {
		int n;
		while (true) {
			Scanner s = new Scanner(System.in);
			try {
				n = s.nextInt();
			} catch (InputMismatchException e) {
				n = 0;
				System.out.println("다시 입력");
				continue;
			}
			return n;
		}
	}

	public static void main(String args[]) {

		System.out.print("나이?");
		int age = getInteger();
		System.out.println("나이" + age + "세");

	}

}
