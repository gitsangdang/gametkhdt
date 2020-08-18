package model;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Apple extends FruitDecorator {

	public Apple() {
		super();
		name = "APPLE";
		path = "files/fruit/apple.png";
		try {
			file = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		imagesNum = 0;
	}

}
