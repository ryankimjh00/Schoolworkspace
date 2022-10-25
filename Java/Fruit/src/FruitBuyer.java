
public class FruitBuyer {
	static final int APPLE_UNITCOST = 1000;
	int money;
	int numofApple;
	
	FruitBuyer(int money, int numofApple){
		this.money = money;
		this.numofApple = numofApple;
	}
	int buyApple(FruitSeller s, int money) {
		this.money -= money;
		this.numofApple += s.saleApple(money);
	}
	
	void showResult() {
		System.out.println("구매한 사과: "+ numofApple + "개");
		System.out.println("남은 돈: "+ (money - numofApple * APPLE_UNITCOST)+ "원");
	}
}
