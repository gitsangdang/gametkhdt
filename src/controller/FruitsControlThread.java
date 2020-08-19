package controller;

import java.awt.Image;
import java.io.File;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import model.Fruit;
import model.FruitCatcher;

public class FruitsControlThread implements Runnable {
	public Thread thread;
	public Fruit fruit;
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
		// mở file âm thanh. Ghi âm thanh khi va chạm
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
				if (fruit.y[0] == 28) {
					MainControlPanel.miss++;
				}
				if (fruit.y[0] < 30) {
					// phần dưới của trái cây chưa chạm đất:y[2] hoặc y[3]
					if (this.delay == 0) {
						for (int i = 0; i < 6; i++) {
							fruit.y[i]++;
						}
					} else {
						if (delay > 0) {
							this.delay--;
						}
					}
				} else {
//				 trái cây xuất hiện random tại một x ngẫu nhiên và y = -3
					for (int i = 0; i < 6; i++) {
						for (int j = 0; j < 6; j++) {
							int index = new Random().nextInt(28);
							fruit.x[i] = index;
							fruit.y[j] = -3;
						}
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

			if ((fruit.y[0] == FruitCatcher.y[i] || fruit.y[0] == FruitCatcher.y[i])
					&& (fruit.x[0] == FruitCatcher.x[i] || fruit.x[0] == FruitCatcher.x[i])) {
				if (goalSound.isOpen()) {
					goalSound.setFramePosition(0);
				}
				goalSound.start();

				// khi va cham roi. Random ra fruit o vi tri khac
				int index = new Random().nextInt(28);
				for (int j = 0; j < 6; j++) {
					fruit.x[j] = index;
					fruit.y[j] = -3;
				}

				isExplosion = true;
				return true;
			}
		}
		return false;
	}

}
