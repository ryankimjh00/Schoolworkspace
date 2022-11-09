import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ActionEventHandler implements ActionListener {
	private JTextField text;
	private JTextArea area;
	
	ActionEventHandler(JTextField texts, JTextArea area){
		this.text = texts;
		this.area = area;
	}
	
	public void actionPerformed(ActionEvent e) {
		String all = text.getText();
		text.setText("");
		String adding_text = text.getText();
		area.setText(adding_text);
	}
}
