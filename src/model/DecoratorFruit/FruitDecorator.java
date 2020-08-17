package model.DecoratorFruit;

import java.awt.Image;

public abstract class FruitDecorator {
	protected String name;
	protected String path;
	protected Image file;
	protected int imagesNum;

	public FruitDecorator() {
	}

	public String getName() {
		return name;
	}

	public String getPath() {
		return path;
	}

	public int getImagesNum() {
		return imagesNum;
	}

	public Image getFile() {
		return file;
	}

	@Override
	public String toString() {
		return "FruitDecorator [name=" + name + ", path=" + path + ", imagesNum=" + imagesNum + "]";
	}

}
