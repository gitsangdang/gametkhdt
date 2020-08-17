package model.DecoratorFruit;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Watermelon extends FruitDecorator {

	public Watermelon() {
		super();
		name = "WATERMELON";
		path = "files/fruit/watermelon.png";
		try {
			file = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		imagesNum = 1;
	}

}
