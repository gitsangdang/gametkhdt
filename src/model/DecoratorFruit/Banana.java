package model.DecoratorFruit;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Banana extends FruitDecorator {

	public Banana() {
		super();
		name = "BANANA";
		path = "files/fruit/banana.png";
		try {
			file = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		imagesNum = 2;
	}

}
