/*
 * Created on 25-feb-2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package util;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;

/**
 * @author Mike
 * 
 * Usage: 
 * 
 *		SoundUtil util = new SoundUtil();
 *		util.play(new File("c:\\home\\programming\\cloudDetect\\conf\\sounds\\alert.wav"), 2);
 *
 */
public class SoundUtil {
	
	private Clip m_clip;
	
	/*
	 *      The clip will be played nLoopCount + 1 times.
	 */
	public void play(File clipFile, int nLoopCount) {
		AudioInputStream audioInputStream = null;
		try {
			audioInputStream = AudioSystem.getAudioInputStream(clipFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (audioInputStream != null) {
			AudioFormat format = audioInputStream.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			try {
				m_clip = (Clip) AudioSystem.getLine(info);
				m_clip.addLineListener(this);
				m_clip.open(audioInputStream);
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			m_clip.loop(nLoopCount);
		} else {
			System.out.println(
				"ClipPlayer.<init>(): can't get data from file "
					+ clipFile.getName());
		}
	}

}
