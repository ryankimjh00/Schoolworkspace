package hw.account;

public class Account {
	protected String accountNo;
	protected String name;
	protected int balance;

	public Account(String accountNo, String name, int balance) {
		this.accountNo = accountNo;
		this.name = name;
		this.balance = balance;
		System.out.println(accountNo + " " + name + " " + balance + "  계좌 생성 성공");
	}

//	public void deposit(int amount) {
//		balance += amount;
//		System.out.println(amount + "원을 입금합니다");
//	}
	public int deposit(int amount) throws MalformedData {
		if (amount < 0)
			throw new MalformedData();
		balance += amount;
		return balance;
	}

//	public int withdraw(int amount) {
//		if (amount > balance) {
//			System.out.println(0 + ", 출금할수 없습니다.");
//			return 0;
//		} else {
//			balance -= amount;
//			System.out.println(amount + "원을 출금합니다");
//			return amount;
//		}
//	}
	public void withdraw(int amount) throws BalanceOutOfBoundsException, MalformedData {
		if (amount > balance)
			throw new BalanceOutOfBoundsException();
		if (amount < 0)
			throw new MalformedData();

	}

	public void check() {
		System.out.println("잔액은 " + balance + "원 입니다.");
	}

}
