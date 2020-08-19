package model;

import java.awt.Graphics;
import java.util.Random;

public class Fruit {
	public int x[] = new int[6];
	public int y[] = new int[6];
	private static FruitDecorator apple = new Apple();
	private static FruitDecorator watermelon = new Watermelon();
	private static FruitDecorator banana = new Banana();
	private static FruitDecorator orange = new Orange();
	private static FruitDecorator strawberry = new Strawberry();
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
		switch (imageNumber) {
		case 0:
			g.drawImage(apple.getFile(), x[0] * 21, y[0] * 21, 40, 40, null);
			break;
		case 1:
			g.drawImage(watermelon.getFile(), x[0] * 21, y[0] * 21, 40, 40, null);
			break;
		case 2:
			g.drawImage(strawberry.getFile(), x[0] * 21, y[0] * 21, 40, 40, null);
			break;
		case 3:
			g.drawImage(orange.getFile(), x[0] * 21, y[0] * 21, 40, 40, null);
			break;
		case 4:
			g.drawImage(orange.getFile(), x[0] * 21, y[0] * 21, 40, 40, null);
			break;
		case 5:
			g.drawImage(boom.getFile(), x[0] * 21, y[0] * 21, 40, 40, null);
			break;
		default:
			break;
		}
	}
}
