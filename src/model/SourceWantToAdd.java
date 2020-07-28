package model;

import java.util.ArrayList;
import javax.swing.JButton;

public class SourceWantToAdd implements Subject {
	ArrayList<Observer> list = new ArrayList<>();
	public String content;
	JButton button;
 
	public SourceWantToAdd(String content) {
		this.content = content;
		button = new JButton("GGGG");

	}

	@Override
	public void addObserver(Observer o) {
		list.add(o);

	}

	@Override
	public void removeObserver(Observer o) {
		list.remove(o);

	}

	@Override
	public void notifys() {

		for (Observer o : list) {
			o.addNew(new JButton("nút được add"));
			System.out.println(1);

		}

	}

	@Override
	public void validation() {

	}

}
