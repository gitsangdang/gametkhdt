package view;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class LogoPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	Image logo;

	public LogoPanel() {

	}

	@Override
	public void paint(Graphics g) {
		try {
			logo = ImageIO.read(new File("files/images/Fruitlogo.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(logo, 0, 0, 200, 200, null);
	}
}
