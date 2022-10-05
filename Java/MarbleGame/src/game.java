
public class game {
	public static void main(String args[]) {
		Child c1 = new Child(1, 15);
		Child c2 = new Child(2, 9);
		c1.winner(c2, 2);
		c2.winner(c1, 7);
		c1.showResult();
		c2.showResult();
	}
}
