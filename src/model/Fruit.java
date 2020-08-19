package model;

import java.awt.Graphics;
import java.util.Random;

public class Fruit {
	public int x[] = new int[6];
	public int y[] = new int[6];
	private static FruitDecorator apple = new Apple();
	private static FruitDecorator watermelon = new Watermelon();
	private static FruitDecorator banana = new Banana();
	private static FruitDecorator strawberry = new Strawberry();
	private static FruitDecorator orange = new Orange();
	private static FruitDecorator boom = new Boom();

	public Fruit() {
		Random rd = new Random();
		int index = rd.nextInt(20);
		for (int i = 0; i < x.length; i++) {
			for (int j = 0; j < y.length; j++) {
				x[i] = index;
				y[j] = -3;
			}
		}
	}

	public void drawFruit(Graphics g, int imageNumber) {
		if (imageNumber == apple.getImagesNum()) {
			g.drawImage(apple.getFile(), x[0] * 21, y[0] * 21, 40, 40, null);
		}

		if (imageNumber == watermelon.getImagesNum()) {
			g.drawImage(watermelon.getFile(), x[0] * 21, y[0] * 21, 40, 40, null);
		}

		if (imageNumber == banana.getImagesNum()) {
			g.drawImage(banana.getFile(), x[0] * 21, y[0] * 21, 40, 40, null);
		}

		if (imageNumber == strawberry.getImagesNum()) {
			g.drawImage(strawberry.getFile(), x[0] * 21, y[0] * 21, 40, 40, null);
		}

		if (imageNumber == orange.getImagesNum()) {
			g.drawImage(orange.getFile(), x[0] * 21, y[0] * 21, 40, 40, null);
		}

		if (imageNumber == boom.getImagesNum()) {
			g.drawImage(boom.getFile(), x[0] * 21, y[0] * 21, 40, 40, null);
		}
	}
}
