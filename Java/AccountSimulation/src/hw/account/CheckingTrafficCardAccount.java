package hw.account;

public class CheckingTrafficCardAccount extends checkingAccount {

	private String cdNo;
	
	public CheckingTrafficCardAccount(String accountNo, String name, int balance, String cardNo, String cdNo) {
		super(accountNo, name, balance, cardNo);
		this.cdNo = cdNo;
	}
	
	public int payTrafficCard(String cdNo, int amount) {
		if(cardNo == this.cdNo) {
			int remain = withdraw(amount);
			return remain;
		}
		else {
			return 0;
		}
	}
}
