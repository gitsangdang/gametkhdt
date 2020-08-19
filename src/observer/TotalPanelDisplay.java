package observer;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TotalPanelDisplay extends JPanel implements Observer, DisplayView {
	private static final long serialVersionUID = 1L;
	private static int point = 0;
	private static int miss = 0;
	private Subject subject;
	private static JLabel label = new JLabel();

	public TotalPanelDisplay(Subject subject) {
		this.subject = subject;
		subject.addObserver(this);
	}

	public TotalPanelDisplay() {

	}

	public int total() {
		return (TotalPanelDisplay.point > TotalPanelDisplay.miss) ? (TotalPanelDisplay.point - TotalPanelDisplay.miss)
				: 0;
	}

	@Override
	public void update(int point, int miss) {
		TotalPanelDisplay.point = point;
		TotalPanelDisplay.miss = miss;
		display();
	}

	@Override
	public JLabel display() {
		label.setFont(new Font("Serif", Font.ROMAN_BASELINE, 18));
		label.setLocation(300, 10);
		label.setText("Tổng điểm: " + this.total());
		return label;
	}

}
