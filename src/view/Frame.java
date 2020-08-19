package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.sound.sampled.FloatControl;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import controller.FruitsControlThread;
import controller.MainControlPanel;
import observer.PointPanelDisplay;
import observer.RemainingPanelDisplay;
import observer.TotalPanelDisplay;

public class Frame extends JFrame {
	private static final long serialVersionUID = 1L;
	public static long beginTime;
	public static long endTime;
	private static JPanel frameTop;
	private LogoPanel frameLogo;
	private JButton frameButton[] = new JButton[7];

	boolean isContinue = false;
	boolean isPlayByKey;
	FloatControl controlSound, controlButton;
	ConTrolVolume soundThread;

	public Frame() {
		MouseAdapter mouseHover = new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				JButton s = (JButton) e.getSource();
				s.setForeground(Color.decode("#ed0a0a"));
				s.setFont(new Font("Serif", Font.ITALIC, 30));
				if (soundThread.buttonClip.isOpen()) {
					soundThread.buttonClip.setFramePosition(0);

				}
				soundThread.buttonClip.start();

			}

			@Override
			public void mouseExited(MouseEvent e) {
				JButton s = (JButton) e.getSource();
				s.setForeground(Color.black);
				s.setFont(new Font("Serif", Font.ITALIC, 25));
			}
		};
		// âm thanh(phai la file wav thuan khong ep kieu moi hoat dong)

		soundThread = new ConTrolVolume();
		add(soundThread);
		soundThread.setVisible(false);

		setLayout(null);

		AboutGamePanel aboutGame = new AboutGamePanel();
		aboutGame.setLocation(0, 0);
		aboutGame.setSize(800, 800);
		aboutGame.setVisible(false);
		add(aboutGame);
		MainControlPanel frameControl = new MainControlPanel();
		frameControl.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

		JLabel backGround = new JLabel(new ImageIcon("files/images/backGround.jpg"));
		backGround.setSize(800, 800);
		backGround.setLocation(0, 0);
		add(backGround);

		frameTop = new JPanel();
		frameTop.setSize(780, 70);
		frameTop.setLocation(10, 10);

		JLabel framePoint = new PointPanelDisplay().display();
		framePoint.setSize(0, 0);
		framePoint.setLocation(10, 20);
		frameTop.add(framePoint);

		JLabel frameRemaining = new RemainingPanelDisplay().display();
		frameRemaining.setSize(0, 0);
		frameRemaining.setLocation(0, 0);
		frameTop.add(frameRemaining);

		JLabel frameTotal = new TotalPanelDisplay().display();
		frameTotal.setSize(0, 0);
		frameTotal.setLocation(60, 20);
		frameTop.add(frameTotal);

		frameLogo = new LogoPanel();
		frameLogo.setSize(200, 200);
		frameLogo.setLocation(300, 50);
		backGround.add(frameLogo);
		frameControl.setLocation(10, 90);
		frameControl.setSize(800, 800);

		JButton saveGame = new JButton("Save Game Và Thoát");
		saveGame.setLocation(100, 0);
		saveGame.setSize(800, 30);
		saveGame.setBorder(BorderFactory.createEmptyBorder());
		saveGame.setFont(new Font("Serif", Font.ITALIC, 25));
		saveGame.setContentAreaFilled(false);
		saveGame.setForeground(Color.red);
		frameTop.add(saveGame);

		JLabel highScore = new JLabel();
		highScore.setLocation(550, 50);
		highScore.setSize(200, 30);
		highScore.setForeground(Color.WHITE);
		highScore.setFont(new Font("Serif", Font.BOLD, 20));
		backGround.add(highScore);
		try {
			String str[];
			FileReader readHighScore = new FileReader("files/data/highScore.txt");
			@SuppressWarnings("resource")
			BufferedReader readFile = new BufferedReader(readHighScore);
			String tam = readFile.readLine();
			if (tam != null) {
				str = tam.split(" ");
				int highScorePoint = Integer.parseInt(str[0]);
				for (int i = 1; i < str.length; i++) {
					if (Integer.parseInt(str[i]) > highScorePoint) {
						highScorePoint = Integer.parseInt(str[i]);
					}
				}
				highScore.setText("Số Điểm Cao Nhất:" + highScorePoint);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		saveGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frameControl.saveGame();

			}
		});

		aboutGame.back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				aboutGame.setVisible(false);
				backGround.setVisible(true);
				frameLogo.setVisible(true);

			}
		});
		soundThread.back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				soundThread.setVisible(false);
				backGround.setVisible(true);
				frameLogo.setVisible(true);

			}
		});
		soundThread.back.addMouseListener(mouseHover);
		soundThread.back.addMouseMotionListener(mouseHover);
		aboutGame.back.addMouseListener(mouseHover);
		aboutGame.back.addMouseMotionListener(mouseHover);
		saveGame.addMouseListener(mouseHover);
		saveGame.addMouseMotionListener(mouseHover);

		// khởi tạo các nút và hiệu ứng

		for (int i = 0; i < frameButton.length - 1; i++) {

			frameButton[i] = new JButton();
			if (i == 4 || i == 6) {
				frameButton[i].setSize(350, 60);
				frameButton[i].setLocation(350 - 125, i * 60 + 260);
			} else {
				frameButton[i].setSize(250, 60);

				frameButton[i].setLocation(400 - 125, i * 60 + 260);
			}
			frameButton[i].setContentAreaFilled(false);
			frameButton[i].setBorder(BorderFactory.createEmptyBorder());
			backGround.add(frameButton[i]);
			frameButton[i].setFont(new Font("Serif", Font.ITALIC, 25));
			frameButton[i].setForeground(Color.black);
			frameButton[i].addMouseListener(mouseHover);
			frameButton[i].addMouseMotionListener(mouseHover);
		}

		frameButton[0].setText("PLAY");
		frameButton[1].setText("Continue Game");
		frameButton[2].setText("CHỌN ĐỘ KHÓ");
		frameButton[3].setText("CHỌN CÁCH ĐIỀU KHIỂN");
		frameButton[4].setText("ABOUT");
		frameButton[5].setText("TÙY CHỈNH ÂM THANH");
		JLabel speed = new JLabel();
		JLabel playBy = new JLabel();
		backGround.add(speed);
		backGround.add(playBy);
		speed.setLocation(300, 240);
		playBy.setLocation(280, 280);
		speed.setSize(250, 60);
		playBy.setSize(250, 60);
		speed.setFont(new Font("Serif", Font.ROMAN_BASELINE, 20));
		speed.setForeground(Color.red);
		playBy.setFont(new Font("Serif", Font.ROMAN_BASELINE, 20));
		playBy.setForeground(Color.red);

		// sự kiện nút new game
		frameButton[0].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (isContinue == false) {
					backGround.setVisible(false);
					frameLogo.setVisible(false);
					frameTop.setVisible(true);
					beginTime = System.currentTimeMillis();
					endTime = System.currentTimeMillis() + 120000;
					frameControl.setVisible(true);
					frameControl.setFocusable(true);
					frameControl.requestFocusInWindow();

					for (int i = 0; i < frameControl.threadFruit.length; i++) {
						frameControl.threadFruit[i].setDelay(1 + i * 10);
						frameControl.threadFruit[i].thread.start();

					}

				} else {
					backGround.setVisible(false);
					frameLogo.setVisible(false);
					frameTop.setVisible(true);
					beginTime = System.currentTimeMillis();
					endTime = System.currentTimeMillis() + 120000;
					frameControl.setVisible(true);
					frameControl.setFocusable(true);
					frameControl.requestFocusInWindow();

					for (int i = 0; i < frameControl.threadFruit.length; i++) {
						frameControl.threadFruit[i].setDelay(1 + i * 10);
						frameControl.threadFruit[i].thread.start();

					}

				}
				if (isPlayByKey) {
					addKeyListener(new KeyListener() {

						@Override
						public void keyTyped(KeyEvent e) {

						}

						@Override
						public void keyReleased(KeyEvent e) {

						}

						@Override
						public void keyPressed(KeyEvent e) {
							if (e.getKeyCode() == KeyEvent.VK_LEFT) {
								MainControlPanel.fruitCatcher.setLocation(-1);

							}

							if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
								MainControlPanel.fruitCatcher.setLocation(1);

							}
							if (e.getKeyCode() == KeyEvent.VK_SPACE) {
								FruitsControlThread.isStart = !FruitsControlThread.isStart;
								MainControlPanel.fruitCatcher.isStart = !MainControlPanel.fruitCatcher.isStart;
								frameControl.isPause = !frameControl.isPause;
							}
						}
					});
				} else {
					MouseAdapter mouse = new MouseAdapter() {
						@Override
						public void mouseMoved(MouseEvent e) {
							MainControlPanel.fruitCatcher.setLocationByMouse(e.getX());
						}

					};
					addMouseListener(mouse);
					addMouseMotionListener(mouse);
				}
			}
		});

		frameButton[1].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				backGround.setVisible(false);
				frameLogo.setVisible(false);
				frameTop.setVisible(true);
				beginTime = System.currentTimeMillis();
				endTime = System.currentTimeMillis() + 120000;
				frameControl.setVisible(true);
				frameControl.setFocusable(true);
				frameControl.requestFocusInWindow();

				for (int i = 0; i < frameControl.threadFruit.length; i++) {
					frameControl.threadFruit[i].setDelay(1 + i * 10);
					frameControl.threadFruit[i].thread.start();

				}

				if (isPlayByKey) {
					addKeyListener(new KeyListener() {

						@Override
						public void keyTyped(KeyEvent e) {

						}

						@Override
						public void keyReleased(KeyEvent e) {

						}

						@Override
						public void keyPressed(KeyEvent e) {
							if (e.getKeyCode() == KeyEvent.VK_LEFT) {
								MainControlPanel.fruitCatcher.setLocation(-1);

							}

							if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
								MainControlPanel.fruitCatcher.setLocation(1);

							}
							if (e.getKeyCode() == KeyEvent.VK_SPACE) {
								FruitsControlThread.isStart = !FruitsControlThread.isStart;
								MainControlPanel.fruitCatcher.isStart = !MainControlPanel.fruitCatcher.isStart;
								frameControl.isPause = !frameControl.isPause;
							}
						}
					});
				} else {
					MouseAdapter mouse = new MouseAdapter() {
						@Override
						public void mouseMoved(MouseEvent e) {
							MainControlPanel.fruitCatcher.setLocationByMouse(e.getX());
						}

					};
					addMouseListener(mouse);
					addMouseMotionListener(mouse);
				}
				frameControl.continueGame();
			}
		});

		frameButton[3].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				GameMode gameMode = new GameMode();
				for (int i = 0; i < gameMode.btn.length; i++) {
					gameMode.btn[i].setFont(new Font("Serif", Font.ITALIC, 25));
					gameMode.btn[i].addMouseListener(mouseHover);
					gameMode.btn[i].addMouseMotionListener(mouseHover);
				}
				gameMode.btn[0].addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						isPlayByKey = false;
						playBy.setText("Chơi Bằng Chuột");
						gameMode.dispose();
					}
				});
				gameMode.btn[1].addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						isPlayByKey = true;

						playBy.setText("Chơi Bằng Phím");
						gameMode.dispose();
					}
				});
			}
		});

		// sự kiện cho nút about
		frameButton[4].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				backGround.setVisible(false);
				frameLogo.setVisible(false);

				aboutGame.setVisible(true);
			}
		});

		add(frameTop);

		// sự kiện phím
		frameButton[5].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				backGround.setVisible(false);
				frameLogo.setVisible(false);

				soundThread.setVisible(true);

			}
		});

		// nút exit nằm ở vị trí đặc biệt
		frameButton[6] = new JButton();
		frameButton[6].setIcon(new ImageIcon("files/images/exitbutton.png"));
		frameButton[6].setSize(200, 50);
		frameButton[6].setLocation(300, 620);
		frameButton[6].setBorder(BorderFactory.createEmptyBorder());
		frameButton[6].setContentAreaFilled(false);
		backGround.add(frameButton[6]);

		// sự kiện cho nút exit
		frameButton[6].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int check = JOptionPane.showConfirmDialog(null, "Bạn Có Muốn Thoát", "EXIT", JOptionPane.YES_NO_OPTION);
				if (check == 0) {

					System.exit(0);

				}
			}
		});

		add(frameControl);
		frameControl.setVisible(false);
		frameTop.setVisible(false);
		frameTop.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		frameTop.setBackground(Color.green);

		// sự kiện cho độ khó
		ActionListener action = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (e.getSource() == frameButton[2]) {
					HardMode hardMode = new HardMode();
					for (int i = 0; i < hardMode.btn.length; i++) {
						hardMode.btn[i].setFont(new Font("Serif", Font.ITALIC, 25));
						hardMode.btn[i].addMouseListener(mouseHover);
						hardMode.btn[i].addMouseMotionListener(mouseHover);

					}
					hardMode.btn[0].addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							FruitsControlThread.speed = 300;

							speed.setText("ĐỘ KHÓ: DỄ");
							hardMode.dispose();
						}
					});
					hardMode.btn[1].addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							FruitsControlThread.speed = 180;

							speed.setText("ĐỘ KHÓ: TRUNG BÌNH");
							hardMode.dispose();
						}
					});
					hardMode.btn[2].addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							FruitsControlThread.speed = 80;

							speed.setText("ĐỘ KHÓ: KHÓ");
							hardMode.dispose();
						}
					});
				}

			}
		};
		// thêm sự kiện độ khó
		frameButton[2].addActionListener(action);
	
		// cấu hình cho frame
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(800, 800);
		this.setResizable(false);
	}

}
