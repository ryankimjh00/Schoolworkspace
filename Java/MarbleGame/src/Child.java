
public class Child {
	int marble;
	int num;
	int childnum;
	
	public Child(int childnum, int marble) {
		this.childnum = childnum;
		this.marble = marble;
	}
	
	void getMarble(int num) {
		marble += num;
	}
	
	void giveMarble(int num) {
		marble -= num;
	}
	
	void winner(Child c, int num) {
		getMarble(num);
		c.giveMarble(num);
	}
	
	void showResult() {
		System.out.println("어린이"+ childnum +"의 남은 구슬은"+ marble + "개 입니다.");
	}
}
