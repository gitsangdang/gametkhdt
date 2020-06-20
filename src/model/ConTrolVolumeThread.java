package model;

import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ConTrolVolumeThread extends JPanel implements Runnable {
	Thread thread;
	public FloatControl controlSound, controlButton;
	public Clip buttonClip, soundClip;
	public ConTrolVolumeThread soundThread;
	public JSlider slider, sliderButton;
	public JButton back;

	public ConTrolVolumeThread() {
		thread = new Thread(this);
		try {
			AudioInputStream audio = AudioSystem.getAudioInputStream(new File("src/musics/music.wav"));
			soundClip = AudioSystem.getClip();
			soundClip.open(audio);
			soundClip.loop(soundClip.LOOP_CONTINUOUSLY);
			controlSound = (FloatControl) soundClip.getControl(FloatControl.Type.MASTER_GAIN);
			controlSound.setValue(0);
			soundClip.start();

		} catch (UnsupportedAudioFileException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (LineUnavailableException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			buttonClip = AudioSystem.getClip();
			buttonClip.open(AudioSystem.getAudioInputStream(new File("src/musics/button2.wav")));
			controlButton = (FloatControl) buttonClip.getControl(FloatControl.Type.MASTER_GAIN);
			controlButton.setValue(0);

		} catch (LineUnavailableException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedAudioFileException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setLayout(null);
		JLabel label = new JLabel("Điều Chỉnh Âm Thanh Nhạc Nền:");
		add(label);
		label.setSize(200, 40);
		label.setLocation(100, 200);
		JLabel label2 = new JLabel("Điều Chỉnh Âm Thanh Hiệu Ứng:");
		add(label2);
		label2.setSize(200, 40);
		label2.setLocation(100, 260);
		Hashtable<Object, Object> labelTable = new Hashtable<>();
		labelTable.put(-30, new JLabel("MIN"));
		labelTable.put(6, new JLabel("MAX"));
		slider = new JSlider(JSlider.HORIZONTAL, -30, 6, 0);
		add(slider);
		slider.setLocation(300, 200);
		slider.setSize(300, 50);
		sliderButton = new JSlider(JSlider.HORIZONTAL, -30, 6, 0);
		add(sliderButton);
		sliderButton.setSize(300, 50);
		sliderButton.setLocation(300, 260);
		slider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				int i = ((JSlider) e.getSource()).getValue();
				System.out.println(i);
				controlSound.setValue(i);
			}
		});
		slider.setMajorTickSpacing(9);
		slider.setMinorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setLabelTable(labelTable);
		slider.setPaintLabels(true);
		sliderButton.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				int i = ((JSlider) e.getSource()).getValue();
				System.out.println(i);
				controlButton.setValue(i);
			}
		});
		sliderButton.setMajorTickSpacing(9);
		sliderButton.setMinorTickSpacing(1);
		sliderButton.setPaintTicks(true);
		sliderButton.setLabelTable(labelTable);
		sliderButton.setPaintLabels(true);
		back = new JButton("Back To Menu");
		add(back);
		back.setFont(new Font("Serif", Font.ITALIC, 25));
		back.setContentAreaFilled(false);
		back.setBorder(BorderFactory.createEmptyBorder());
		back.setSize(250, 60);
		back.setLocation(250, 650);

		setSize(800, 800);
	}

	@Override
	public void run() {
		soundClip.start();
	}
}
