package model.DecoratorFruit;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Mango extends FruitDecorator {
	public Mango() {
		super();
		name = "MANGO";
		path = "files/fruit/mango.png";
		try {
			file = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		imagesNum = 5;
	}

}
