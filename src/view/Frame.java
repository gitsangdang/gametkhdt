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

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.BevelBorder;

import controller.MainControlPanel;
import model.FruitsControlThread;
import model.ConTrolVolumeThread;

public class Frame extends JFrame {
	private static final long serialVersionUID = 1L;
	MainControlPanel frameControl;
	public static PointPanel framePoint;
	AboutGamePanel aboutGame;
	GameMode gameMode;
	public static String time = "";
	public static long beginTime;
	LogoPanel frameLogo;
	Clip clip;
	public static long endTime;
	HardMode hardMode;
	JButton frameButton[] = new JButton[7];
	JButton saveGame, continueGame;
	boolean isContinue = false;
	String str[];
	int highScorePoint;
	boolean isPlayByKey;
	Clip buttonClip, soundClip;
	FloatControl controlSound, controlButton;
	ConTrolVolumeThread soundThread;

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

		soundThread = new ConTrolVolumeThread();
		add(soundThread);
		soundThread.setVisible(false);

		setLayout(null);

		framePoint = new PointPanel();
		aboutGame = new AboutGamePanel();
		aboutGame.setLocation(0, 0);
		aboutGame.setSize(800, 800);
		aboutGame.setVisible(false);
		add(aboutGame);
		frameControl = new MainControlPanel();
		frameControl.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

		JLabel backGround = new JLabel(new ImageIcon("files/images/backGround.jpg"));
		backGround.setSize(800, 800);
		backGround.setLocation(0, 0);
		add(backGround);

		framePoint.setSize(780, 70);
		framePoint.setLocation(10, 10);

		frameLogo = new LogoPanel();
		frameLogo.setSize(200, 200);
		frameLogo.setLocation(300, 50);
		backGround.add(frameLogo);
		frameControl.setLocation(10, 90);
		frameControl.setSize(800, 800);
		saveGame = new JButton("Save Game Và Thoát");
		saveGame.setLocation(600, 20);
		saveGame.setSize(80, 30);
		saveGame.setBorder(BorderFactory.createEmptyBorder());

		saveGame.setFont(new Font("Serif", Font.ITALIC, 25));
		saveGame.setContentAreaFilled(false);
		saveGame.setForeground(Color.red);
		framePoint.add(saveGame);

		JLabel highScore = new JLabel();
		highScore.setLocation(550, 50);
		highScore.setSize(200, 30);
		highScore.setForeground(Color.WHITE);
		highScore.setFont(new Font("Serif", Font.BOLD, 20));
		backGround.add(highScore);
		try {
			FileReader readHighScore = new FileReader("files/data/highScore.txt");
			@SuppressWarnings("resource")
			BufferedReader readFile = new BufferedReader(readHighScore);
			String tam = readFile.readLine();
			if (tam != null) {
				str = tam.split(" ");
				highScorePoint = Integer.parseInt(str[0]);
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

		frameButton[1].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				backGround.setVisible(false);
				frameLogo.setVisible(false);
				framePoint.setVisible(true);
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
								MainControlPanel.container.setLocation(-1);

							}

							if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
								MainControlPanel.container.setLocation(1);

							}
							if (e.getKeyCode() == KeyEvent.VK_SPACE) {
								FruitsControlThread.isStart = !FruitsControlThread.isStart;
								MainControlPanel.container.isStart = !MainControlPanel.container.isStart;
								frameControl.isPause = !frameControl.isPause;
							}
						}
					});
				} else {
					MouseAdapter mouse = new MouseAdapter() {
						@Override
						public void mouseMoved(MouseEvent e) {
							MainControlPanel.container.setLocationByMouse(e.getX());
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

				gameMode = new GameMode();
				for (int i = 0; i < gameMode.btn.length; i++) {
					gameMode.btn[i].setFont(new Font("Serif", Font.ITALIC, 25));
					gameMode.btn[i].addMouseListener(mouseHover);
					gameMode.btn[i].addMouseMotionListener(mouseHover);
				}
				gameMode.btn[0].addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						isPlayByKey = false;
						playBy.setText("Phương Thức:Chơi Bằng Chuột");
						gameMode.dispose();
					}
				});
				gameMode.btn[1].addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						isPlayByKey = true;

						playBy.setText("Phương Thức:Chơi Bằng Phím");
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

		add(framePoint);

		// nút exit nằm ở vị trí đặc biệt
		frameButton[6] = new JButton();
		frameButton[6].setIcon(new ImageIcon("files/images/exitbutton.png"));
		frameButton[6].setSize(200, 50);
		frameButton[6].setLocation(300, 650);
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
		framePoint.setVisible(false);
		framePoint.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		framePoint.setBackground(Color.green);

		// sự kiện cho độ khó
		ActionListener action = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (e.getSource() == frameButton[2]) {
					hardMode = new HardMode();
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
		frameButton[2].addActionListener(action);

		// thêm sự kiện độ khó

		// sự kiện nút new game
		frameButton[0].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (isContinue == false) {
					backGround.setVisible(false);
					frameLogo.setVisible(false);
					framePoint.setVisible(true);
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
					framePoint.setVisible(true);
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
								MainControlPanel.container.setLocation(-1);

							}

							if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
								MainControlPanel.container.setLocation(1);

							}
							if (e.getKeyCode() == KeyEvent.VK_SPACE) {
								FruitsControlThread.isStart = !FruitsControlThread.isStart;
								MainControlPanel.container.isStart = !MainControlPanel.container.isStart;
								frameControl.isPause = !frameControl.isPause;
							}
						}
					});
				} else {
					MouseAdapter mouse = new MouseAdapter() {
						@Override
						public void mouseMoved(MouseEvent e) {
							MainControlPanel.container.setLocationByMouse(e.getX());
						}

					};
					addMouseListener(mouse);
					addMouseMotionListener(mouse);
				}
			}
		});
		// sự kiện phím
		frameButton[5].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				backGround.setVisible(false);
				frameLogo.setVisible(false);

				soundThread.setVisible(true);

			}
		});
		// cấu hình cho frame
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(800, 800);
		this.setResizable(false);
	}

}
