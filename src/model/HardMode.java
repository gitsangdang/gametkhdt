package model;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;

public class HardMode extends JFrame {
	private static final long serialVersionUID = 1L;
	public JButton btn[] = new JButton[3];
	public String contentBtn[] = { "DỄ", "TRUNG BÌNH", "KHÓ" };

	public HardMode() {
		setTitle("Chọn Độ Khó");
		setLocation(0, 400);
		setLayout(new FlowLayout());
		for (int i = 0; i < btn.length; i++) {
			btn[i] = new JButton(contentBtn[i]);
			btn[i].setPreferredSize(new Dimension(200, 30));
			btn[i].setContentAreaFilled(false);
			btn[i].setBorder(BorderFactory.createEmptyBorder());
			add(btn[i]);
		}
		setSize(700, 100);
		setVisible(true);

	}
}
