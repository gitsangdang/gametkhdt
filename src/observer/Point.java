package observer;

import java.util.ArrayList;
import java.util.List;

public class Point implements Subject {
	private List<Observer> observers;
	private int point;
	private int miss;

	public Point() {
		observers = new ArrayList<Observer>();
	}

	@Override
	public void addObserver(Observer o) {
		observers.add(o);

	}

	@Override
	public void notifyObserver() {
		for (int i = 0; i < observers.size(); i++) {
			Observer ob = observers.get(i);
			ob.update(point, miss);
		}

	}

	public void pointChanged() {
		notifyObserver();
	}

	public void setPoint(int point, int miss) {
		this.point = point;
		this.miss = miss;
		pointChanged();
	}

	public int getPoint() {
		return point;
	}

	public int getMiss() {
		return miss;
	}

}
