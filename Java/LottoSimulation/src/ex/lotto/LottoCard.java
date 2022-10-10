package ex.lotto;

import java.util.Random;

public class LottoCard {
	Lotto[] lotto_arr = new Lotto[5];
	Random random = new Random();
	int mEffNum = random.nextInt(5);
	int[] arr1 = { 1, 2, 3, 4, 5, 6 };
	int[] arr2 = { 1, 3, 5, 7, 9, 11 };
	int[] arr3 = { 2, 4, 6, 8, 10, 12 };
	int[] arr4 = { 45, 44, 43, 42, 41, 40 };
	int[] arr5 = { 10, 20, 30, 40, 50, 60 };
	int[][] manual_arr = { arr1, arr2, arr3, arr4, arr5 };

	public LottoCard() {
		System.out.println("자동");
		for (int i = 0; i < mEffNum; i++) {
			Lotto auto = new Lotto();
			lotto_arr[i] = auto;
			if (auto.verify() == false) {
				i--;
				return;
			} else {
				auto.show();
			}
		}
		System.out.println("수동");
		for (int j = mEffNum; j < 5; j++) {
			Lotto manual = new Lotto(manual_arr[j]);
			if (manual.verify() == false) {
				j--;
				return;
			} else {
				manual.show();
			}
		}

	}

}
