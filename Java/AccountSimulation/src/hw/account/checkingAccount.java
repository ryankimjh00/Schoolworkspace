package hw.account;

public class checkingAccount extends Account {
	public checkingAccount(String accountNo, String name, int balance) {
		super(accountNo, name, balance);
	}

	public int pay(String cardNo, int amount) {
		if (this.accountNo != cardNo) {
			System.out.println("등록되지 않은 체크카드입니다.");
			return 0;
		} else {
			balance -= amount;
			System.out.println(amount + "원을 결제합니다");
			return amount;
		}
	}
}
