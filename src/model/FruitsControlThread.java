package model;

import java.awt.Image;
import java.io.File;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import controller.MainControlPanel;

public class FruitsControlThread implements Runnable {
	public Thread thread;
	public Fruit fruit;
	int index;// biến random
	Random rd = new Random();
	Clip goalSound;

	public Image explosionImg;
	public boolean isExplosion = false;

	public static boolean isStart = true;// điều khiển chức năng pause
	public static int speed = 100;
	int delayExplosion = 0;
	public int delay = 0;
	public boolean isSpeedDown = false;
	public int speedDownTime = 20;
	public boolean isHelp = false;

	public FruitsControlThread(Fruit fruit) {

		try {
			goalSound = AudioSystem.getClip();
			goalSound.open(AudioSystem.getAudioInputStream(new File("files/musics/point.wav")));
			explosionImg = ImageIO.read(new File("files/images/explosionicon.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		thread = new Thread(this);
		this.fruit = fruit;
		delay = 30;
	}

	public void setDelay(int n) {
		this.delay = n;
	}

	@Override
	public void run() {

		while (true) {
			if (isStart) {

				if (fruit.y[3] == 28) {

					MainControlPanel.miss++;
				}
				if (fruit.y[3] < 31) {
					// phần dưới của trái cây chưa chạm đất:y[2] hoặc y[3]
					if (this.delay == 0) {

						for (int i = 0; i < 4; i++) {

							fruit.y[i]++;
						}
					} else {
						if (delay > 0) {
							this.delay--;
						}
					}
				} else {

					for (int i = 0; i < 4; i++) {
						index = rd.nextInt(28);

						fruit.x[0] = index;// trái cây xuất hiện random tại một x ngẫu nhiên và y =0
						fruit.x[1] = index + 1;
						fruit.x[2] = index;
						fruit.x[3] = index + 1;
						fruit.y[0] = -3;
						fruit.y[1] = -3;
						fruit.y[2] = -2;
						fruit.y[3] = -2;

					}

				}

				// thời gian vụ nổ tồn tại
				if (isExplosion) {
					if (delayExplosion == 3) {
						isExplosion = false;
						delayExplosion = 0;
					} else {

						delayExplosion++;
					}
				}

				if (isSpeedDown) {
					if (speedDownTime == 0) {
						isSpeedDown = false;
						increaseSpeed();
						speedDownTime = 20;
					} else {
						speedDownTime--;
					}
				}
			}

			try {
				Thread.sleep(speed);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public void decreaseSpeed() {
		FruitsControlThread.speed += 30;

		isSpeedDown = true;
	}

	public void increaseSpeed() {
		FruitsControlThread.speed -= 30;
	}

	// kiem tra va cham
	public boolean checkCollision() {
		for (int i = 0; i < 6; i++) {

			if ((fruit.y[2] == FruitCatcher.y[i] || fruit.y[3] == FruitCatcher.y[i])
					&& (fruit.x[2] == FruitCatcher.x[i] || fruit.x[3] == FruitCatcher.x[i])) {
				if (goalSound.isOpen()) {
					goalSound.setFramePosition(0);
				}
				goalSound.start();
				index = rd.nextInt(28);
				fruit.x[0] = index;
				fruit.x[1] = index + 1;
				fruit.x[2] = index;
				fruit.x[3] = index + 1;
				fruit.y[0] = -3;
				fruit.y[1] = -3;
				fruit.y[2] = -2;
				fruit.y[3] = -2;

				isExplosion = true;
				return true;

			}

		}

		return false;
	}

}
