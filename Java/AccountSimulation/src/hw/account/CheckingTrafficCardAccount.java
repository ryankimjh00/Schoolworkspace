package hw.account;

public class CheckingTrafficCardAccount extends checkingAccount {

	protected String cdNo = "111111";

	public CheckingTrafficCardAccount(String accountNo, String name, int balance) {
		super(accountNo, name, balance);
	}

	public int payTrafficCard(String cdNo, int amount) {
		if (this.accountNo != cdNo) {
			System.out.println("등록되지 않은 교통카드입니다.");
			return 0;
		} else {
			balance -= amount;
			System.out.println(amount + "를 결제합니다");
			return amount;
		}
	}
}
