package model;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Boom extends FruitDecorator {
	public Boom() {
		super();
		name = "BOOM";
		path = "files/fruit/boom.png";
		try {
			file = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		imagesNum = 5;
	}

}
