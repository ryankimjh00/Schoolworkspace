package hw.account;

public class Bank {
	public static void main(String args[]) {
		Account[] array = new Account[5];
		array[0] = new Account("2191298", "Account", 1000);
		array[1] = new checkingAccount("2191298", "checkingAccount", 1000);
		array[2] = new CheckingTrafficCardAccount("2191298", "CheckingTrafficCardAccount", 1000);
		array[3] = new CreditLineAccount("2191298", "CreditLineAccount", 1000);
		array[4] = new BonusPointAccount("2191298", "BonusPointAccount", 1000, 0);
		System.out.println();

		for (int i = 0; i < 5; i++) {
			if (array[i] instanceof Account) {
				System.out.println(array[i].name + " Simulation");
				array[i].deposit(1000);
				array[i].withdraw(1000);
				array[i].withdraw(5000);
				array[i].check();
				if (array[i] instanceof CheckingTrafficCardAccount) {
					System.out.println("CheckingTrafficCardAccount 메소드 payTrafficCard() 테스트");
					CheckingTrafficCardAccount traffic = (CheckingTrafficCardAccount) array[i]; // 다운캐스팅
					traffic.payTrafficCard("2191298", 100);
					traffic.payTrafficCard("0000000", 100);
				}
				System.out.println();
			}
		}
		System.out.println("checkingAccount 메소드 pay() 테스트");
		if (array[1] instanceof checkingAccount) {
			checkingAccount check = (checkingAccount) array[1]; // 다운캐스팅
			check.pay("2191298", 100);
			check.pay("0000000", 100);
		}
	}

}
