package ex.hw;

import ex.lotto.*;

public class LottoSim {
	public static void main(String args[]) {
		int num = (int) (Math.random() * 3 + 1);
		System.out.println("로또 " + num + "장을 출력합니다.\n");
		for (int i = 0; i < num; i++) {
			LottoCard card = new LottoCard();
			System.out.println();
		}
	}
}
