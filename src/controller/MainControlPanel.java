package controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Fruit;
import model.FruitCatcher;
import observer.RemainingPanelDisplay;
import observer.Point;
import observer.PointPanelDisplay;
import observer.TotalPanelDisplay;
import view.Frame;

public class MainControlPanel extends JPanel implements Runnable {
	private static final long serialVersionUID = 1L;
	public static FruitCatcher fruitCatcher;
	Thread thread;
	public FruitsControlThread threadFruit[] = new FruitsControlThread[6];
	public static Fruit fruit[] = new Fruit[6];
	public static int point = 0;
	public static int miss = 0;
	long time = 0;
	public static int minute = 0;
	public static int second = 0;
//	public boolean isPause = false;

	public MainControlPanel() {

		for (int i = 0; i < fruit.length; i++) {
			fruit[i] = new Fruit();
		}
		fruitCatcher = new FruitCatcher();

		thread = new Thread(this);

		for (int i = 0; i < threadFruit.length; i++) {
			threadFruit[i] = new FruitsControlThread(fruit[i]);

		}

		thread.start();

	}

	public void paint(Graphics g) {
		// phải vẽ ảnh nền đầu tiên
		drawBackground(g);
		// ve catcher
		fruitCatcher.drawFruitCatcher(g);

		// ve trai cay
		for (int i = 0; i < threadFruit.length; i++) {
			fruit[i].drawFruit(g, i);
		}

		// khi kiemtravacham là true thì vẽ vụ nổ
		for (int i = 0; i < threadFruit.length; i++) {
			if (threadFruit[i].isExplosion) {
				g.drawImage(threadFruit[0].explosionImg, (FruitCatcher.x[0]) * 21, (FruitCatcher.y[0] - 3) * 21, 80,
						80, null);
			}

		}

		g.setFont(new Font("Serif", Font.ROMAN_BASELINE, 40));
		g.setColor(Color.red);
//		if (isPause) {
//			String message = "Tạm Dừng!!";
//			g.drawString(message, 250, 300);
//		}

	}

	public void continueGame() {
		try {
			String[] str;
			FileReader fr = new FileReader("files/data/data.txt");
			FileReader fr2 = new FileReader("files/data/point.txt");

			BufferedReader read = new BufferedReader(fr);
			BufferedReader read2 = new BufferedReader(fr2);
			String line = null;
			int i = 0;
			while ((line = read.readLine()) != null && i < 6) {
				str = line.split(" ");

				for (int j = 0; j < 4; j++) {
					threadFruit[i].fruit.x[j] = Integer.parseInt(str[j]);
				}
				for (int j = 4; j < 8; j++) {
					threadFruit[i].fruit.y[j - 4] = Integer.parseInt(str[j]);
				}

				i++;
			}

			read.close();
			line = read2.readLine();
			str = line.split(" ");
			read2.close();
			point = Integer.parseInt(str[0]);
			miss = Integer.parseInt(str[1]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveGame() {
		try {
			FileWriter fw = new FileWriter("files/data/data.txt");
			FileWriter fw2 = new FileWriter("files/data/highScore.txt", true);
			FileWriter fw3 = new FileWriter("files/data/point.txt");

			BufferedWriter write = new BufferedWriter(fw);
			BufferedWriter writeHighScore = new BufferedWriter(fw2);
			BufferedWriter writePoint = new BufferedWriter(fw3);

			for (int i = 0; i < threadFruit.length; i++) {
				for (int j = 0; j < 4; j++) {
					write.write(threadFruit[i].fruit.x[j] + " ");
				}
				for (int j = 0; j < 4; j++) {
					write.write(threadFruit[i].fruit.y[j] + " ");
				}
				write.newLine();
			}
			write.close();

			writePoint.write(point + " ");
			writePoint.write(miss + "");
			writePoint.close();
			writeHighScore.write(point + " ");
			writeHighScore.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		System.exit(0);
	}

	// vẽ ảnh nền
	public void drawBackground(Graphics g) {
		Image img = null;
		try {
			img = ImageIO.read(new File("files/images/forest.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(img, 0, 0, null);
	}

	@Override
	public void run() {
		while (true) {

			repaint();// vẽ lại liên tục

			time = Frame.endTime - System.currentTimeMillis();

			second = (int) time / 1000;

			if (second == 60) {
				minute++;
				Frame.beginTime = System.currentTimeMillis();

			}

			// chơi lại game
			if (miss == 20) {

				int check = JOptionPane.showConfirmDialog(null, "Bạn đã thua,bạn có muốn chơi lại?", "Thua Cuộc",
						JOptionPane.YES_NO_OPTION);
				if (check == 0) {
					for (int i = 0; i < threadFruit.length; i++) {
						threadFruit[i].setDelay(1 + i * 10);
						for (int j = 0; j < 6; j++) {
							// trái cây xuất hiện random tại một x ngẫu nhiên và y = -3
							int index = new Random().nextInt(28);
							threadFruit[i].fruit.x[j] = index;
							threadFruit[i].fruit.y[j] = -3;
						}
					}
					miss = 0;
					second = 0;
					point = 0;
					Frame.endTime = System.currentTimeMillis() + 120000;
				} else {
					System.exit(0);
				}
			}
			for (int i = 0; i < threadFruit.length - 1; i++) {

				if (threadFruit[i].fruit.y[0] > 2 && threadFruit[i + 1].fruit.y[0] > 2) {
					if (threadFruit[i].fruit.y[0] == threadFruit[i + 1].fruit.y[0]) {
						for (int j = 0; j < threadFruit[i].fruit.y.length; j++) {
							threadFruit[i].fruit.y[j] -= 2;
						}
					}
				}
			}

			for (int i = 0; i < threadFruit.length; i++) {

				if (threadFruit[i].checkCollision()) {
					point++;
					if (i == 5) {
						for (int j = 0; j < threadFruit.length; j++) {
							threadFruit[j].decreaseSpeed();
						}
						threadFruit[i].setDelay(100);
					} else {
						threadFruit[i].setDelay(10);
					}

				}

				// Observer
				Point p = new Point();
				new PointPanelDisplay(p);
				new RemainingPanelDisplay(p);
				new TotalPanelDisplay(p);
				p.setPoint(point, miss);
			}

		}
	}
}