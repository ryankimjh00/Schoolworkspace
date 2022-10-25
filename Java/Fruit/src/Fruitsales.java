
public class Fruitsales {
	public static void main(String args[]) {
		FruitSeller seller = new FruitSeller(20, 0);
		FruitBuyer me = new FruitBuyer(10000, 0);
		
		int n = seller.saleApple(2000);
		me.buyApple(seller, 2000);
		
		seller.showResult();
		me.showResult();
		
	}
}
