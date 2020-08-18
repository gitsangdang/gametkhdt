package observer;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RemainingPanelDisplay extends JPanel implements Observer, DisplayView {
	private static final long serialVersionUID = 1L;
	private static int miss;
	private static int point;
	private Subject subject;
	private static JLabel label = new JLabel();

	public RemainingPanelDisplay() {

	}

	public RemainingPanelDisplay(Subject subject) {
		this.subject = subject;
		subject.addObserver(this);
	}

	@Override
	public void update(int point, int miss) {
		RemainingPanelDisplay.point = point;
		RemainingPanelDisplay.miss = miss;
		display();
	}

	@Override
	public JLabel display() {
		label.setFont(new Font("Serif", Font.ROMAN_BASELINE, 18));
		label.setLocation(10, 10);
		label.setText("Còn lại: " + (20 - miss) + "lượt");
		return label;
	}

}
