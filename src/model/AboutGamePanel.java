package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AboutGamePanel extends JPanel {
	JLabel[] aboutGame;
	JLabel[] memberInformation;
	Image arrowKey;
	public JButton back;

	public AboutGamePanel() {

		try {
			arrowKey = ImageIO.read(new File("files/images/arrowkey.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JLabel arrowKeyLabel = new JLabel(new ImageIcon(arrowKey));
		arrowKeyLabel.setLocation(400, 300);
		arrowKeyLabel.setSize(200, 130);
		JLabel backGround = new JLabel(new ImageIcon("files/images/fruitbg.jpg"));
		backGround.setLocation(0, 0);
		backGround.setSize(800, 800);
		backGround.add(arrowKeyLabel);
		add(backGround);
		aboutGame = new JLabel[4];
		memberInformation = new JLabel[4];
		setLayout(null);
		aboutGame[0] = new JLabel(
				"Trò chơi hứng trái cây là một trò chơi kinh điển được rất nhiều người yêu thích trên thế ");
		aboutGame[1] = new JLabel(
				"giới,trong trò chơi này,người chơi phải điều khiển cho cô gái di chuyển qua lại để hứng các ");
		aboutGame[2] = new JLabel(
				"trái cây liên tục rơi xuống,nhấn phím <- để di chuyển qua bên trái và -> để di chuyển qua bên ");
		aboutGame[3] = new JLabel("phải,cố gắng để đạt được số điểm cao nhất");
		for (int i = 0; i < aboutGame.length; i++) {
			aboutGame[i].setSize(800, 50);
			aboutGame[i].setLocation(10, 130 + i * 41);
			backGround.add(aboutGame[i]);
			aboutGame[i].setForeground(Color.red);
			aboutGame[i].setFont(new Font("Serif", Font.ROMAN_BASELINE, 20));
		}
		memberInformation[0] = new JLabel("Tìm hiểu và xây dựng bởi nhóm 15:");
		memberInformation[1] = new JLabel("1.16130543-Đặng Thanh Sang");
		memberInformation[2] = new JLabel("2.16130579- Phạm Bùi Tuấn Thành");
		memberInformation[3] = new JLabel("3.15130024-Trần Văn Đăng");
		for (int i = 0; i < memberInformation.length; i++) {
			memberInformation[i].setFont(new Font("Serif", Font.ROMAN_BASELINE, 20));
			memberInformation[i].setSize(800, 50);
			memberInformation[i].setLocation(10, 320 + i * 41);
			memberInformation[i].setForeground(Color.blue);
			backGround.add(memberInformation[i]);
		}
		JLabel left = new JLabel("Qua Trái");
		left.setLocation(400, 420);
		left.setSize(80, 40);
		backGround.add(left);
		JLabel right = new JLabel("Qua Phải");
		right.setLocation(550, 420);
		right.setSize(80, 40);
		backGround.add(right);
		back = new JButton("Back To Menu");
		back.setFont(new Font("Serif", Font.ITALIC, 25));
		back.setContentAreaFilled(false);
		back.setBorder(BorderFactory.createEmptyBorder());
		back.setSize(300, 50);
		back.setLocation(250, 650);
		backGround.add(back);

	}

}
