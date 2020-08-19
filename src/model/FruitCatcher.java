package model;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FruitCatcher {
	
	public static int x[] = new int[6];
	public static int y[] = new int[6];
	public boolean isStart = true;
	Image img;

	public FruitCatcher() {
		try {
			img = ImageIO.read(new File("files/images/girlcatch.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// set vi no, set vi tri xuat hien cua catcher
		for (int i = 0; i < 6; i++) {
			x[i] = i + 12;
			y[i] = 23;
		}

	}

	// set vi tri catcher
	public void setLocation(int n) {
		if (isStart) {

			if (n == -1 && (x[0] > 0)) {// kiem tra xem bình chứa đụng tường
										// trái nhanh
				for (int i = 0; i < x.length; i++) {

					x[i] -= 2;

				}
			}
			if (n == 1 && (x[5] < 29)) {// kiem tra xem bình chứa đụng tường
										// phải nhanh
				for (int i = 0; i < x.length; i++) {

					x[i] += 2;

				}
			}

		}
	}

	public void setLocationByMouse(int xMouse) {
		if (xMouse > (21 * 2) && xMouse < (21 * 27)) {
			for (int i = 0; i < x.length; i++) {
				x[i] = ((xMouse / 21) - 2 + i);
			}
		}
	}

	public void drawFruitCatcher(Graphics g) {
		g.drawImage(img, x[0] * 21, y[0] * 21, 21 * 6, 21 * 4, null);
	}
}
