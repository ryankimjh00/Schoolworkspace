import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

class FileSave implements ActionListener {

	PaintPanel paintpanel;
	BufferedImage bufferedImage;
	JFileChooser jFileChooser;

	
	public FileSave(PaintPanel paintpanel, BufferedImage bufferedImage) {
		this.paintpanel = paintpanel;
		this.bufferedImage = bufferedImage;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		jFileChooser = new JFileChooser(); //JFileChooser로 대화상자를 통해 파일을 저장합니다.
		jFileChooser.setFileFilter(new FileNameExtensionFilter("*.jpg", "jpg"));
		int rVal = jFileChooser.showSaveDialog(null);
		if (rVal == JFileChooser.APPROVE_OPTION) {
			File file = jFileChooser.getSelectedFile();
			try {
				ImageIO.write(bufferedImage, "png", new File(file.getAbsolutePath()));
				System.out.println("saved Correctly " + file.getAbsolutePath());
			} catch (IOException e1) {
				System.out.println("Failed to save image");
			}
		}
		if (rVal == JFileChooser.CANCEL_OPTION) {
			System.out.println("No file choosen");
		}

	}

}