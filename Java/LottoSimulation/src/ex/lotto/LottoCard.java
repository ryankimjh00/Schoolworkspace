package ex.lotto;

import java.util.Random;

public class LottoCard {
	public LottoCard() {
		Random random = new Random();
		int mEffNum = random.nextInt(4) + 1;
		for (int i = 0; i < mEffNum; i++) {
			for (int j = 0; j < 6; j++) {
				Lotto manual = new Lotto();
			}
		}
//		for(int i=0;i<mEffNum;i++) {
//			Lotto mlotto = new Lotto();
//			mlotto.show();
//			System.out.println();
//		}
		for (int i = 0; i < 5 - mEffNum; i++) {
			Lotto auto = new Lotto();
		}
	}
}


