import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

class FileLoad implements ActionListener {

	PaintPanel paintpanel;
	BufferedImage bufferedImage;
	JFrame frame;
	JFileChooser jFileChooser = new JFileChooser();;

//	public FileLoad(JFrame frame) {
//		this.frame = frame;
//	}
	public FileLoad(PaintPanel paintpanel, BufferedImage bufferedImage) {
		this.paintpanel = paintpanel;
		this.bufferedImage = bufferedImage;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPEG", "jpeg", "jpg", "png", "bmp", "gif");
		jFileChooser.addChoosableFileFilter(filter);

		int n = jFileChooser.showOpenDialog(null);
		if (n == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jFileChooser.getSelectedFile();
			try {
				paintpanel.bufferedImage = ImageIO.read(new File(selectedFile.getAbsolutePath()));
				paintpanel.repaint();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
		if (n == JFileChooser.CANCEL_OPTION) {

		}

	}

}