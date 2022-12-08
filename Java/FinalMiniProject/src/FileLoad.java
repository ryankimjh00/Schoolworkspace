import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

class FileLoad implements ActionListener {

	PaintPanel paintpanel;
	BufferedImage bufferedImage;
	JFileChooser jFileChooser = new JFileChooser();;

	public FileLoad(PaintPanel paintpanel, BufferedImage bufferedImage) {
		this.paintpanel = paintpanel;
		this.bufferedImage = bufferedImage;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPEG", "jpeg", "jpg", "png", "bmp", "gif");
		jFileChooser.addChoosableFileFilter(filter);

		int rVal = jFileChooser.showOpenDialog(null);
		if (rVal == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jFileChooser.getSelectedFile();
			try {
				paintpanel.bufferedImage = ImageIO.read(new File(selectedFile.getAbsolutePath()));
				paintpanel.repaint();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if (rVal == JFileChooser.CANCEL_OPTION) {

		}

	}

}