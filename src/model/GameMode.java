package model;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;

public class GameMode extends JFrame {
	private static final long serialVersionUID = 1L;
	public JButton btn[] = new JButton[2];
	String content[] = { "Chơi Bằng Chuột", "Chơi Bằng Phím" };

	public GameMode() {
		setTitle("Chọn Chế Độ Điều Khiển"); 
		setLocation(0, 500);
		setLayout(new FlowLayout());
		for (int i = 0; i < btn.length; i++) {
			btn[i] = new JButton(content[i]);
			btn[i].setPreferredSize(new Dimension(220, 30));
			btn[i].setContentAreaFilled(false);
			btn[i].setBorder(BorderFactory.createEmptyBorder());
			add(btn[i]);
		}
		setSize(700, 100);
		setVisible(true);
	}
}
