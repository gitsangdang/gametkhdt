package observer;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PointPanelDisplay extends JPanel implements Observer, DisplayView {
	private static final long serialVersionUID = 1L;
	private static int point;
	private static int miss;
	private static JLabel label = new JLabel();
	private Subject subject;

	public PointPanelDisplay(Subject subject) {
		this.subject = subject;
		subject.addObserver(this);
	}

	public PointPanelDisplay() {

	}

	@Override
	public void update(int point, int miss) {
		PointPanelDisplay.point = point;
		PointPanelDisplay.miss = miss;
		display();
	}

	@Override
	public JLabel display() {
		label.setFont(new Font("Serif", Font.ROMAN_BASELINE, 18));
		label.setText("Bắt được: " + point);
		return label;
	}

}
