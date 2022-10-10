package ex.lotto;

import java.util.Random;

public class Lotto {
	int lotto[] = new int[6];

	public Lotto() {
		for (int i = 0; i < 6; i++) {
			Random random = new Random();
			int num = random.nextInt(44) + 1;
			lotto[i] = num;
		}
	}

	public Lotto(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			lotto[i] = nums[i];
		}
	}

	public void show() {
		for (int i = 0; i < 6; i++) {
			System.out.print(lotto[i] + " ");
		}
		System.out.println();
	}

	public boolean verify() {
		boolean is_valid = false;
		for (int i = 0; i < 6; i++) {
			for (int j = i + 1; j < 6; j++) {
				if (lotto[i] == lotto[j]) {
					is_valid = false;
				} else {
					is_valid = true;
				}
			}
		}
		return is_valid;
	}
}
