package ex.hw;
import ex.lotto.Lotto;
import ex.lotto.LottoCard;

public class LottoSim {
	public static void main(String args[]) {
		System.out.println("수동 3장 번호 입력");
		for(int i=1; i<4; i++) {
			System.out.print(i+ "번쨰 장 입력: ");
			Lotto manual = new Lotto(6);
			manual.show();
		}
		System.out.println();
		System.out.println("자동 n개");
		LottoCard card = new LottoCard();
		
	}
}

