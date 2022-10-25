
public class FruitSeller {
	static final int APPLE_UNITCOST = 1000;
	int numofApple;
	int money;
	
	FruitSeller(int money, int numofApple){
		this.money = money;
		this.numofApple = numofApple;
	}
	
	int saleApple(int m) {
		int num = m/APPLE_UNITCOST;
		numofApple -=num;
		money += m;
		return num;
	}
	
	
	void showResult() {
		System.out.println("남은 사과: "+ numofApple + "개");
		System.out.println("판매 수익: "+ money + "원");
	}
}
