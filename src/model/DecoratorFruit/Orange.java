package model.DecoratorFruit;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Orange extends FruitDecorator {

	public Orange() {
		super();
		name = "ORANGE";
		path = "files/fruit/orange.png";
		try {
			file = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		imagesNum = 3;
	}

}
