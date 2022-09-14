
public class TVUser {
	public static void main(String args[]) {
		TV tv = new TV();
		
		tv.Power();
		System.out.println("");

		for(int i = 0; i < 5; i++) { // channel up 할떄 5에서 더 누르면 다시 1로 돌아감
			tv.ChannelUp();
		}
		System.out.println("");

		for(int i = 0; i < 3; i++) { // channel down 할떄 1에서 더 누르면 다시 5로 돌아감
			tv.ChannelDown();
		}
		System.out.println("");

		for(int i = 0; i < 6; i++) { // volume up 할떄 10에 다다르면 더이상 증가하지 않음
			tv.VolumeUp();
		}
		System.out.println("");

		for(int i = 0; i < 11; i++) { // volume down 할떄 0에 다다르면 더이상 증가하지 않음
			tv.VolumeDown();
		}		System.out.println("");

		tv.Power();
	}
}
