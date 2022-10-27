package hw.account;

public class MalformedData extends Exception {
	public MalformedData() {
		super("잘못된 입금액입니다.");
	}
}
