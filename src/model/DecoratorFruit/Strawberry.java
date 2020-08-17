package model.DecoratorFruit;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Strawberry extends FruitDecorator {

	public Strawberry() {
		super();
		name = "APPLE";
		path = "files/fruit/ichigo.png";
		try {
			file = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		imagesNum = 4;
	}

}
