package gameMain;

import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;

/**
 * The Enum SoundEffect.
 */
//SoundEffect.EFFECTYOUWANTTOUSE.play()
public enum SoundEffect {
	
	/** The hey. */
	HEY(System.getProperty("user.dir") + "\\Audio\\" + "hey.mp3"), 
	
	/** The hurt. */
	HURT(System.getProperty("user.dir") + "\\Audio\\" + "hurt.mp3"), 
	
	/** The pcdie. */
	PCDIE(System.getProperty("user.dir") + "\\Audio\\" + "scream.mp3"), 
	
	/** The attack. */
	ATTACK(System.getProperty("user.dir") + "\\Audio\\" + "attack.mp3"), 
	
	/** The money. */
	MONEY(System.getProperty("user.dir") + "\\Audio\\" + "money.mp3"),
	
	/** The npcdie. */
	NPCDIE(System.getProperty("user.dir") + "\\Audio\\" + "die.mp3"), 
	
	/** The open. */
	OPEN(System.getProperty("user.dir") + "\\Audio\\" + "open.mp3");
	
	/**
	 * The Enum Volume.
	 */
	public static enum Volume {
		
		/** The mute. */
		MUTE, 
 /** The low. */
 LOW, 
 /** The medium. */
 MEDIUM, 
 /** The high. */
 HIGH
	}

	/** The volume. */
	public static Volume volume = Volume.HIGH;

	/** The clip. */
	private Clip clip;
	
	/** The audio dir. */
	private final String audioDir = System.getProperty("user.dir") + "\\Audio";

	/**
	 * Instantiates a new sound effect.
	 *
	 * @param soundFileName the sound file name
	 */
	SoundEffect(String soundFileName) {
		try {
			File audioClip = new File(soundFileName);
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioClip);
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Play.
	 */
	public void play() {
		if (volume != Volume.MUTE) {
			if (clip.isRunning())
				clip.stop(); 
			clip.setFramePosition(0); 
			clip.start(); 
		}
	}

	/**
	 * Inits the.
	 */
	static void init() {
		values(); 
	}
}
