package hw.account;

public class BalanceOutOfBoundsException extends Exception{
	public BalanceOutOfBoundsException() {
		super("출금액이 잔고보다 많습니다");
	}
}
