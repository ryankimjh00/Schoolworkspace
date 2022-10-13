package hw.account;

public class Account {
	String accountNo;
	String name;
	int balance;

	public Account(String accountNo, String name, int balance) {
		this.accountNo = accountNo;
		this.name = name;
		this.balance = balance;
		System.out.println(accountNo + " " + name + " " + balance);
	}

	public void deposit(int amount) {
		balance += amount;
		System.out.println(balance);
	}

	public int withdraw(int amount) {
		if (amount > balance) {
			System.out.println(0);
			return 0;
		}

		else {
			balance -= amount;
			System.out.println(balance);
			return amount;
		}
	}

	public void check() {
		Account a = new Account(accountNo, name, balance);
		System.out.println(a.balance);
	}

}
