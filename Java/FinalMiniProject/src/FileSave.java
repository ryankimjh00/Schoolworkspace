import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

class FileSave implements ActionListener {

	PaintPanel paintpanel;
	BufferedImage bufferedImage;
	JFileChooser jFileChooser;
	JFrame frame;

	public FileSave(PaintPanel paintpanel, BufferedImage bufferedImage) {
		this.paintpanel = paintpanel;
		this.bufferedImage = bufferedImage;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		jFileChooser = new JFileChooser(); // JFileChooser로 대화상자를 통해 파일을 저장합니다.
		jFileChooser.setFileFilter(new FileNameExtensionFilter("*.png", "png"));
		int n = jFileChooser.showSaveDialog(null);
		if (n == JFileChooser.APPROVE_OPTION) {
			File file = jFileChooser.getSelectedFile();
			try {
				ImageIO.write(bufferedImage, "png", new File(file.getAbsolutePath()));
				System.out.println("저장되었습니다. " + file.getAbsolutePath());
			} catch (IOException ioe) {
				System.out.println("저장되지 않았습니다.");
			}
		}
		if (n == JFileChooser.CANCEL_OPTION) {
			System.out.println("파일이 선택되지 않았습니다.");
		}

	}

}