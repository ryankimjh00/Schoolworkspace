
public class PhoneInfo {
	private String name;
	private String birthDay;
	private String phoneNum;

	PhoneInfo(String name, String birthDay, String phoneNum) {
		this.name = name;
		this.birthDay = birthDay;
		this.phoneNum = phoneNum;
	}

	void show() {
		System.out.print(name);
		System.out.print(birthDay);
		System.out.print(phoneNum);
	}

	public String toString() {
		return name;
	}
}
