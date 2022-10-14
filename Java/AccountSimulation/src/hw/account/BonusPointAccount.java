package hw.account;

public class BonusPointAccount extends Account {
	protected int bonusPoint;

	public BonusPointAccount(String accountNo, String name, int balance, int bonusPoint) {
		super(accountNo, name, balance);
		this.bonusPoint = bonusPoint;
	}

	@Override
	public void deposit(int amount) {
		balance += amount;
		bonusPoint += amount * 0.001;
		System.out.println(amount + "를 입금합니다.");
	}

	@Override
	public void check() {
		System.out.println("잔액은 " + balance + "원, 포인트는 " + bonusPoint + "점 입니다.");
	}
}
