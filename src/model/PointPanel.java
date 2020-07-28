package model;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PointPanel extends JPanel implements Observer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static JLabel point = new JLabel("ĐIỂM SỐ CỦA BẠN LÀ:");
	public static JLabel miss = new JLabel("BẠN LÀM RỚT:");

	JLabel label;

	public PointPanel() {
		label = new JLabel();
		setLayout(new GridLayout(2, 2));
		point.setFont(new Font("Serif", Font.ROMAN_BASELINE, 18));
		point.setLocation(10, 10);

		miss.setFont(new Font("Serif", Font.ROMAN_BASELINE, 18));
		miss.setLocation(10, 10);
		label = new JLabel();
		add(point);
		add(miss);
		add(label);
	}

	@Override
	public void addNew(JButton btn) {
		this.add(btn);

	}
}
