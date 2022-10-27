package hw.account;

public class CreditLineAccount extends Account {
	protected int creditLine = 10000; // 마이너스 한도

	public CreditLineAccount(String accountNo, String name, int balance) {
		super(accountNo, name, balance);
	}

	@Override
	public void withdraw(int amount) {
		if (amount > balance + this.creditLine) {
			System.out.println(0);
//			return 0;
		} else {
			balance -= amount;
			System.out.println("마이너스 한도는 " + creditLine + "원입니다." + amount + "원을 출금합니다");
//			return amount;
		}
	}
}
