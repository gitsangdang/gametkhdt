package model;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Fruit {
	public int x[] = new int[4];
	public int y[] = new int[4];
	Image img1, img2, img3, img4, img5, slowFruit;

	int type;
	public static final int APPLE = 0;
	public static final int WATERMELON = 1;
	public static final int BANANA = 2;
	public static final int STRAWBERRY = 3;
	public static final int ORANGE = 4;

	public Fruit() {
		Random rd = new Random();
		int index = rd.nextInt(28);

		try {
			img1 = ImageIO.read(new File("src/files/images/apple.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			img2 = ImageIO.read(new File("src/files/images/watermelon.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			img3 = ImageIO.read(new File("src/files/images/banana.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			img4 = ImageIO.read(new File("src/files/images/ichigo.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			img5 = ImageIO.read(new File("src/files/images/orange.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			slowFruit = ImageIO.read(new File("src/files/images/slowDown.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		x[0] = index;
		x[1] = index + 1;
		x[2] = index;
		x[3] = index + 1;
		y[0] = -3;
		y[1] = -3;
		y[2] = -2;
		y[3] = -2;

	}

	public void drawFruit(Graphics g, int imageNumber) {

		if (imageNumber == APPLE) {

			type = APPLE;

			g.drawImage(img1, x[0] * 21, y[0] * 21, 40, 40, null);

		}
		if (imageNumber == WATERMELON) {
			type = WATERMELON;

			g.drawImage(img2, x[0] * 21, y[0] * 21, 40, 40, null);

		}
		if (imageNumber == BANANA) {
			type = BANANA;

			g.drawImage(img3, x[0] * 21, y[0] * 21, 40, 40, null);

		}
		if (imageNumber == STRAWBERRY) {
			type = STRAWBERRY;

			g.drawImage(img4, x[0] * 21, y[0] * 21, 40, 40, null);

		}
		if (imageNumber == ORANGE) {
			type = ORANGE;

			g.drawImage(img5, x[0] * 21, y[0] * 21, 40, 40, null);

		}

		if (imageNumber == 5) {

			g.drawImage(slowFruit, x[0] * 21, y[0] * 21, 40, 40, null);

		}
	}
}
