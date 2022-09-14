
public class TV {
	boolean power;
	String p;
	int volume = 5;
	int channel = 1;
	
	public void Power() {
		if(power == true) {
			power = false;
			System.out.println("Power OFF");
		}
		else {
			power = true;
			System.out.println("Power ON");
		}
	}
	
	public void ChannelUp() {
		if(power == true) {
			if(channel > 0 && channel < 6) {
				channel++;
				if(channel > 5) {
					channel = 1;
				}
			}
			System.out.println("Channel: " + channel);
		}
	}
	
	public void ChannelDown() {
		if(power == true) {
			if(channel > 0 && channel < 6) {
				channel--;
				if(channel < 1) {
					channel = 5;
				}
			}
			System.out.println("Channel: " + channel);
		}
	}
	
	public void VolumeUp() {
		if(power == true) {
			if(volume > 0 && volume < 11) {
				volume++;
			if(volume > 10) {
				volume = 10;
				}
			}
			System.out.println("Volume: " + volume);
		}
	}
	public void VolumeDown() {
		if(power == true) {
			if(volume > 0 && volume < 11) {
				volume--;
			if(volume < 0) {
				volume = 0;
				}
			}
			System.out.println("Volume: " + volume);
		}
	}
}
