package hw.account;

public class Bank {
	public static void main(String args[]) {
		Account a = new Account("12345", "user", 1000000);
		checkingAccount b = new checkingAccount("12345", "user", 1000000, "22222");
		
		System.out.println(b.accountNo + " " + b.name + " " + b.balance + " " + b.cardNo);
		a.deposit(1000000);
		a.withdraw(100);

	}
}
