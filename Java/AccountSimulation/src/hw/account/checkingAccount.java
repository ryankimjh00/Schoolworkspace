package hw.account;

public class checkingAccount extends Account {
	protected String cardNo;
	
	public checkingAccount(String accountNo, String name, int balance, String cardNo) {
		super(accountNo, name, balance);
		this.cardNo = cardNo;
	}

	public int pay(String cardNo, int amount) {
		if(cardNo == this.cardNo) {
			int remain = withdraw(amount);
			return remain;
		}
		else {
			return 0;
		}
	}
}
